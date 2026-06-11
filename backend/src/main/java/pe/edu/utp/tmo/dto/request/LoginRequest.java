package pe.edu.utp.tmo.dto.request;
import jakarta.validation.constraints.*;
public record LoginRequest(@NotBlank @Email @Size(max=180) String correo, @NotBlank @Size(min=8,max=100) String password) {}
