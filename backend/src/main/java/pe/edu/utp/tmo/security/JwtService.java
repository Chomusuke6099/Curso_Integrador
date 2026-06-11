package pe.edu.utp.tmo.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import java.nio.charset.StandardCharsets; import java.time.Instant; import java.util.Date; import javax.crypto.SecretKey; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import pe.edu.utp.tmo.model.entity.Usuario;
@Service public class JwtService {
 private final String secret; private final long minutes;
 public JwtService(@Value("${tmo.security.jwt-secret}") String secret,@Value("${tmo.security.jwt-expiration-minutes}") long minutes){ if(secret==null||secret.getBytes(StandardCharsets.UTF_8).length<32) throw new IllegalStateException("La clave JWT debe tener al menos 32 caracteres."); this.secret=secret; this.minutes=minutes; }
 public String generarToken(Usuario u){Instant now=Instant.now(), exp=now.plusSeconds(minutes*60); return Jwts.builder().subject(u.getCorreo()).claim("uid",u.getId()).claim("rol",u.getRol().name()).claim("area",u.getArea()).issuedAt(Date.from(now)).expiration(Date.from(exp)).signWith(key()).compact();}
 public String correo(String token){return claims(token).getSubject();} public boolean valido(String token,UserPrincipal p){return correo(token).equalsIgnoreCase(p.getUsername()) && claims(token).getExpiration().after(new Date());} public Instant expira(){return Instant.now().plusSeconds(minutes*60);}
 private Claims claims(String t){return Jwts.parser().verifyWith(key()).build().parseSignedClaims(t).getPayload();} private SecretKey key(){return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
}
