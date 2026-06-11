package pe.edu.utp.tmo.dto.request;
import jakarta.validation.constraints.*;
import pe.edu.utp.tmo.model.enums.TipoCaso;
public record TicketCreateRequest(@NotNull TipoCaso tipoCaso, @NotBlank @Size(min=3,max=160) String referencia) {}
