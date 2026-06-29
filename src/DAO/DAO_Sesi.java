//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package DAO;

import Model.varSesi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAO_Sesi implements DAO_Interface<varSesi> {
    Connection conn;

    public DAO_Sesi() {
        conn = Koneksi.Database.KoneksiDB();
    }
    
    String INSERT = "insert into sesi(kode_sesi, jam_mulai, jam_selesai) values(?,?,?)";
    String UPDATE = "update sesi set jam_mulai=?, jam_selesai=? where kode_sesi=?";
    String DELETE = "delete from sesi where kode_sesi=?";
    String SelectUntukGetAll = "select * from sesi";
    String SelectUntukGetCari = "select * from sesi where kode_sesi like ?";
    
    @Override
    public void insert(varSesi Object) {
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, String.valueOf(Object.getKodeSesi()));
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan!");
            } else {
                st = conn.prepareStatement(INSERT);
                st.setInt(1, Object.getKodeSesi());
                st.setString(2, Object.getJamMulai());
                st.setString(3, Object.getJamSelesai());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru berhasil disimpan");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(varSesi Object) {
        try {
            PreparedStatement st = conn.prepareStatement(UPDATE);
            st.setString(1, Object.getJamMulai());
            st.setString(2, Object.getJamSelesai());
            st.setInt(3, Object.getKodeSesi());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        try {
            PreparedStatement st = conn.prepareStatement(DELETE);
            st.setInt(1, Integer.parseInt(key));
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<varSesi> getAll() {
        List<varSesi> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetAll);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                varSesi obj = new varSesi();
                obj.setKodeSesi(rs.getInt("kode_sesi"));
                obj.setJamMulai(rs.getString("jam_mulai"));
                obj.setJamSelesai(rs.getString("jam_selesai"));
                list.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varSesi> getCari(String key) {
        List<varSesi> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                varSesi obj = new varSesi();
                obj.setKodeSesi(rs.getInt("kode_sesi"));
                obj.setJamMulai(rs.getString("jam_mulai"));
                obj.setJamSelesai(rs.getString("jam_selesai"));
                list.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
