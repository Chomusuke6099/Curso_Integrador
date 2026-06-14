package pe.edu.utp.tmo.util;
import org.apache.commons.lang3.StringUtils;
import pe.edu.utp.tmo.exception.ValidacionNegocioException;

public final class ValidationUtils {
    private ValidationUtils(){}
    
    public static String observacion(String raw){
        String v=StringUtils.trimToEmpty(raw).replaceAll("[\\p{Cntrl}&&[^\\r\\n\\t]]"," ").replaceAll("\\s+"," ");
        if(StringUtils.length(v)<12) throw new ValidacionNegocioException("OBSERVACION_INVALIDA","La observación debe describir la gestión.");
        if(StringUtils.length(v)>1000) throw new ValidacionNegocioException("OBSERVACION_EXTENSA","La observación supera el límite.");
        return v;
    }
    
    public static String correo(String raw){
        String v=StringUtils.lowerCase(StringUtils.trimToEmpty(raw));
        if(StringUtils.isBlank(v)) throw new ValidacionNegocioException("CORREO_INVALIDO","El correo es obligatorio.");
        return v;
    }
}
