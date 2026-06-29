//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Controller;

import DAO.DAO_Jadwal;
import Model.varJadwal;
import View.FrmJadwal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

class MyTableModelJadwal extends DefaultTableModel {
    public MyTableModelJadwal(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

public class Controller_Jadwal {
    FrmJadwal form;
    DAO_Jadwal model;
    List<varJadwal> list;
    String[] header = {"ID Jadwal", "TA", "Semester", "Ruang", "Hari", "Waktu", "Dosen", "Matakuliah", "Kelompok"};
    Connection conn;
    public int selectedId = -1;
    
    // Variables to store selected calculated sessions
    private int calculatedSesiSelesai = -1;
    private int selectedSks = 0;
    
    public Controller_Jadwal(FrmJadwal form) {
        this.form = form;
        model = new DAO_Jadwal();
        conn = Koneksi.Database.KoneksiDB();
    }
    
    public void isiTabel() {
        list = model.getAll();
        MyTableModelJadwal tblModel = new MyTableModelJadwal(new Object[][]{}, header);
        for (varJadwal obj : list) {
            tblModel.addRow(new Object[]{
                obj.getIdJadwal(),
                obj.getTa(),
                obj.getSemester(),
                obj.getKodeRuang(),
                obj.getHari(),
                obj.getWaktu(),
                obj.getNamaDosen(),
                obj.getNamaMtk(),
                obj.getKelompok()
            });
        }
        form.getTblJadwal().setModel(tblModel);
        // Hide ID Jadwal column
        form.getTblJadwal().getColumnModel().getColumn(0).setMinWidth(0);
        form.getTblJadwal().getColumnModel().getColumn(0).setMaxWidth(0);
        form.getTblJadwal().getColumnModel().getColumn(0).setWidth(0);
    }
    
    public void loadCombos() {
        try {
            // Load TA
            PreparedStatement stTA = conn.prepareStatement("SELECT DISTINCT TA FROM periode WHERE TA != ''");
            ResultSet rsTA = stTA.executeQuery();
            form.getCbTA().removeAllItems();
            form.getCbTA().addItem("-Pilih-");
            while(rsTA.next()) {
                form.getCbTA().addItem(rsTA.getString("TA"));
            }
            
            // Load Semester
            PreparedStatement stSem = conn.prepareStatement("SELECT DISTINCT Semester FROM periode WHERE Semester != ''");
            ResultSet rsSem = stSem.executeQuery();
            form.getCbSemester().removeAllItems();
            form.getCbSemester().addItem("-Pilih-");
            while(rsSem.next()) {
                form.getCbSemester().addItem(rsSem.getString("Semester"));
            }
            
            // Load Ruang
            PreparedStatement stRuang = conn.prepareStatement("SELECT kode_ruang FROM ruang");
            ResultSet rsRuang = stRuang.executeQuery();
            form.getCbRuang().removeAllItems();
            form.getCbRuang().addItem("-Pilih-");
            while(rsRuang.next()) {
                form.getCbRuang().addItem(rsRuang.getString("kode_ruang"));
            }
            
            // Load Sesi
            PreparedStatement stSesi = conn.prepareStatement("SELECT kode_sesi FROM sesi");
            ResultSet rsSesi = stSesi.executeQuery();
            form.getCbSesi().removeAllItems();
            form.getCbSesi().addItem("-Pilih-");
            while(rsSesi.next()) {
                form.getCbSesi().addItem(rsSesi.getString("kode_sesi"));
            }
            
            // Load Dosen
            PreparedStatement stDosen = conn.prepareStatement("SELECT nip FROM dosen");
            ResultSet rsDosen = stDosen.executeQuery();
            form.getCbDosen().removeAllItems();
            form.getCbDosen().addItem("-Pilih-");
            while(rsDosen.next()) {
                form.getCbDosen().addItem(rsDosen.getString("nip"));
            }
            
            // Load Mtk
            PreparedStatement stMtk = conn.prepareStatement("SELECT KodeMTK FROM matakuliah");
            ResultSet rsMtk = stMtk.executeQuery();
            form.getCbMtk().removeAllItems();
            form.getCbMtk().addItem("-Pilih-");
            while(rsMtk.next()) {
                form.getCbMtk().addItem(rsMtk.getString("KodeMTK"));
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void fetchNamaDosen() {
        if(form.getCbDosen().getSelectedIndex() > 0) {
            String nip = form.getCbDosen().getSelectedItem().toString().trim();
            try {
                PreparedStatement st = conn.prepareStatement("SELECT nama_dosen FROM dosen WHERE nip = ?");
                st.setString(1, nip);
                ResultSet rs = st.executeQuery();
                if(rs.next()) {
                    form.getTxtNamaDosen().setText(rs.getString("nama_dosen"));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            form.getTxtNamaDosen().setText("");
        }
    }
    
    public void fetchNamaMtk() {
        selectedSks = 0;
        if(form.getCbMtk().getSelectedIndex() > 0) {
            String kode = form.getCbMtk().getSelectedItem().toString().trim();
            try {
                PreparedStatement st = conn.prepareStatement("SELECT NamaMTK, SKS FROM matakuliah WHERE KodeMTK = ?");
                st.setString(1, kode);
                ResultSet rs = st.executeQuery();
                if(rs.next()) {
                    form.getTxtNamaMtk().setText(rs.getString("NamaMTK"));
                    selectedSks = rs.getInt("SKS");
                    if(form.getTxtSks() != null) form.getTxtSks().setText(selectedSks + " SKS");
                } else {
                    if(form.getTxtSks() != null) form.getTxtSks().setText("");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            form.getTxtNamaMtk().setText("");
            if(form.getTxtSks() != null) form.getTxtSks().setText("");
        }
        calculateJamSelesai();
        realTimeValidation();
    }
    
    public void calculateJamSelesai() {
        calculatedSesiSelesai = -1;
        if (form.getCbSesi() != null && form.getTxtJamSelesai() != null && form.getTxtJamMulai() != null && form.getTxtSesiSelesai() != null) {
            if(form.getCbSesi().getSelectedIndex() > 0) {
                int sesiMulai = Integer.parseInt(form.getCbSesi().getSelectedItem().toString().trim());
                
                try {
                    PreparedStatement stM = conn.prepareStatement("SELECT jam_mulai FROM sesi WHERE kode_sesi = ?");
                    stM.setInt(1, sesiMulai);
                    ResultSet rsM = stM.executeQuery();
                    if(rsM.next()) {
                        form.getTxtJamMulai().setText(rsM.getString("jam_mulai"));
                    } else {
                        form.getTxtJamMulai().setText("");
                    }
                } catch(Exception e) {
                    form.getTxtJamMulai().setText("");
                    e.printStackTrace();
                }

                if (selectedSks > 0) {
                    try {
                        calculatedSesiSelesai = sesiMulai + selectedSks - 1;
                        
                        PreparedStatement st = conn.prepareStatement("SELECT jam_selesai FROM sesi WHERE kode_sesi = ?");
                        st.setInt(1, calculatedSesiSelesai);
                        ResultSet rs = st.executeQuery();
                        if(rs.next()) {
                            String jamSelesai = rs.getString("jam_selesai");
                            form.getTxtSesiSelesai().setText(String.valueOf(calculatedSesiSelesai));
                            form.getTxtJamSelesai().setText(jamSelesai);
                        } else {
                            form.getTxtSesiSelesai().setText("Err");
                            form.getTxtJamSelesai().setText("Err");
                            calculatedSesiSelesai = -1; // invalid
                        }
                    } catch(Exception e) {
                        form.getTxtSesiSelesai().setText("");
                        form.getTxtJamSelesai().setText("");
                        e.printStackTrace();
                    }
                } else {
                    form.getTxtSesiSelesai().setText("");
                    form.getTxtJamSelesai().setText("");
                }
            } else {
                form.getTxtJamMulai().setText("");
                form.getTxtSesiSelesai().setText("");
                form.getTxtJamSelesai().setText("");
            }
        }
        realTimeValidation();
    }
    
    public void isiField(int row) {
        selectedId = list.get(row).getIdJadwal();
        setComboItem(form.getCbTA(), list.get(row).getTa());
        setComboItem(form.getCbSemester(), list.get(row).getSemester());
        setComboItem(form.getCbRuang(), list.get(row).getKodeRuang());
        setComboItem(form.getCbHari(), list.get(row).getHari());
        setComboItem(form.getCbSesi(), String.valueOf(list.get(row).getKodeSesi()));
        setComboItem(form.getCbDosen(), list.get(row).getNip());
        setComboItem(form.getCbMtk(), list.get(row).getKodeMtk());
        form.getTxtKelompok().setText(list.get(row).getKelompok());
        
        form.getTxtNamaDosen().setText(list.get(row).getNamaDosen());
        form.getTxtNamaMtk().setText(list.get(row).getNamaMtk());
        
        selectedSks = list.get(row).getSks();
        if(form.getTxtSks() != null) form.getTxtSks().setText(selectedSks + " SKS");
        calculateJamSelesai();
        realTimeValidation();
    }

    private void setComboItem(javax.swing.JComboBox<String> combo, String value) {
        if (value == null) return;
        String valTrim = value.trim();
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).trim().equals(valTrim)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
    
    public void reset() {
        selectedId = -1;
        calculatedSesiSelesai = -1;
        selectedSks = 0;
        if(form.getCbTA().getItemCount() > 0) form.getCbTA().setSelectedIndex(0);
        if(form.getCbSemester().getItemCount() > 0) form.getCbSemester().setSelectedIndex(0);
        if(form.getCbRuang().getItemCount() > 0) form.getCbRuang().setSelectedIndex(0);
        if(form.getCbHari().getItemCount() > 0) form.getCbHari().setSelectedIndex(0);
        if(form.getCbSesi().getItemCount() > 0) form.getCbSesi().setSelectedIndex(0);
        if(form.getCbDosen().getItemCount() > 0) form.getCbDosen().setSelectedIndex(0);
        if(form.getCbMtk().getItemCount() > 0) form.getCbMtk().setSelectedIndex(0);
        form.getTxtNamaDosen().setText("");
        form.getTxtNamaMtk().setText("");
        form.getTxtKelompok().setText("");
        if(form.getTxtJamSelesai() != null) form.getTxtJamSelesai().setText("");
        if(form.getTxtJamMulai() != null) form.getTxtJamMulai().setText("");
        if(form.getTxtSesiSelesai() != null) form.getTxtSesiSelesai().setText("");
        if(form.getTxtSks() != null) form.getTxtSks().setText("");
        isiTabel();
    }
    
    
    public void realTimeValidation() {
        if(!isValidInput()) return;
        
        String ta = form.getCbTA().getSelectedItem().toString();
        String sem = form.getCbSemester().getSelectedItem().toString();
        String ruang = form.getCbRuang().getSelectedItem().toString().trim();
        String hari = form.getCbHari().getSelectedItem().toString();
        int sesi = Integer.parseInt(form.getCbSesi().getSelectedItem().toString().trim());
        String nip = form.getCbDosen().getSelectedItem().toString().trim();
        
        // Let checkConflict show the pane if there's a conflict
        checkConflict(ta, sem, ruang, hari, sesi, calculatedSesiSelesai, nip, selectedId);
    }
    
    public boolean isValidInput() {
        if(form.getCbTA().getSelectedIndex() <= 0) return false;
        if(form.getCbSemester().getSelectedIndex() <= 0) return false;
        if(form.getCbRuang().getSelectedIndex() <= 0) return false;
        if(form.getCbHari().getSelectedIndex() <= 0) return false;
        if(form.getCbSesi().getSelectedIndex() <= 0) return false;
        if(form.getCbDosen().getSelectedIndex() <= 0) return false;
        if(form.getCbMtk().getSelectedIndex() <= 0) return false;
        if(form.getTxtKelompok().getText().trim().isEmpty()) return false;
        if(calculatedSesiSelesai == -1) return false; // invalid session span
        return true;
    }
    
    // VALIDATION LOGIC FOR CONFLICTS
    public boolean checkConflict(String ta, String sem, String ruang, String hari, int sesiMulai, int sesiSelesai, String nip, int currentId) {
        try {
            // Check Room Conflict
            String qRuang = "SELECT id_jadwal, kode_mtk FROM jadwal WHERE TA=? AND Semester=? AND hari=? AND kode_ruang=? AND id_jadwal != ? AND ((kode_sesi <= ? AND kode_sesi_selesai >= ?) OR (kode_sesi <= ? AND kode_sesi_selesai >= ?) OR (kode_sesi >= ? AND kode_sesi_selesai <= ?))";
            PreparedStatement stR = conn.prepareStatement(qRuang);
            stR.setString(1, ta);
            stR.setString(2, sem);
            stR.setString(3, hari);
            stR.setString(4, ruang);
            stR.setInt(5, currentId);
            stR.setInt(6, sesiMulai);
            stR.setInt(7, sesiMulai);
            stR.setInt(8, sesiSelesai);
            stR.setInt(9, sesiSelesai);
            stR.setInt(10, sesiMulai);
            stR.setInt(11, sesiSelesai);
            ResultSet rsR = stR.executeQuery();
            if(rsR.next()) {
                JOptionPane.showMessageDialog(form, "BENTROK RUANG: Ruang " + ruang + " sudah dipakai oleh jadwal lain pada hari dan sesi tersebut!");
                return true;
            }
            
            // Check Dosen Conflict
            String qDosen = "SELECT id_jadwal, kode_ruang FROM jadwal WHERE TA=? AND Semester=? AND hari=? AND nip=? AND id_jadwal != ? AND ((kode_sesi <= ? AND kode_sesi_selesai >= ?) OR (kode_sesi <= ? AND kode_sesi_selesai >= ?) OR (kode_sesi >= ? AND kode_sesi_selesai <= ?))";
            PreparedStatement stD = conn.prepareStatement(qDosen);
            stD.setString(1, ta);
            stD.setString(2, sem);
            stD.setString(3, hari);
            stD.setString(4, nip);
            stD.setInt(5, currentId);
            stD.setInt(6, sesiMulai);
            stD.setInt(7, sesiMulai);
            stD.setInt(8, sesiSelesai);
            stD.setInt(9, sesiSelesai);
            stD.setInt(10, sesiMulai);
            stD.setInt(11, sesiSelesai);
            ResultSet rsD = stD.executeQuery();
            if(rsD.next()) {
                JOptionPane.showMessageDialog(form, "BENTROK DOSEN: Dosen tersebut sudah memiliki jadwal mengajar pada hari dan sesi tersebut (di ruang " + rsD.getString("kode_ruang") + ")!");
                return true;
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void insert() {
        if(!isValidInput()) {
            JOptionPane.showMessageDialog(form, "Data belum lengkap (ComboBox harus dipilih, Sesi harus valid, & Kelompok tidak boleh kosong)!");
            return;
        }
        
        String ta = form.getCbTA().getSelectedItem().toString();
        String sem = form.getCbSemester().getSelectedItem().toString();
        String ruang = form.getCbRuang().getSelectedItem().toString().trim();
        String hari = form.getCbHari().getSelectedItem().toString();
        int sesi = Integer.parseInt(form.getCbSesi().getSelectedItem().toString().trim());
        String nip = form.getCbDosen().getSelectedItem().toString().trim();
        String mtk = form.getCbMtk().getSelectedItem().toString().trim();
        String kel = form.getTxtKelompok().getText().trim();
        
        if (checkConflict(ta, sem, ruang, hari, sesi, calculatedSesiSelesai, nip, -1)) {
            return;
        }
        
        varJadwal obj = new varJadwal();
        obj.setTa(ta);
        obj.setSemester(sem);
        obj.setKodeRuang(ruang);
        obj.setHari(hari);
        obj.setKodeSesi(sesi);
        obj.setKodeSesiSelesai(calculatedSesiSelesai);
        obj.setNip(nip);
        obj.setKodeMtk(mtk);
        obj.setKelompok(kel);
        model.insert(obj);
        reset();
    }
    
    public void update() {
        if(selectedId == -1) {
            JOptionPane.showMessageDialog(form, "Pilih data jadwal yang akan diubah!");
            return;
        }
        if(!isValidInput()) {
            JOptionPane.showMessageDialog(form, "Data belum lengkap (ComboBox harus dipilih, Sesi harus valid, & Kelompok tidak boleh kosong)!");
            return;
        }
        
        String ta = form.getCbTA().getSelectedItem().toString();
        String sem = form.getCbSemester().getSelectedItem().toString();
        String ruang = form.getCbRuang().getSelectedItem().toString().trim();
        String hari = form.getCbHari().getSelectedItem().toString();
        int sesi = Integer.parseInt(form.getCbSesi().getSelectedItem().toString().trim());
        String nip = form.getCbDosen().getSelectedItem().toString().trim();
        String mtk = form.getCbMtk().getSelectedItem().toString().trim();
        String kel = form.getTxtKelompok().getText().trim();
        
        if (checkConflict(ta, sem, ruang, hari, sesi, calculatedSesiSelesai, nip, selectedId)) {
            return;
        }
        
        varJadwal obj = new varJadwal();
        obj.setIdJadwal(selectedId);
        obj.setTa(ta);
        obj.setSemester(sem);
        obj.setKodeRuang(ruang);
        obj.setHari(hari);
        obj.setKodeSesi(sesi);
        obj.setKodeSesiSelesai(calculatedSesiSelesai);
        obj.setNip(nip);
        obj.setKodeMtk(mtk);
        obj.setKelompok(kel);
        model.update(obj);
        reset();
    }
    
    public void delete() {
        if(selectedId != -1) {
            model.delete(String.valueOf(selectedId));
            reset();
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data jadwal yang akan dihapus!");
        }
    }
}
