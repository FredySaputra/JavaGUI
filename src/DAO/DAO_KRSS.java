//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package DAO;
import Koneksi.Database;
import java.sql.Connection;
import Model.varKRSS;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Freds
 */
public class DAO_KRSS implements DAO_Interface<varKRSS> {
    
    Connection conn;
    public DAO_KRSS(){
        conn = Database.KoneksiDB();
    }
    
    @Override
    public void insert(varKRSS Object) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM krs where TA=? and Semester=? and NIM=?");
            st.setString(1, Object.getVTA());
            st.setString(2, Object.getVSemester());
            st.setString(3, Object.getVNIM());
            rs = st.executeQuery();
            if(!rs.next()){
                st = null;
                st = conn.prepareStatement("INSERT INTO krs(TA,Semester,NIM,TglKRS) VALUES(?,?,?,?)");
                st.setString(1, Object.getVTA());
                st.setString(2, Object.getVSemester());
                st.setString(3, Object.getVNIM());
                st.setString(4, Object.getVTglKRS().substring(0,10));
                System.out.println(""+st);
                st.executeUpdate();
            }
            st = null;
                st = conn.prepareStatement("SELECT * FROM detil_krs where TA=? and Semester=? and NIM=? and KodeMTK=?");
                st.setString(1, Object.getVTA());
                st.setString(2, Object.getVSemester());
                st.setString(3, Object.getVNIM());
                st.setString(4, Object.getVKdMTK());
                rs = st.executeQuery();
                if(!rs.next()){
                    st = null;
                    st = conn.prepareStatement("INSERT INTO detil_krs(TA,Semester,NIM,KodeMTK) VALUES(?,?,?,?)");
                    st.setString(1, Object.getVTA());
                    st.setString(2, Object.getVSemester());
                    st.setString(3, Object.getVNIM());
                    st.setString(4, Object.getVKdMTK());
                    st.executeUpdate();
            }
            rs.close();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(varKRSS Object) {
    }

    @Override
    public void delete(String key) {
    }

    @Override
    public List<varKRSS> getAll() {
        return null;
    }

    @Override
    public List<varKRSS> getCari(String key) {
        return null;
    }
    
    public List<varKRSS> isicomboTA(){
        PreparedStatement statement;
        List<varKRSS> list = null;
        try{
            list = new ArrayList();
            statement = conn.prepareStatement("SELECT DISTINCT TA FROM periode ORDER BY TA");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                varKRSS b = new varKRSS();
                b.setVTA(rs.getString("TA"));
                list.add(b);
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<varKRSS> isicomboSMT(){
        PreparedStatement statement;
        List<varKRSS> list = null;
        try{
            list = new ArrayList();
            statement = conn.prepareStatement("SELECT DISTINCT Semester FROM periode ORDER BY Semester");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                varKRSS b = new varKRSS();
                b.setVSemester(rs.getString("Semester"));
                list.add(b);
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<varKRSS> getNmMhs(String nim){
        PreparedStatement statement;
        List<varKRSS> list = null;
        try{
            list = new ArrayList();
            statement = conn.prepareStatement("SELECT * from mahasiswa where NIM=?");
            statement.setString(1,nim);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                System.out.println("Data tidak ditemukan");
            }else{
                varKRSS b = new varKRSS();
                b.setVNama(rs.getString("nama"));
                list.add(b);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<varKRSS> getDataMtk (String kdmtk){
        PreparedStatement statement;
        List<varKRSS> list = null;
        try{
            list = new ArrayList();
            statement = conn.prepareStatement("SELECT * from matakuliah where KodeMTK=?");
            statement.setString(1, kdmtk);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                System.out.println("Data tidak ditemukan");
            }else{
                varKRSS b = new varKRSS();
                b.setVNamaMTK(rs.getString("NamaMTK"));
                b.setVSKS(rs.getInt("SKS"));
                b.setVKodePrasyarat(rs.getString("KodePrasyarat"));
                list.add(b);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<varKRSS> getAllDetilKRS (String ta, String semester, String nim){
        PreparedStatement st = null;
        List<varKRSS> list = null;
        try{
            st= null;
            list = new ArrayList<varKRSS>();
            String sql = "SELECT * from detil_krs a, matakuliah b where a.TA=? and a.Semester=? and a.NIM=? and a.KodeMTK=b.KodeMTK";
            st = conn.prepareStatement(sql);
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                varKRSS objKRS = new varKRSS();
                objKRS.setVKdMTK(rs.getString("KodeMTK"));
                objKRS.setVNamaMTK(rs.getString("NamaMTK"));
                objKRS.setVSKS(rs.getInt("SKS"));
                objKRS.setVKodePrasyarat(rs.getString("KodePrasyarat"));
                list.add(objKRS);
            }
            rs.close();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public void deleteKRS (String ta, String semester, String nim, String kdmtk){
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st= null;
            st = conn.prepareStatement("SELECT * FROM detil_krs where TA=? and Semester=? and NIM=? and KodeMTK=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            st.setString(4, kdmtk);
            rs = st.executeQuery();
            if(rs.next()){
                st = null;
                st = conn.prepareStatement("DELETE from detil_krs where TA=? and Semester=? and NIM=? and KodeMTK=?");
                st.setString(1, ta);
                st.setString(2, semester);
                st.setString(3, nim);
                st.setString(4, kdmtk);
                st.executeUpdate();
            }else{
                st = null;
                st = conn.prepareStatement("DELETE from krs where TA=? and Semester=? and NIM=?");
                st.setString(1, ta);
                st.setString(2, semester);
                st.setString(3, nim);
                st.executeUpdate();
            }
            rs.close();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
