package pe.edu.utp.tmo.exception;
import jakarta.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;
import org.slf4j.*; import org.springframework.http.*; import org.springframework.security.access.AccessDeniedException; import org.springframework.security.authentication.BadCredentialsException; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*;
import pe.edu.utp.tmo.dto.response.ErrorResponse;
@RestControllerAdvice
public class GlobalExceptionHandler {
 private static final Logger log=LoggerFactory.getLogger(GlobalExceptionHandler.class);
 @ExceptionHandler(BusinessException.class) ResponseEntity<ErrorResponse> business(BusinessException ex,HttpServletRequest req){return build(ex.getStatus(),ex.getCode(),ex.getMessage(),req);}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException ex,HttpServletRequest req){String msg=ex.getBindingResult().getFieldErrors().stream().map(e->e.getField()+": "+e.getDefaultMessage()).collect(Collectors.joining("; "));return build(HttpStatus.BAD_REQUEST,"ENTRADA_INVALIDA",msg,req);}
 @ExceptionHandler(AccessDeniedException.class) ResponseEntity<ErrorResponse> denied(Exception ex,HttpServletRequest req){return build(HttpStatus.FORBIDDEN,"ACCESO_DENEGADO","No cuenta con permisos para esta operación.",req);}
 @ExceptionHandler(BadCredentialsException.class) ResponseEntity<ErrorResponse> credentials(Exception ex,HttpServletRequest req){return build(HttpStatus.UNAUTHORIZED,"CREDENCIALES_INVALIDAS","Credenciales inválidas.",req);}
 @ExceptionHandler(Exception.class) ResponseEntity<ErrorResponse> unexpected(Exception ex,HttpServletRequest req){log.error("Error no controlado traceId={}",MDC.get("traceId"),ex);return build(HttpStatus.INTERNAL_SERVER_ERROR,"ERROR_INTERNO","No fue posible procesar la solicitud.",req);}
 private ResponseEntity<ErrorResponse> build(HttpStatus status,String code,String msg,HttpServletRequest req){return ResponseEntity.status(status).body(new ErrorResponse(OffsetDateTime.now(),status.value(),code,msg,req.getRequestURI(),MDC.get("traceId")));}
}
