package Controller;

import DAO.DAO_KRSS;
import Model.varKRSS;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Controller_LapRepKRSS {
    
    DAO_KRSS model_internal;

    public Controller_LapRepKRSS() {
        model_internal = new DAO_KRSS();
    }
    
    public void isicomboTahunAjaran(JComboBox cboTA){
        cboTA.removeAllItems();
        cboTA.addItem("-Pilih-");
        for(varKRSS b: model_internal.isicomboTA()){
            cboTA.addItem(b.getVTA());
        }
    }
    
    public void isicomboSemester(JComboBox cboSemester){
        cboSemester.removeAllItems();
        cboSemester.addItem("-Pilih-");
        for(varKRSS b: model_internal.isicomboSMT()){
            cboSemester.addItem(b.getVSemester());
        }
    }
    
    public void tampilNama(javax.swing.JTextField txtNIM, javax.swing.JTextField txtNama) {
        String nim = txtNIM.getText();
        if (!nim.isEmpty()) {
            java.util.List<varKRSS> list = model_internal.getNmMhs(nim);
            if (!list.isEmpty()) {
                txtNama.setText(list.get(0).getVNama());
            } else {
                txtNama.setText("NIM Tidak Terdaftar");
            }
        } else {
            txtNama.setText("");
        }
    }

    private void compileReport(String jrxmlPath, String jasperPath) throws Exception {
        File jasperFile = new File(jasperPath);
        if (jasperFile.exists()) {
            jasperFile.delete(); // Hapus file lama agar dicompile ulang dengan perubahan terbaru
        }
        JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
    }

    public void cetak_preview(String nim, String ta, String semester) {
        try {
            Connection conn = Koneksi.Database.KoneksiDB();
            String jrxmlPath = "src/Report/LapRepKRSS.jrxml";
            String jasperPath = "src/Report/LapRepKRSS.jasper";
            
            compileReport(jrxmlPath, jasperPath);
            
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("pNIM", nim);
            parameters.put("pTA", ta);
            parameters.put("pSemester", semester);
            
            JasperReport jp = (JasperReport) JRLoader.loadObject(new File(jasperPath));
            JasperPrint print = JasperFillManager.fillReport(jp, parameters, conn);
            JasperViewer.viewReport(print, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak!! " + ex.getMessage(),
                    "Cetak Data", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public void cetak_excel(String nim, String ta, String semester) {
        try {
            Connection conn = Koneksi.Database.KoneksiDB();
            String jrxmlPath = "src/Report/LapRepKRSS.jrxml";
            String jasperPath = "src/Report/LapRepKRSS.jasper";
            
            compileReport(jrxmlPath, jasperPath);
            
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("pNIM", nim);
            parameters.put("pTA", ta);
            parameters.put("pSemester", semester);
            
            File xlsx = new File("D:/LapRepKRSS_" + nim + ".xlsx");
            JasperPrint print = JasperFillManager.fillReport(jasperPath, parameters, conn);
            
            JRXlsxExporter xlsxExporter = new JRXlsxExporter();
            xlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            xlsxExporter.setParameter(JRExporterParameter.OUTPUT_FILE, xlsx);
            xlsxExporter.exportReport();
            
            JOptionPane.showMessageDialog(null, "Cek file pada drive D:/LapRepKRSS_" + nim + ".xlsx");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak!! " + ex.getMessage(),
                    "Cetak Data", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
