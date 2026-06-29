//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package DAO;

import Model.varJadwal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAO_Jadwal implements DAO_Interface<varJadwal> {
    Connection conn;

    public DAO_Jadwal() {
        conn = Koneksi.Database.KoneksiDB();
    }
    
    String INSERT = "insert into jadwal(TA, Semester, nip, kode_ruang, hari, kode_sesi, kode_sesi_selesai, kode_mtk, kelompok) values(?,?,?,?,?,?,?,?,?)";
    String UPDATE = "update jadwal set TA=?, Semester=?, nip=?, kode_ruang=?, hari=?, kode_sesi=?, kode_sesi_selesai=?, kode_mtk=?, kelompok=? where id_jadwal=?";
    String DELETE = "delete from jadwal where id_jadwal=?";
    String SelectUntukGetAll = "SELECT j.*, d.nama_dosen, m.NamaMTK, m.SKS, CONCAT('Sesi ', j.kode_sesi, ' - ', j.kode_sesi_selesai, ' (', s.jam_mulai, ' - ', s2.jam_selesai, ')') AS waktu FROM jadwal j LEFT JOIN dosen d ON j.nip = d.nip LEFT JOIN matakuliah m ON j.kode_mtk = m.KodeMTK LEFT JOIN sesi s ON j.kode_sesi = s.kode_sesi LEFT JOIN sesi s2 ON j.kode_sesi_selesai = s2.kode_sesi";
    String SelectUntukGetCari = "SELECT j.*, d.nama_dosen, m.NamaMTK, m.SKS, CONCAT('Sesi ', j.kode_sesi, ' - ', j.kode_sesi_selesai, ' (', s.jam_mulai, ' - ', s2.jam_selesai, ')') AS waktu FROM jadwal j LEFT JOIN dosen d ON j.nip = d.nip LEFT JOIN matakuliah m ON j.kode_mtk = m.KodeMTK LEFT JOIN sesi s ON j.kode_sesi = s.kode_sesi LEFT JOIN sesi s2 ON j.kode_sesi_selesai = s2.kode_sesi WHERE j.TA like ? OR j.Semester like ?";
    
    @Override
    public void insert(varJadwal Object) {
        try {
            PreparedStatement st = conn.prepareStatement(INSERT);
            st.setString(1, Object.getTa());
            st.setString(2, Object.getSemester());
            st.setString(3, Object.getNip());
            st.setString(4, Object.getKodeRuang());
            st.setString(5, Object.getHari());
            st.setInt(6, Object.getKodeSesi());
            st.setInt(7, Object.getKodeSesiSelesai());
            st.setString(8, Object.getKodeMtk());
            st.setString(9, Object.getKelompok());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Baru berhasil disimpan");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(varJadwal Object) {
        try {
            PreparedStatement st = conn.prepareStatement(UPDATE);
            st.setString(1, Object.getTa());
            st.setString(2, Object.getSemester());
            st.setString(3, Object.getNip());
            st.setString(4, Object.getKodeRuang());
            st.setString(5, Object.getHari());
            st.setInt(6, Object.getKodeSesi());
            st.setInt(7, Object.getKodeSesiSelesai());
            st.setString(8, Object.getKodeMtk());
            st.setString(9, Object.getKelompok());
            st.setInt(10, Object.getIdJadwal());
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
    public List<varJadwal> getAll() {
        List<varJadwal> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetAll);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                varJadwal obj = new varJadwal();
                obj.setIdJadwal(rs.getInt("id_jadwal"));
                obj.setTa(rs.getString("TA"));
                obj.setSemester(rs.getString("Semester"));
                obj.setNip(rs.getString("nip"));
                obj.setKodeRuang(rs.getString("kode_ruang"));
                obj.setHari(rs.getString("hari"));
                obj.setKodeSesi(rs.getInt("kode_sesi"));
                obj.setKodeSesiSelesai(rs.getInt("kode_sesi_selesai"));
                obj.setKodeMtk(rs.getString("kode_mtk"));
                obj.setSks(rs.getInt("SKS"));
                obj.setKelompok(rs.getString("kelompok"));
                obj.setNamaDosen(rs.getString("nama_dosen"));
                obj.setNamaMtk(rs.getString("NamaMTK"));
                obj.setWaktu(rs.getString("waktu"));
                list.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varJadwal> getCari(String key) {
        List<varJadwal> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SelectUntukGetCari);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                varJadwal obj = new varJadwal();
                obj.setIdJadwal(rs.getInt("id_jadwal"));
                obj.setTa(rs.getString("TA"));
                obj.setSemester(rs.getString("Semester"));
                obj.setNip(rs.getString("nip"));
                obj.setKodeRuang(rs.getString("kode_ruang"));
                obj.setHari(rs.getString("hari"));
                obj.setKodeSesi(rs.getInt("kode_sesi"));
                obj.setKodeSesiSelesai(rs.getInt("kode_sesi_selesai"));
                obj.setKodeMtk(rs.getString("kode_mtk"));
                obj.setSks(rs.getInt("SKS"));
                obj.setKelompok(rs.getString("kelompok"));
                obj.setNamaDosen(rs.getString("nama_dosen"));
                obj.setNamaMtk(rs.getString("NamaMTK"));
                obj.setWaktu(rs.getString("waktu"));
                list.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
