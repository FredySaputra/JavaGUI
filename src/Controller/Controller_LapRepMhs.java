//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Controller;

import java.io.File;
import java.sql.Connection;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


class MyTableModelLapRepMhs extends javax.swing.table.DefaultTableModel {
    public MyTableModelLapRepMhs(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

public class Controller_LapRepMhs {
    
    private void compileReport(String jrxmlPath, String jasperPath) throws Exception {
        File jasperFile = new File(jasperPath);
        if (jasperFile.exists()) {
            jasperFile.delete(); // Hapus file lama agar dicompile ulang dengan perubahan terbaru
        }
        JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
    }

    public void cetak_preview() {
        try {
            Connection conn = Koneksi.Database.KoneksiDB();
            String jrxmlPath = "src/Report/LapRepMhs.jrxml";
            String jasperPath = "src/Report/LapRepMhs.jasper";
            
            compileReport(jrxmlPath, jasperPath);
            
            JasperReport jp = (JasperReport) JRLoader.loadObject(new File(jasperPath));
            JasperPrint print = JasperFillManager.fillReport(jp, null, conn);
            JasperViewer.viewReport(print, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak!! " + ex.getMessage(),
                    "Cetak Data", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cetak_excel() {
        try {
            Connection conn = Koneksi.Database.KoneksiDB();
            String jrxmlPath = "src/Report/LapRepMhs.jrxml";
            String jasperPath = "src/Report/LapRepMhs.jasper";
            
            compileReport(jrxmlPath, jasperPath);
            
            File xlsx = new File("D:/LapRepMhs.xlsx");
            JasperPrint print = JasperFillManager.fillReport(jasperPath, null, conn);
            
            JRXlsxExporter xlsxExporter = new JRXlsxExporter();
            xlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            xlsxExporter.setParameter(JRExporterParameter.OUTPUT_FILE, xlsx);
            xlsxExporter.exportReport();
            
            JOptionPane.showMessageDialog(null, "Cek file pada drive D:/LapRepMhs.xlsx");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak!! " + ex.getMessage(),
                    "Cetak Data", JOptionPane.ERROR_MESSAGE);
        }
    }
}
