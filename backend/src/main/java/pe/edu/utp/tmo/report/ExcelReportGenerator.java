package pe.edu.utp.tmo.report;
import java.io.*;
import java.util.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import pe.edu.utp.tmo.model.entity.RegistroTMO;

@Component
public class ExcelReportGenerator {
 public byte[] generarReporteTmo(List<RegistroTMO> registros){
    Workbook wb=new XSSFWorkbook();
    
    try{Sheet s=wb.createSheet("TMO");
    Row h=s.createRow(0); String[] hs={"Código","Analista","Tipo","Estado","Tipificación","Inicio","Fin","TMO segundos"};
    
    for(int i=0;i<hs.length;i++){
        h.createCell(i).setCellValue(hs[i]);
    }
    
    int row=1;
    for(RegistroTMO r:registros){
        Row x=s.createRow(row++);
        x.createCell(0).setCellValue(safe(r.getCaso().getCodigo()));
        x.createCell(1).setCellValue(safe(r.getCaso().getAnalista().getNombres()));
        x.createCell(2).setCellValue(safe(r.getCaso().getTipoCaso().name()));
        x.createCell(3).setCellValue(safe(r.getCaso().getEstado().name()));
        x.createCell(4).setCellValue(safe(r.getCaso().getTipificacion()==null?"":r.getCaso().getTipificacion().getNombre()));
        x.createCell(5).setCellValue(r.getInicio().toString());
        x.createCell(6).setCellValue(r.getFin().toString());
        x.createCell(7).setCellValue(r.getTmoSegundos());
    }
    ByteArrayOutputStream out=new ByteArrayOutputStream();
    wb.write(out);
    return out.toByteArray();
} catch(IOException e){
    throw new IllegalStateException("No fue posible generar XLSX.",e);
} finally{
    IOUtils.closeQuietly(wb);
}
}
private String safe(String v){
    String n=StringUtils.trimToEmpty(v);
    return (n.startsWith("=")||n.startsWith("+")||n.startsWith("-")||n.startsWith("@"))?"'"+n:n;
}
}
