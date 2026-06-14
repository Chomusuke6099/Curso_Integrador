package pe.edu.utp.tmo.service.impl;
import java.time.*;
import java.util.*;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.tmo.dao.*;
import pe.edu.utp.tmo.dto.request.*;
import pe.edu.utp.tmo.dto.response.TicketResponse;
import pe.edu.utp.tmo.exception.*;
import pe.edu.utp.tmo.mapper.TicketMapper;
import pe.edu.utp.tmo.model.entity.*;
import pe.edu.utp.tmo.model.enums.*;
import pe.edu.utp.tmo.service.TicketService;
import pe.edu.utp.tmo.util.*;

@Service

public class TicketServiceImpl implements TicketService {
 private static final Logger log=LoggerFactory.getLogger(TicketServiceImpl.class);
 private final UsuarioDAO usuarios; private final CasoDAO casos;
 private final RegistroTMODAO registros;
 private final TipificacionDAO tips;
 private final AuditoriaDAO auditoria;
 private final TmoCalculator calc;
 private final TicketCodeGenerator codes;
 private final TicketMapper mapper;
 private final Clock clock;

 public TicketServiceImpl(UsuarioDAO usuarios,CasoDAO casos,RegistroTMODAO registros,TipificacionDAO tips,AuditoriaDAO auditoria,TmoCalculator calc,TicketCodeGenerator codes,TicketMapper mapper,Clock clock){this.usuarios=usuarios;
    this.casos=casos;this.registros=registros;this.tips=tips;this.auditoria=auditoria;this.calc=calc;
    this.codes=codes;this.mapper=mapper;this.clock=clock;
}

 @Transactional

 public TicketResponse abrirTicket(String correo,TicketCreateRequest r){
    Usuario u=user(correo);
    if(u.getRol()!=RolUsuario.ANALISTA) throw new AccesoNoAutorizadoException("Solo analistas abren tickets.");
    LocalDateTime now=LocalDateTime.now(clock);
    Caso c=casos.guardar(Caso.abrir(codes.nuevoCodigo(),u,r.tipoCaso(),r.referencia(),now));
    auditoria.registrarEvento(new AuditoriaEvento(u,c,"APERTURA_TICKET",now,"Ticket abierto tipo="+r.tipoCaso(),"OK"));
    log.info("Ticket abierto id={} usuarioId={}",c.getId(),u.getId()); return mapper.toResponse(c,null);
}

 @Transactional
 
 public TicketResponse cerrarTicket(String correo,Long id,TicketCloseRequest r){
    Usuario u=user(correo);
    Caso c=casos.buscarPorId(id).orElseThrow(()->new TicketNoEncontradoException(id));
    permiso(u,c);
    if(c.getEstado()==EstadoCaso.CERRADO) throw new TicketNoCerrableException("El ticket ya está cerrado.");
    Tipificacion t=tips.buscarPorId(r.tipificacionId()).filter(Tipificacion::isActivo).orElseThrow(()->new ValidacionNegocioException("TIPIFICACION_INVALIDA","Tipificación inactiva o inexistente."));
    String obs=ValidationUtils.observacion(r.observacion());
    LocalDateTime fin=LocalDateTime.now(clock);
    long tmo=calc.calcularSegundos(c.getFechaInicio(),fin);
    c.cerrar(t,obs,fin); Caso saved=casos.guardar(c);
    RegistroTMO reg=registros.guardar(new RegistroTMO(saved,saved.getFechaInicio(),fin,tmo));
    auditoria.registrarEvento(new AuditoriaEvento(u,saved,"CIERRE_TICKET",fin,"Ticket cerrado tmoSegundos="+tmo+", tipificacionId="+t.getId(),"OK"));
    log.info("Ticket cerrado id={} usuarioId={} tmoSegundos={}",saved.getId(),u.getId(),tmo);
    return mapper.toResponse(saved,reg.getTmoSegundos());
}

 @Transactional(readOnly=true)
 
 public List<TicketResponse> listarMisTickets(String correo){
    Usuario u=user(correo);
    return casos.listarPorAnalista(u.getId()).stream().map(c->mapper.toResponse(c,registros.buscarPorCaso(c.getId()).map(RegistroTMO::getTmoSegundos).orElse(null))).toList();
}

 private Usuario user(String correo){
    return usuarios.buscarPorCorreo(correo).filter(Usuario::isActivo).orElseThrow(()->new AccesoNoAutorizadoException("Usuario no autorizado."));
}

 private void permiso(Usuario u,Caso c){
    boolean propietario=c.getAnalista()!=null && (Objects.equals(c.getAnalista().getId(),u.getId())||c.getAnalista().getCorreo().equalsIgnoreCase(u.getCorreo()));
    boolean superior=Set.of(RolUsuario.SUPERVISOR,RolUsuario.GERENTE,RolUsuario.ADMINISTRADOR).contains(u.getRol());
    if(!propietario&&!superior) throw new AccesoNoAutorizadoException("No puede operar este ticket.");
    if(u.getRol()==RolUsuario.SUPERVISOR && c.getAnalista()!=null && !u.getArea().equalsIgnoreCase(c.getAnalista().getArea())) throw new AccesoNoAutorizadoException("Fuera de su área.");
}
}
