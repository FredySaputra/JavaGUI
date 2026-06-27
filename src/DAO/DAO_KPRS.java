package DAO;

import Koneksi.Database;
import java.sql.Connection;
import Model.varKPRS;
import Model.varDetilKPRS;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO_KPRS implements DAO_Interface<varDetilKPRS> {
    Connection conn;
    public DAO_KPRS() {
        conn = Database.KoneksiDB();
    }

    @Override
    public void insert(varDetilKPRS Object) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO detil_kprs(TA,Semester,NIM,KodeMTK,kelompok) VALUES(?,?,?,?,?)");
            st.setString(1, Object.getTA());
            st.setString(2, Object.getSemester());
            st.setString(3, Object.getNIM());
            st.setString(4, Object.getKodeMTK());
            st.setString(5, Object.getKelompok());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(varDetilKPRS Object) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE detil_kprs SET kelompok=? WHERE TA=? AND Semester=? AND NIM=? AND KodeMTK=?");
            st.setString(1, Object.getKelompok());
            st.setString(2, Object.getTA());
            st.setString(3, Object.getSemester());
            st.setString(4, Object.getNIM());
            st.setString(5, Object.getKodeMTK());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String ta, String semester, String nim, String kodeMTK) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM detil_kprs WHERE TA=? AND Semester=? AND NIM=? AND KodeMTK=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            st.setString(4, kodeMTK);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        // Not used, using multi-key delete instead
    }

    @Override
    public List<varDetilKPRS> getAll() {
        return null;
    }

    @Override
    public List<varDetilKPRS> getCari(String key) {
        return null;
    }

    public List<varDetilKPRS> getAllDetil(String ta, String semester, String nim) {
        PreparedStatement st = null;
        List<varDetilKPRS> list = new ArrayList<>();
        try {
            String sql = "SELECT a.*, b.NamaMTK, b.SKS, c.hari, c.kode_sesi, c.kode_sesi_selesai, c.kode_ruang " +
                         "FROM detil_kprs a " +
                         "JOIN matakuliah b ON a.KodeMTK = b.KodeMTK " +
                         "LEFT JOIN jadwal c ON a.KodeMTK = c.kode_mtk AND a.kelompok = c.kelompok AND a.TA = c.TA AND a.Semester = c.Semester " +
                         "WHERE a.TA=? AND a.Semester=? AND a.NIM=?";
            st = conn.prepareStatement(sql);
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                varDetilKPRS obj = new varDetilKPRS();
                obj.setTA(rs.getString("TA"));
                obj.setSemester(rs.getString("Semester"));
                obj.setNIM(rs.getString("NIM"));
                obj.setKodeMTK(rs.getString("KodeMTK"));
                obj.setKelompok(rs.getString("kelompok"));
                obj.setNamaMTK(rs.getString("NamaMTK"));
                obj.setSKS(rs.getInt("SKS"));
                obj.setHari(rs.getString("hari"));
                // This could be mapped from jadwal, we assume it's fetched correctly.
                obj.setJamMulai(rs.getString("kode_sesi")); 
                obj.setJamSelesai(rs.getString("kode_sesi_selesai"));
                obj.setRuang(rs.getString("kode_ruang"));
                list.add(obj);
            }
            rs.close();
            st.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean checkKprsExists(String ta, String semester, String nim) {
        PreparedStatement st = null;
        boolean exists = false;
        try {
            st = conn.prepareStatement("SELECT 1 FROM kprs WHERE TA=? AND Semester=? AND NIM=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            ResultSet rs = st.executeQuery();
            if (rs.next()) exists = true;
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean checkDetilKprsExists(String ta, String semester, String nim) {
        PreparedStatement st = null;
        boolean exists = false;
        try {
            st = conn.prepareStatement("SELECT 1 FROM detil_kprs WHERE TA=? AND Semester=? AND NIM=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            ResultSet rs = st.executeQuery();
            if (rs.next()) exists = true;
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void deleteKprsHeader(String ta, String semester, String nim) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM kprs WHERE TA=? AND Semester=? AND NIM=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkKrsExists(String ta, String semester, String nim) {
        PreparedStatement st = null;
        boolean exists = false;
        try {
            st = conn.prepareStatement("SELECT 1 FROM krs WHERE TA=? AND Semester=? AND NIM=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            ResultSet rs = st.executeQuery();
            if (rs.next()) exists = true;
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void copyKrsToKprs(String ta, String semester, String nim) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO kprs(TA, Semester, NIM, TglKPRS) " +
                                       "SELECT TA, Semester, NIM, CURDATE() FROM krs WHERE TA=? AND Semester=? AND NIM=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            st.executeUpdate();
            st.close();
            
            st = conn.prepareStatement("INSERT INTO detil_kprs(TA, Semester, NIM, KodeMTK, kelompok) " +
                                       "SELECT TA, Semester, NIM, KodeMTK, '' FROM detil_krs WHERE TA=? AND Semester=? AND NIM=? AND KodeMTK IN (SELECT KodeMTK FROM matakuliah)");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, nim);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean cekBentrokJadwal(String nim, String ta, String semester, String hari, int sesiMulai, int sesiSelesai, String kodeMTKAbaikan) {
        PreparedStatement st = null;
        boolean isBentrok = false;
        try {
            String sql = "SELECT j_lama.kode_mtk, mk.NamaMTK, j_lama.hari " +
                         "FROM jadwal j_lama " +
                         "JOIN detil_kprs dk ON j_lama.kode_mtk = dk.KodeMTK AND j_lama.kelompok = dk.kelompok " +
                         "JOIN matakuliah mk ON j_lama.kode_mtk = mk.KodeMTK " +
                         "WHERE dk.NIM = ? " +
                         "  AND dk.TA = ? " +
                         "  AND dk.Semester = ? " +
                         "  AND j_lama.hari = ? " +
                         "  AND ( ? <= j_lama.kode_sesi_selesai ) " +
                         "  AND ( ? >= j_lama.kode_sesi ) " +
                         "  AND j_lama.kode_mtk != ?";
            st = conn.prepareStatement(sql);
            st.setString(1, nim);
            st.setString(2, ta);
            st.setString(3, semester);
            st.setString(4, hari);
            st.setInt(5, sesiMulai);
            st.setInt(6, sesiSelesai);
            st.setString(7, kodeMTKAbaikan == null ? "" : kodeMTKAbaikan);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                isBentrok = true;
                // Optional: We can throw an exception or return detailed info here, 
                // but a boolean is the minimum requirement to check bentrok.
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isBentrok;
    }
    
    public String[] getBentrokInfo(String nim, String ta, String semester, String hari, int sesiMulai, int sesiSelesai, String kodeMTKAbaikan) {
        PreparedStatement st = null;
        String[] info = null;
        try {
            String sql = "SELECT j_lama.kode_mtk, mk.NamaMTK, j_lama.hari " +
                         "FROM jadwal j_lama " +
                         "JOIN detil_kprs dk ON j_lama.kode_mtk = dk.KodeMTK AND j_lama.kelompok = dk.kelompok " +
                         "JOIN matakuliah mk ON j_lama.kode_mtk = mk.KodeMTK " +
                         "WHERE dk.NIM = ? " +
                         "  AND dk.TA = ? " +
                         "  AND dk.Semester = ? " +
                         "  AND j_lama.hari = ? " +
                         "  AND ( ? <= j_lama.kode_sesi_selesai ) " +
                         "  AND ( ? >= j_lama.kode_sesi ) " +
                         "  AND j_lama.kode_mtk != ?";
            st = conn.prepareStatement(sql);
            st.setString(1, nim);
            st.setString(2, ta);
            st.setString(3, semester);
            st.setString(4, hari);
            st.setInt(5, sesiMulai);
            st.setInt(6, sesiSelesai);
            st.setString(7, kodeMTKAbaikan == null ? "" : kodeMTKAbaikan);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                info = new String[]{ rs.getString("NamaMTK"), rs.getString("hari") };
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
    
    
    
    public List<String> isicomboKelompok(String ta, String semester, String kdMtk) {
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT kelompok FROM jadwal WHERE TA=? AND Semester=? AND kode_mtk=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, kdMtk);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                list.add(rs.getString("kelompok"));
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Object[] getJadwalInfo(String ta, String semester, String kdMtk, String kelompok) {
        Object[] info = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT hari, kode_sesi, kode_sesi_selesai FROM jadwal WHERE TA=? AND Semester=? AND kode_mtk=? AND kelompok=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, kdMtk);
            st.setString(4, kelompok);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                info = new Object[]{rs.getString("hari"), rs.getInt("kode_sesi"), rs.getInt("kode_sesi_selesai")};
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public List<String> isicomboTA(){
        PreparedStatement statement;
        List<String> list = new ArrayList<>();
        try{
            statement = conn.prepareStatement("SELECT DISTINCT TA FROM periode ORDER BY TA");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(rs.getString("TA"));
            }
            rs.close();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<String> isicomboSMT(){
        PreparedStatement statement;
        List<String> list = new ArrayList<>();
        try{
            statement = conn.prepareStatement("SELECT DISTINCT Semester FROM periode ORDER BY Semester");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(rs.getString("Semester"));
            }
            rs.close();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public String getNamaMahasiswa(String nim) {
        PreparedStatement st = null;
        String nama = null;
        try {
            st = conn.prepareStatement("SELECT Nama FROM mahasiswa WHERE NIM = ?");
            st.setString(1, nim);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                nama = rs.getString("Nama");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nama;
    }
}
