package pe.edu.utp.tmo.dao;
import java.time.LocalDateTime;
import java.util.*;
import pe.edu.utp.tmo.model.entity.Caso;
import pe.edu.utp.tmo.model.enums.EstadoCaso;

public interface CasoDAO {
    Caso guardar(Caso caso);
    Optional<Caso> buscarPorId(Long id);
    List<Caso> listarPorAnalista(Long idAnalista);
    List<Caso> listarPorEstado(EstadoCaso estado);
    List<Caso> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fin);
}
