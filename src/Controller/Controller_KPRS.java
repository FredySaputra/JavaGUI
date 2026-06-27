package Controller;

import DAO.DAO_KPRS;
import Model.varDetilKPRS;
import View.FrmKPRS;
import View.KPRSTableModel;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controller_KPRS {
    FrmKPRS form;
    DAO_KPRS model_internal;
    List<varDetilKPRS> list;
    
    public Controller_KPRS(FrmKPRS form){
        this.form = form;
        model_internal = new DAO_KPRS();
        form.getTblKPRS().setShowGrid(true);
        form.getTblKPRS().setShowVerticalLines(true);
        form.getTblKPRS().setGridColor(Color.blue);
    }
    
    public void validasiNimEnter() {
        String nim = form.getTxtNim().getText().trim();
        String ta = ("" + form.getCmbTA().getSelectedItem()).trim();
        String semester = ("" + form.getCmbSemester().getSelectedItem()).trim();
        
        if (ta.equals("Pilih") || ta.equals("-Pilih-") || semester.equals("Pilih") || semester.equals("-Pilih-") || nim.isEmpty()) {
            JOptionPane.showMessageDialog(form, "Pilih TA, Semester, dan masukkan NIM.");
            return;
        }

        String namaMhs = model_internal.getNamaMahasiswa(nim);
        if (namaMhs == null) {
            JOptionPane.showMessageDialog(form, "Mahasiswa tidak terdaftar");
            reset();
            return;
        }
        form.getTxtNamaMhs().setText(namaMhs);

        boolean kprsExists = model_internal.checkKprsExists(ta, semester, nim);
        boolean detilKprsExists = model_internal.checkDetilKprsExists(ta, semester, nim);

        if (kprsExists && !detilKprsExists) {
            model_internal.deleteKprsHeader(ta, semester, nim);
            kprsExists = false;
        }

        if (kprsExists) {
            isiTabel();
        } else {
            if (model_internal.checkKrsExists(ta, semester, nim)) {
                model_internal.copyKrsToKprs(ta, semester, nim);
                isiTabel();
            } else {
                JOptionPane.showMessageDialog(form, "Data KRS tidak ditemukan. Mahasiswa belum mengisi KRS.");
                reset();
                return;
            }
        }
        isicomboKodeMtk();
    }
    
    public void isicomboKodeMtk() {
        String ta = ("" + form.getCmbTA().getSelectedItem()).trim();
        String semester = ("" + form.getCmbSemester().getSelectedItem()).trim();
        String nim = form.getTxtNim().getText().trim();
        form.getCmbKodeMtk().removeAllItems();
        form.getCmbKodeMtk().addItem("-Pilih-");
        // get KodeMTK from detil_kprs for this mahasiswa
        try {
            java.sql.Connection conn = Koneksi.Database.KoneksiDB();
            java.sql.PreparedStatement st = conn.prepareStatement("SELECT KodeMTK FROM detil_kprs WHERE TA=? AND Semester=? AND NIM=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()) {
                form.getCmbKodeMtk().addItem(rs.getString("KodeMTK"));
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void isiDetailMtk() {
        String kdMtk = ("" + form.getCmbKodeMtk().getSelectedItem()).trim();
        if(kdMtk.equals("-Pilih-") || kdMtk.isEmpty()) {
            form.getTxtNamaMtk().setText("");
            form.getTxtSks().setText("");
            form.getTxtPrasyarat().setText("");
            form.getCmbKelompok().removeAllItems();
            return;
        }
        // Fetch Mtk Details
        try {
            java.sql.Connection conn = Koneksi.Database.KoneksiDB();
            java.sql.PreparedStatement st = conn.prepareStatement("SELECT NamaMTK, SKS, KodePrasyarat FROM matakuliah WHERE KodeMTK=?");
            st.setString(1, kdMtk);
            java.sql.ResultSet rs = st.executeQuery();
            if(rs.next()) {
                form.getTxtNamaMtk().setText(rs.getString("NamaMTK"));
                form.getTxtSks().setText(rs.getString("SKS"));
                form.getTxtPrasyarat().setText(rs.getString("KodePrasyarat") != null ? rs.getString("KodePrasyarat") : "-");
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        isicomboKelompok(kdMtk);
    }
    
    public void isicomboKelompok(String kdMtk) {
        String ta = ("" + form.getCmbTA().getSelectedItem()).trim();
        String semester = ("" + form.getCmbSemester().getSelectedItem()).trim();
        form.getCmbKelompok().removeAllItems();
        form.getCmbKelompok().addItem("-Pilih-");
        for(String klp: model_internal.isicomboKelompok(ta, semester, kdMtk)) {
            form.getCmbKelompok().addItem(klp);
        }
    }

    public void isicomboTahunAjaran(){
        form.getCmbTA().removeAllItems();
        form.getCmbTA().addItem("-Pilih-");
        for(String ta: model_internal.isicomboTA()){
            form.getCmbTA().addItem(ta);
        }
    }
    
    public void isicomboSemester(){
        form.getCmbSemester().removeAllItems();
        form.getCmbSemester().addItem("-Pilih-");
        for(String smt: model_internal.isicomboSMT()){
            form.getCmbSemester().addItem(smt);
        }
    }

    public void isiTabel(){
        String ta = (""+form.getCmbTA().getSelectedItem()).trim();
        String semester = (""+form.getCmbSemester().getSelectedItem()).trim();
        String nim = form.getTxtNim().getText().trim();
        list = model_internal.getAllDetil(ta, semester, nim);
        
        KPRSTableModel tblModel = new KPRSTableModel();
        
        for(varDetilKPRS obj : list){
            Object[] data = new Object[5];
            data[0] = obj.getKodeMTK();
            data[1] = obj.getNamaMTK();
            data[2] = obj.getSKS();
            
            // fetch Prasyarat
            String prasyarat = "-";
            try {
                java.sql.Connection conn = Koneksi.Database.KoneksiDB();
                java.sql.PreparedStatement st = conn.prepareStatement("SELECT KodePrasyarat FROM matakuliah WHERE KodeMTK=?");
                st.setString(1, obj.getKodeMTK());
                java.sql.ResultSet rs = st.executeQuery();
                if(rs.next()) {
                    prasyarat = rs.getString("KodePrasyarat");
                    if(prasyarat == null) prasyarat = "-";
                }
                rs.close();
                st.close();
            } catch(Exception e) {}

            data[3] = prasyarat;
            data[4] = obj.getKelompok();
            tblModel.addRow(data);
        }
        form.getTblKPRS().setModel(tblModel);
    }
    
    public void reset(){
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        isicomboTahunAjaran();
        isicomboSemester();
        if(form.getCmbTA().getItemCount() > 0) form.getCmbTA().setSelectedIndex(0);
        if(form.getCmbSemester().getItemCount() > 0) form.getCmbSemester().setSelectedIndex(0);
        form.getTxtTglKPRS().setText(String.valueOf(tgl.format(new Date())));
        form.getTxtNim().setText("");
        form.getTxtNamaMhs().setText("");
        
        resetDetil();
        
        form.getTxtTglKPRS().setEditable(false);
        form.getTxtNim().setEditable(true);
        form.getTxtNim().requestFocus();
    }
    
    public void resetDetil(){
        if(form.getCmbKodeMtk().getItemCount() > 0) form.getCmbKodeMtk().setSelectedIndex(0);
        form.getTxtNamaMtk().setText("");
        form.getTxtSks().setText("");
        form.getTxtPrasyarat().setText("");
        if(form.getCmbKelompok().getItemCount() > 0) form.getCmbKelompok().setSelectedIndex(0);
        form.getTxtNim().requestFocus();
    }
    
    public void isiField(int row){
        if (row >= 0 && row < list.size()) {
            String kdMtk = list.get(row).getKodeMTK();
            form.getCmbKodeMtk().setSelectedItem(kdMtk);
            
            form.getTxtNamaMtk().setText(list.get(row).getNamaMTK());
            form.getTxtSks().setText(""+list.get(row).getSKS());
            form.getTxtPrasyarat().setText(form.getTblKPRS().getValueAt(row, 3).toString());
            
            isicomboKelompok(kdMtk);
            form.getCmbKelompok().setSelectedItem(list.get(row).getKelompok());
        }
    }
    
    // tambah() method removed as requested

    public void ubahKelompok() {
        String nim = form.getTxtNim().getText().trim();
        String ta = ("" + form.getCmbTA().getSelectedItem()).trim();
        String semester = ("" + form.getCmbSemester().getSelectedItem()).trim();
        String kdMtk = ("" + form.getCmbKodeMtk().getSelectedItem()).trim();
        String kelompokBaru = ("" + form.getCmbKelompok().getSelectedItem()).trim();
        
        if (kdMtk.equals("-Pilih-") || kdMtk.isEmpty() || kelompokBaru.equals("-Pilih-") || kelompokBaru.isEmpty()) {
            JOptionPane.showMessageDialog(form, "Pilih Kode Matakuliah dan Kelompok yang baru.");
            return;
        }

        Object[] jInfo = model_internal.getJadwalInfo(ta, semester, kdMtk, kelompokBaru);
        if (jInfo == null) {
            JOptionPane.showMessageDialog(form, "Jadwal untuk kelompok tersebut tidak ditemukan.");
            return;
        }
        
        String hariBaru = (String) jInfo[0];
        int sesiMulaiBaru = (Integer) jInfo[1];
        int sesiSelesaiBaru = (Integer) jInfo[2];

        if (model_internal.cekBentrokJadwal(nim, ta, semester, hariBaru, sesiMulaiBaru, sesiSelesaiBaru, kdMtk)) {
            String[] info = model_internal.getBentrokInfo(nim, ta, semester, hariBaru, sesiMulaiBaru, sesiSelesaiBaru, kdMtk);
            if (info != null) {
                JOptionPane.showMessageDialog(form, "Bentrok dengan mata kuliah: " + info[0] + " pada hari " + info[1]);
            } else {
                JOptionPane.showMessageDialog(form, "Jadwal bentrok dengan mata kuliah lain.");
            }
            return;
        }

        varDetilKPRS obj = new varDetilKPRS();
        obj.setTA(ta);
        obj.setSemester(semester);
        obj.setNIM(nim);
        obj.setKodeMTK(kdMtk);
        obj.setKelompok(kelompokBaru);

        model_internal.update(obj);
        isiTabel();
        resetDetil();
        JOptionPane.showMessageDialog(form, "Data berhasil diubah");
    }
    
    public void hapus() {
        String nim = form.getTxtNim().getText().trim();
        String ta = ("" + form.getCmbTA().getSelectedItem()).trim();
        String semester = ("" + form.getCmbSemester().getSelectedItem()).trim();
        String kdMtk = ("" + form.getCmbKodeMtk().getSelectedItem()).trim();
        
        if (kdMtk.equals("-Pilih-") || kdMtk.isEmpty()) {
            JOptionPane.showMessageDialog(form, "Pilih mata kuliah yang kelompoknya akan dihapus.");
            return;
        }
        
        String kelompokSaatIni = ("" + form.getCmbKelompok().getSelectedItem()).trim();
        if (kelompokSaatIni.equals("-Pilih-") || kelompokSaatIni.isEmpty()) {
            JOptionPane.showMessageDialog(form, "Mata kuliah ini belum memiliki kelompok. Tidak ada yang perlu dihapus.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(form, "Yakin ingin menghapus kelompok pada mata kuliah ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        varDetilKPRS obj = new varDetilKPRS();
        obj.setTA(ta);
        obj.setSemester(semester);
        obj.setNIM(nim);
        obj.setKodeMTK(kdMtk);
        obj.setKelompok(""); // Hanya mengosongkan kelompok

        model_internal.update(obj);
        isiTabel();
        resetDetil();
        JOptionPane.showMessageDialog(form, "Kelompok berhasil dihapus dari mata kuliah tersebut.");
    }
}
