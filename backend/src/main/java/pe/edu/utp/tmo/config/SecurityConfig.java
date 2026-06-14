package pe.edu.utp.tmo.config;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.edu.utp.tmo.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity

public class SecurityConfig {
 @Bean SecurityFilterChain filterChain(HttpSecurity http,JwtAuthenticationFilter jwt) throws Exception {
  http.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/actuator/health","/actuator/info").permitAll()
   .requestMatchers("/api/admin/**").hasRole("ADMINISTRADOR")
   .requestMatchers("/api/dashboard/**").hasAnyRole("SUPERVISOR","GERENTE","ADMINISTRADOR")
   .requestMatchers(HttpMethod.GET,"/api/reports/**").hasAnyRole("SUPERVISOR","GERENTE","ADMINISTRADOR")
   .requestMatchers("/api/tickets/**","/api/catalogs/**").authenticated().anyRequest().authenticated())
   .addFilterBefore(jwt,UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }
 
 @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
