package pe.edu.utp.tmo.infrastructure.persistence.repository;
import java.time.LocalDateTime; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository; import pe.edu.utp.tmo.model.entity.Caso; import pe.edu.utp.tmo.model.enums.EstadoCaso;
public interface CasoRepository extends JpaRepository<Caso,Long>{
 List<Caso> findByAnalistaIdOrderByFechaInicioDesc(Long analistaId);
 List<Caso> findByEstado(EstadoCaso estado);
 List<Caso> findByFechaInicioBetweenOrderByFechaInicioDesc(LocalDateTime inicio,LocalDateTime fin);
}
