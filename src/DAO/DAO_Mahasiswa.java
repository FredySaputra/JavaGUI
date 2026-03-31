/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.varMahasiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahasiswa
 */
public class DAO_Mahasiswa implements DAO_Interface<varMahasiswa> {

    Connection conn;

    public DAO_Mahasiswa() {
        conn = Koneksi.Database.KoneksiDB();
    }
    
    String INSERT = "insert into mahasiswa(NIM,nama,alamat) values(?,?,?)";
    String UPDATE = "update mahasiswa set nama=?, alamat=? where NIM=?";
    String DELETE = "delete from mahasiswa where NIM=?";
    String SelectUntukGetAll = "select * from mahasiswa";
    String SelectUntukGetCari = "select * from mahasiswa where NIM like ?";
    
    @Override
    public void insert(varMahasiswa Object) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, Object.getVNIM());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan!");
            }else{
                st = null;
                st = conn.prepareStatement(INSERT);
                st.setString(1,Object.getVNIM());
                st.setString(2,Object.getVNama());
                st.setString(3,Object.getVAlamat());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru berhasil disimpan");
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(varMahasiswa Object) {
        PreparedStatement st = null;
        try{
            st = null;
            st = conn.prepareStatement(UPDATE);
            st.setString(1,Object.getVNama());
            st.setString(2,Object.getVAlamat());
            st.setString(3,Object.getVNIM());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        PreparedStatement st = null;
        try{
            st = null;
            st = conn.prepareStatement(DELETE);
            st.setString(1, key);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<varMahasiswa> getAll() {
        List<varMahasiswa> list = null;
        PreparedStatement st = null;
        try{
            st = null;
            list = new ArrayList<varMahasiswa>();
            st = conn.prepareStatement(SelectUntukGetAll);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                varMahasiswa objMhs = new varMahasiswa();
                objMhs.setVNIM(rs.getString("nim"));
                objMhs.setVNama(rs.getString("nama"));
                objMhs.setVAlamat(rs.getString("alamat"));
                list.add(objMhs);
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varMahasiswa> getCari(String key) {
       List<varMahasiswa> list = null;
       PreparedStatement st = null;
       try{
           st = null;
           list = new ArrayList<varMahasiswa>();
           st = conn.prepareStatement(SelectUntukGetCari);
           st.setString(1,"%"+key+"%");
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               varMahasiswa objMhs = new varMahasiswa();
               objMhs.setVNIM(rs.getString("nim"));
               objMhs.setVNama(rs.getString("nama"));
               objMhs.setVAlamat(rs.getString("alamat"));
               list.add(objMhs);
           }
           st.close();
       }catch(Exception e){
           e.printStackTrace();
       }
        return list;
    }
    
}
