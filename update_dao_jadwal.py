import re

with open('src/DAO/DAO_Jadwal.java', 'r', encoding='utf-8') as f:
    content = f.read()

# Update INSERT
content = content.replace(
    'String INSERT = "insert into jadwal(TA, Semester, nip, kode_ruang, hari, kode_sesi, kode_mtk, kelompok) values(?,?,?,?,?,?,?,?)";',
    'String INSERT = "insert into jadwal(TA, Semester, nip, kode_ruang, hari, kode_sesi, kode_sesi_selesai, kode_mtk, kelompok) values(?,?,?,?,?,?,?,?,?)";'
)

# Update UPDATE
content = content.replace(
    'String UPDATE = "update jadwal set TA=?, Semester=?, nip=?, kode_ruang=?, hari=?, kode_sesi=?, kode_mtk=?, kelompok=? where id_jadwal=?";',
    'String UPDATE = "update jadwal set TA=?, Semester=?, nip=?, kode_ruang=?, hari=?, kode_sesi=?, kode_sesi_selesai=?, kode_mtk=?, kelompok=? where id_jadwal=?";'
)

# Update Selects
content = content.replace(
    "SELECT j.*, d.nama_dosen, m.NamaMTK, CONCAT(s.jam_mulai, ' - ', s.jam_selesai) AS waktu FROM jadwal j LEFT JOIN dosen d ON j.nip = d.nip LEFT JOIN matakuliah m ON j.kode_mtk = m.KodeMTK LEFT JOIN sesi s ON j.kode_sesi = s.kode_sesi",
    "SELECT j.*, d.nama_dosen, m.NamaMTK, m.SKS, CONCAT('Sesi ', j.kode_sesi, ' - ', j.kode_sesi_selesai, ' (', s.jam_mulai, ' - ', s2.jam_selesai, ')') AS waktu FROM jadwal j LEFT JOIN dosen d ON j.nip = d.nip LEFT JOIN matakuliah m ON j.kode_mtk = m.KodeMTK LEFT JOIN sesi s ON j.kode_sesi = s.kode_sesi LEFT JOIN sesi s2 ON j.kode_sesi_selesai = s2.kode_sesi"
)

# Update insert method
content = content.replace(
    '''            st.setInt(6, Object.getKodeSesi());
            st.setString(7, Object.getKodeMtk());
            st.setString(8, Object.getKelompok());
            st.executeUpdate();''',
    '''            st.setInt(6, Object.getKodeSesi());
            st.setInt(7, Object.getKodeSesiSelesai());
            st.setString(8, Object.getKodeMtk());
            st.setString(9, Object.getKelompok());
            st.executeUpdate();'''
)

# Update update method
content = content.replace(
    '''            st.setInt(6, Object.getKodeSesi());
            st.setString(7, Object.getKodeMtk());
            st.setString(8, Object.getKelompok());
            st.setInt(9, Object.getIdJadwal());
            st.executeUpdate();''',
    '''            st.setInt(6, Object.getKodeSesi());
            st.setInt(7, Object.getKodeSesiSelesai());
            st.setString(8, Object.getKodeMtk());
            st.setString(9, Object.getKelompok());
            st.setInt(10, Object.getIdJadwal());
            st.executeUpdate();'''
)

# Update getAll/getCari mappings
content = content.replace(
    '''                obj.setKodeSesi(rs.getInt("kode_sesi"));
                obj.setKodeMtk(rs.getString("kode_mtk"));''',
    '''                obj.setKodeSesi(rs.getInt("kode_sesi"));
                obj.setKodeSesiSelesai(rs.getInt("kode_sesi_selesai"));
                obj.setKodeMtk(rs.getString("kode_mtk"));
                obj.setSks(rs.getInt("SKS"));'''
)

with open('src/DAO/DAO_Jadwal.java', 'w', encoding='utf-8') as f:
    f.write(content)
