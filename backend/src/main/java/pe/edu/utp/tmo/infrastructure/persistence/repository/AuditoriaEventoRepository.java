package pe.edu.utp.tmo.infrastructure.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.tmo.model.entity.AuditoriaEvento;

public interface AuditoriaEventoRepository extends JpaRepository<AuditoriaEvento,Long>{ }