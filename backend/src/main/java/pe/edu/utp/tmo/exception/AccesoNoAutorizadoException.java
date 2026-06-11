package pe.edu.utp.tmo.exception; import org.springframework.http.HttpStatus;
public class AccesoNoAutorizadoException extends BusinessException { public AccesoNoAutorizadoException(String message){super("ACCESO_NO_AUTORIZADO",HttpStatus.FORBIDDEN,message);} }
