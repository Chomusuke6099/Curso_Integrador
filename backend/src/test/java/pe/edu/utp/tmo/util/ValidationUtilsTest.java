package pe.edu.utp.tmo.util;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pe.edu.utp.tmo.exception.ValidacionNegocioException;

class ValidationUtilsTest {
 @Test void
    limpiaObservacion(){
        assertEquals("Gestión realizada correctamente",ValidationUtils.observacion("  Gestión   realizada correctamente "));
    }

    @Test void
    rechazaObservacionCorta(){
        assertThrows(ValidacionNegocioException.class,()->ValidationUtils.observacion("ok"));
    }
}
