package pe.edu.utp.tmo.infrastructure.persistence.repository;
import java.time.LocalDateTime; import java.util.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import pe.edu.utp.tmo.model.entity.RegistroTMO;
public interface RegistroTMORepository extends JpaRepository<RegistroTMO,Long>{
 Optional<RegistroTMO> findByCasoId(Long casoId);
 List<RegistroTMO> findByFinBetween(LocalDateTime inicio,LocalDateTime fin);
 @Query("select avg(r.tmoSegundos) from RegistroTMO r where r.fin between :inicio and :fin")
 Double promedioPorPeriodo(@Param("inicio") LocalDateTime inicio,@Param("fin") LocalDateTime fin);
}
