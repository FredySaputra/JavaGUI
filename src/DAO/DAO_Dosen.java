//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package DAO;

import Model.varDosen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAO_Dosen implements DAO_Interface<varDosen> {
    Connection conn;

    public DAO_Dosen() {
        conn = Koneksi.Database.KoneksiDB();
    }
    
    String INSERT = "insert into dosen(nip, nama_dosen, no_hp) values(?,?,?)";
    String UPDATE = "update dosen set nama_dosen=?, no_hp=? where nip=?";
    String DELETE = "delete from dosen where nip=?";
    String SelectUntukGetAll = "select * from dosen";
    String SelectUntukGetCari = "select * from dosen where nip like ?";
    
    @Override
    public void insert(varDosen Object) {
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, Object.getNip());
            ResultSet rs = st.executeQuery();
            if(rs.next() && Object.getNip().equals(rs.getString("nip"))) {
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan!");
            } else {
                st = conn.prepareStatement(INSERT);
                st.setString(1, Object.getNip());
                st.setString(2, Object.getNamaDosen());
                st.setString(3, Object.getNoHp());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru berhasil disimpan");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(varDosen Object) {
        try {
            PreparedStatement st = conn.prepareStatement(UPDATE);
            st.setString(1, Object.getNamaDosen());
            st.setString(2, Object.getNoHp());
            st.setString(3, Object.getNip());
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
    public List<varDosen> getAll() {
        List<varDosen> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetAll);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                varDosen obj = new varDosen();
                obj.setNip(rs.getString("nip"));
                obj.setNamaDosen(rs.getString("nama_dosen"));
                obj.setNoHp(rs.getString("no_hp"));
                list.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varDosen> getCari(String key) {
        List<varDosen> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                varDosen obj = new varDosen();
                obj.setNip(rs.getString("nip"));
                obj.setNamaDosen(rs.getString("nama_dosen"));
                obj.setNoHp(rs.getString("no_hp"));
                list.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
