package pe.edu.utp.tmo.exception; import org.springframework.http.HttpStatus;
public class TicketNoEncontradoException extends BusinessException { public TicketNoEncontradoException(Long id){super("TICKET_NO_ENCONTRADO",HttpStatus.NOT_FOUND,"No se encontró el ticket solicitado: "+id);} }
