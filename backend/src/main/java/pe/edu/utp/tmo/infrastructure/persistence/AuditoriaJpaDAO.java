package pe.edu.utp.tmo.infrastructure.persistence;
import org.springframework.stereotype.Repository;
import pe.edu.utp.tmo.dao.AuditoriaDAO;
import pe.edu.utp.tmo.infrastructure.persistence.repository.AuditoriaEventoRepository;
import pe.edu.utp.tmo.model.entity.AuditoriaEvento;

@Repository

public class AuditoriaJpaDAO implements AuditoriaDAO{
    private final AuditoriaEventoRepository r;
    public AuditoriaJpaDAO(AuditoriaEventoRepository r){this.r=r;}
    
    public AuditoriaEvento registrarEvento(AuditoriaEvento e){
        return r.save(e);
    }
}