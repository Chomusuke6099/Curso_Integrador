package pe.edu.utp.tmo.dto.response;
import java.time.Instant;

public record AuthResponse(String token,String rol,String nombre,String area,Instant expiresAt) {}