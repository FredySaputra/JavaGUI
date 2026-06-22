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
        if(form.getCbMtk().getSelectedIndex() > 0) {
            String kode = form.getCbMtk().getSelectedItem().toString().trim();
            try {
                PreparedStatement st = conn.prepareStatement("SELECT NamaMTK FROM matakuliah WHERE KodeMTK = ?");
                st.setString(1, kode);
                ResultSet rs = st.executeQuery();
                if(rs.next()) {
                    form.getTxtNamaMtk().setText(rs.getString("NamaMTK"));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            form.getTxtNamaMtk().setText("");
        }
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
        
        // Ensure names are immediately populated without waiting for ItemListener
        form.getTxtNamaDosen().setText(list.get(row).getNamaDosen());
        form.getTxtNamaMtk().setText(list.get(row).getNamaMtk());
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
        isiTabel();
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
        return true;
    }
    
    public void insert() {
        if(!isValidInput()) {
            JOptionPane.showMessageDialog(form, "Data belum lengkap (ComboBox harus dipilih & Kelompok tidak boleh kosong)!");
            return;
        }
        varJadwal obj = new varJadwal();
        obj.setTa(form.getCbTA().getSelectedItem().toString());
        obj.setSemester(form.getCbSemester().getSelectedItem().toString());
        obj.setKodeRuang(form.getCbRuang().getSelectedItem().toString().trim());
        obj.setHari(form.getCbHari().getSelectedItem().toString());
        obj.setKodeSesi(Integer.parseInt(form.getCbSesi().getSelectedItem().toString().trim()));
        obj.setNip(form.getCbDosen().getSelectedItem().toString().trim());
        obj.setKodeMtk(form.getCbMtk().getSelectedItem().toString().trim());
        obj.setKelompok(form.getTxtKelompok().getText().trim());
        model.insert(obj);
        reset();
    }
    
    public void update() {
        if(selectedId == -1) {
            JOptionPane.showMessageDialog(form, "Pilih data jadwal yang akan diubah!");
            return;
        }
        if(!isValidInput()) {
            JOptionPane.showMessageDialog(form, "Data belum lengkap (ComboBox harus dipilih & Kelompok tidak boleh kosong)!");
            return;
        }
        varJadwal obj = new varJadwal();
        obj.setIdJadwal(selectedId);
        obj.setTa(form.getCbTA().getSelectedItem().toString());
        obj.setSemester(form.getCbSemester().getSelectedItem().toString());
        obj.setKodeRuang(form.getCbRuang().getSelectedItem().toString().trim());
        obj.setHari(form.getCbHari().getSelectedItem().toString());
        obj.setKodeSesi(Integer.parseInt(form.getCbSesi().getSelectedItem().toString().trim()));
        obj.setNip(form.getCbDosen().getSelectedItem().toString().trim());
        obj.setKodeMtk(form.getCbMtk().getSelectedItem().toString().trim());
        obj.setKelompok(form.getTxtKelompok().getText().trim());
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
