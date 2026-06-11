package pe.edu.utp.tmo.exception; import org.springframework.http.HttpStatus;
public class ValidacionNegocioException extends BusinessException { public ValidacionNegocioException(String code,String message){super(code,HttpStatus.BAD_REQUEST,message);} }
