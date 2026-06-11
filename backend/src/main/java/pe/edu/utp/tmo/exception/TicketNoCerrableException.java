package pe.edu.utp.tmo.exception; import org.springframework.http.HttpStatus;
public class TicketNoCerrableException extends BusinessException { public TicketNoCerrableException(String message){super("TICKET_NO_CERRABLE",HttpStatus.CONFLICT,message);} }
