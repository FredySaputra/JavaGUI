/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
absen : 7
 */
package DAO;

import Model.varPeriode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Periode
 */
public class DAO_Periode implements DAO_Interface<varPeriode> {

    Connection conn;

    public DAO_Periode() {
        conn = Koneksi.Database.KoneksiDB();
    }
    
    String INSERT = "insert into periode(TA,Semester) values(?,?)";
    String UPDATE = "update periode set Semester=? where TA=?";
    String DELETE = "delete from periode where TA=?";
    String SelectUntukGetAll = "select * from periode";
    String SelectUntukGetCari = "select * from periode where TA like ?";
    
    @Override
    public void insert(varPeriode Object) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, Object.getVTA());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan!");
            }else{
                st = null;
                st = conn.prepareStatement(INSERT);
                st.setString(1,Object.getVTA());
                st.setString(2,Object.getVSemester());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru berhasil disimpan");
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(varPeriode Object) {
        PreparedStatement st = null;
        try{
            st = null;
            st = conn.prepareStatement(UPDATE);
            st.setString(1,Object.getVSemester());
            st.setString(2,Object.getVTA());
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
    public List<varPeriode> getAll() {
        List<varPeriode> list = null;
        PreparedStatement st = null;
        try{
            st = null;
            list = new ArrayList<varPeriode>();
            st = conn.prepareStatement(SelectUntukGetAll);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                varPeriode objMhs = new varPeriode();
                objMhs.setVTA(rs.getString("TA"));
                objMhs.setVSemester(rs.getString("Semester"));
                list.add(objMhs);
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varPeriode> getCari(String key) {
       List<varPeriode> list = null;
       PreparedStatement st = null;
       try{
           st = null;
           list = new ArrayList<varPeriode>();
           st = conn.prepareStatement(SelectUntukGetCari);
           st.setString(1,"%"+key+"%");
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               varPeriode objMhs = new varPeriode();
               objMhs.setVTA(rs.getString("TA"));
               objMhs.setVSemester(rs.getString("Semester"));
               list.add(objMhs);
           }
           st.close();
       }catch(Exception e){
           e.printStackTrace();
       }
        return list;
    }
    
}
