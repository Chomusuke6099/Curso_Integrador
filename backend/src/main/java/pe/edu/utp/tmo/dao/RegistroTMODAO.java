package pe.edu.utp.tmo.dao;
import java.time.LocalDateTime; import java.util.*; import pe.edu.utp.tmo.model.entity.RegistroTMO;
public interface RegistroTMODAO { RegistroTMO guardar(RegistroTMO r); Optional<RegistroTMO> buscarPorCaso(Long casoId); Double promedioPorPeriodo(LocalDateTime inicio, LocalDateTime fin); List<RegistroTMO> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fin); }
