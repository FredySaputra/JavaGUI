/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.varMatakuliah;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Matakuliah
 */
public class DAO_Matakuliah implements DAO_Interface<varMatakuliah> {

    Connection conn;

    public DAO_Matakuliah() {
        conn = Koneksi.Database.KoneksiDB();
    }
    
    String INSERT = "insert into matakuliah(KodeMTK,NamaMTK,SKS,KodePrasyarat) values(?,?,?,?)";
    String UPDATE = "update matakuliah set NamaMTK=?, SKS=?,KodePrasyarat=? where KodeMTK=?";
    String DELETE = "delete from matakuliah where KodeMTK=?";
    String SelectUntukGetAll = "select * from matakuliah";
    String SelectUntukGetCari = "select * from matakuliah where KodeMTK like ?";
    
    @Override
    public void insert(varMatakuliah Object) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, Object.getVKodeMTK());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan!");
            }else{
                st = null;
                st = conn.prepareStatement(INSERT);
                st.setString(1,Object.getVKodeMTK());
                st.setString(2,Object.getVNamaMTK());
                st.setInt(3,Object.getVSKS());
                st.setString(4,Object.getVKodePrasyarat());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru berhasil disimpan");
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(varMatakuliah Object) {
        PreparedStatement st = null;
        try{
            st = null;
            st = conn.prepareStatement(UPDATE);
            st.setString(1,Object.getVNamaMTK());
            st.setInt(2,Object.getVSKS());
            st.setString(3,Object.getVKodePrasyarat());
            st.setString(4,Object.getVKodeMTK());
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
    public List<varMatakuliah> getAll() {
        List<varMatakuliah> list = null;
        PreparedStatement st = null;
        try{
            st = null;
            list = new ArrayList<varMatakuliah>();
            st = conn.prepareStatement(SelectUntukGetAll);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                varMatakuliah objMhs = new varMatakuliah();
                objMhs.setVKodeMTK(rs.getString("KodeMTK"));
                objMhs.setVNamaMTK(rs.getString("NamaMTK"));
                objMhs.setVSKS(rs.getInt("SKS"));
                objMhs.setVKodePrasyarat(rs.getString("KodePrasyarat"));
                list.add(objMhs);
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varMatakuliah> getCari(String key) {
       List<varMatakuliah> list = null;
       PreparedStatement st = null;
       try{
           st = null;
           list = new ArrayList<varMatakuliah>();
           st = conn.prepareStatement(SelectUntukGetCari);
           st.setString(1,"%"+key+"%");
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               varMatakuliah objMhs = new varMatakuliah();
               objMhs.setVKodeMTK(rs.getString("KodeMTK"));
               objMhs.setVNamaMTK(rs.getString("NamaMTK"));
               objMhs.setVSKS(rs.getInt("SKS"));
               objMhs.setVNamaMTK(rs.getString("KodePrasyarat"));
               list.add(objMhs);
           }
           st.close();
       }catch(Exception e){
           e.printStackTrace();
       }
        return list;
    }
    
}
