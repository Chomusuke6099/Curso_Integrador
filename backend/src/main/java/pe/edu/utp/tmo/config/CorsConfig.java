package pe.edu.utp.tmo.config;
import java.util.*; import org.springframework.beans.factory.annotation.Value; import org.springframework.context.annotation.*; import org.springframework.web.cors.*;
@Configuration public class CorsConfig {
 @Bean CorsConfigurationSource corsConfigurationSource(@Value("${tmo.security.cors-origins}") String origins){
  CorsConfiguration c=new CorsConfiguration(); c.setAllowedOrigins(Arrays.stream(origins.split(",")).map(String::trim).filter(s->!s.isBlank()).toList());
  c.setAllowedMethods(List.of("GET","POST","PUT","OPTIONS")); c.setAllowedHeaders(List.of("Authorization","Content-Type","X-Trace-Id")); c.setExposedHeaders(List.of("Content-Disposition","X-Trace-Id")); c.setAllowCredentials(false);
  UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource(); s.registerCorsConfiguration("/**",c); return s;
 }
}
