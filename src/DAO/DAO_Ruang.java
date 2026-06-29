//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package DAO;

import Model.varRuang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAO_Ruang implements DAO_Interface<varRuang> {
    Connection conn;

    public DAO_Ruang() {
        conn = Koneksi.Database.KoneksiDB();
    }
    
    String INSERT = "insert into ruang(kode_ruang, keterangan) values(?,?)";
    String UPDATE = "update ruang set keterangan=? where kode_ruang=?";
    String DELETE = "delete from ruang where kode_ruang=?";
    String SelectUntukGetAll = "select * from ruang";
    String SelectUntukGetCari = "select * from ruang where kode_ruang like ?";
    
    @Override
    public void insert(varRuang Object) {
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, Object.getKodeRuang());
            ResultSet rs = st.executeQuery();
            if(rs.next() && Object.getKodeRuang().equals(rs.getString("kode_ruang"))) {
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan!");
            } else {
                st = conn.prepareStatement(INSERT);
                st.setString(1, Object.getKodeRuang());
                st.setString(2, Object.getKeterangan());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru berhasil disimpan");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(varRuang Object) {
        try {
            PreparedStatement st = conn.prepareStatement(UPDATE);
            st.setString(1, Object.getKeterangan());
            st.setString(2, Object.getKodeRuang());
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
            st.setString(1, key);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<varRuang> getAll() {
        List<varRuang> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetAll);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                varRuang obj = new varRuang();
                obj.setKodeRuang(rs.getString("kode_ruang"));
                obj.setKeterangan(rs.getString("keterangan"));
                list.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varRuang> getCari(String key) {
        List<varRuang> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                varRuang obj = new varRuang();
                obj.setKodeRuang(rs.getString("kode_ruang"));
                obj.setKeterangan(rs.getString("keterangan"));
                list.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
