package pe.edu.utp.tmo.util;
import static org.junit.jupiter.api.Assertions.*; import java.time.LocalDateTime; import org.junit.jupiter.api.Test; import pe.edu.utp.tmo.exception.ValidacionNegocioException;
class TmoCalculatorTest {
 private final TmoCalculator calc=new TmoCalculator();
 @Test void debeCalcularSegundos(){assertEquals(750,calc.calcularSegundos(LocalDateTime.of(2026,6,10,8,0),LocalDateTime.of(2026,6,10,8,12,30)));}
 @Test void noPermiteTmoNegativo(){assertThrows(ValidacionNegocioException.class,()->calc.calcularSegundos(LocalDateTime.of(2026,6,10,8,10),LocalDateTime.of(2026,6,10,8,0)));}
}
