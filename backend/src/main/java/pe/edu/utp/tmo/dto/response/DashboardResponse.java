package pe.edu.utp.tmo.dto.response;
import java.util.List;

public record DashboardResponse(long abiertos,long cerrados,double tmoPromedioSegundos,long alertasSla,List<KpiPorTipoResponse> porTipo) {}