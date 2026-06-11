package pe.edu.utp.tmo.security;
import java.util.*; import org.springframework.security.core.*; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.userdetails.UserDetails; import pe.edu.utp.tmo.model.entity.Usuario;
public class UserPrincipal implements UserDetails {
 private final Usuario u; public UserPrincipal(Usuario u){this.u=u;} public Usuario getUsuario(){return u;}
 public Collection<? extends GrantedAuthority> getAuthorities(){return List.of(new SimpleGrantedAuthority("ROLE_"+u.getRol().name()));}
 public String getPassword(){return u.getPasswordHash();} public String getUsername(){return u.getCorreo();} public boolean isAccountNonExpired(){return true;} public boolean isAccountNonLocked(){return true;} public boolean isCredentialsNonExpired(){return true;} public boolean isEnabled(){return u.isActivo();}
}
