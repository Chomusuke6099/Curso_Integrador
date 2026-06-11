package pe.edu.utp.tmo.dto.request;
import jakarta.validation.constraints.*;
import pe.edu.utp.tmo.model.enums.RolUsuario;
public record UserCreateRequest(@NotBlank @Size(max=120) String nombres,@NotBlank @Email @Size(max=180) String correo,@NotBlank @Size(min=12,max=100) String password,@NotNull RolUsuario rol,@NotBlank @Size(max=80) String area) {}
