package pe.edu.utp.tmo.report;
import static org.junit.jupiter.api.Assertions.*; import java.util.List; import org.junit.jupiter.api.Test;
class ExcelReportGeneratorTest { @Test void generaXlsxVacio(){assertTrue(new ExcelReportGenerator().generarReporteTmo(List.of()).length>0);} }
