package pe.edu.utp.tmo.dto.request;
import jakarta.validation.constraints.*;
public record TicketCloseRequest(@NotNull Long tipificacionId, @NotBlank @Size(min=12,max=1000) String observacion) {}
