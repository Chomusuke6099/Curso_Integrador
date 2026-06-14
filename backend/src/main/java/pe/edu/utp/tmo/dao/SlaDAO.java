package pe.edu.utp.tmo.dao;
import java.util.Optional;
import pe.edu.utp.tmo.model.entity.ParametroSLA;
import pe.edu.utp.tmo.model.enums.TipoCaso;

public interface SlaDAO {
    Optional<ParametroSLA> buscarPorTipoCaso(TipoCaso tipoCaso);
    ParametroSLA guardarParametro(ParametroSLA parametro);
}