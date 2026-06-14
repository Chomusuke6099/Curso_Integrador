package pe.edu.utp.tmo.infrastructure.persistence;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Repository;
import pe.edu.utp.tmo.dao.RegistroTMODAO;
import pe.edu.utp.tmo.infrastructure.persistence.repository.RegistroTMORepository;
import pe.edu.utp.tmo.model.entity.RegistroTMO;

@Repository

public class RegistroTMOJpaDAO implements RegistroTMODAO{
    private final RegistroTMORepository r;
    
    public RegistroTMOJpaDAO(RegistroTMORepository r){this.r=r;}
    public RegistroTMO guardar(RegistroTMO x){return r.save(x);}
    public Optional<RegistroTMO> buscarPorCaso(Long id){return r.findByCasoId(id);} 
    public Double promedioPorPeriodo(LocalDateTime i,LocalDateTime f){return r.promedioPorPeriodo(i,f);} 
    public List<RegistroTMO> listarPorPeriodo(LocalDateTime i,LocalDateTime f){return r.findByFinBetween(i,f);} }