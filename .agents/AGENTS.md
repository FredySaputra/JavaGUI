# Spesifikasi Teknis & Panduan Pengembangan: Sistem Akademik

Dokumen ini berisi panduan dan konvensi utama untuk proyek Sistem Akademik ini. Panduan ini mencerminkan state project saat ini dan wajib dipatuhi oleh Agen AI saat melakukan modifikasi atau penambahan fitur di masa mendatang.

## 1. Lingkungan Pengembangan & Kompilasi (Wajib Dipatuhi)
* **IDE**: NetBeans IDE versi 8.2.
* **JDK**: Java 8.
* **Kompilasi Manual oleh Agen**: 
  Jika agen melakukan modifikasi pada kode `.java`, agen **WAJIB** melakukan kompilasi (*compile*) file tersebut ke target direktori `build/classes/` (bukan ke `src/`). Hal ini mutlak diperlukan karena saat pengguna mengklik tombol *Run Project* di NetBeans, NetBeans akan mengeksekusi kelas dari folder `build/classes/`. Jika kompilasi tidak diarahkan ke sana (misalnya `javac -d build\classes -cp src ...`), pengguna akan terus melihat aplikasi versi lama (stale code).
* **Catatan Penting**: DILARANG mengubah versi Java atau IDE. Kode yang dihasilkan harus selalu kompatibel dengan Java 8.

## 2. Arsitektur Proyek (MVC)
Proyek ini menggunakan pola arsitektur **Model-View-Controller (MVC)** klasik dipadukan dengan pola **Data Access Object (DAO)**. 

Struktur direktori pada `src/` terbagi menjadi:
1. **`Model`**: Berisi kelas representasi entitas (POJO). Konvensi penamaan: `var<NamaEntitas>.java`.
2. **`DAO`**: Berisi kelas untuk operasi database (CRUD). Konvensi penamaan: `DAO_<NamaEntitas>.java`. Wajib mengimplementasikan interface `DAO_Interface<T>`.
3. **`Controller`**: Berisi kelas penghubung View dan DAO, mengelola logika bisnis. Konvensi penamaan: `Controller_<NamaEntitas>.java`.
4. **`View`**: Komponen UI berbasis Java Swing. Konvensi penamaan: `Frm<NamaEntitas>.java` beserta file form NetBeans `Frm<NamaEntitas>.form`.

## 3. Konvensi Database
* **Nama Database**: `db_akademik_2311500140`
* Proyek telah melalui *refactoring* dengan menghapus prefiks `tb_` dari tabel master.

### Skema Tabel Database Terkini:
1. **`dosen`**: `nip` (varchar 20, PK), `nama_dosen` (varchar 100), `no_hp` (varchar 15).
2. **`ruang`**: `kode_ruang` (varchar 10, PK), `keterangan` (text).
3. **`sesi`**: `kode_sesi` (int 11, PK), `jam_mulai` (varchar 5), `jam_selesai` (varchar 5).
4. **`matakuliah`**: `KodeMTK` (char 5, PK), `NamaMTK` (varchar 50), `SKS` (int 11), `KodePrasyarat` (char 5).
5. **`mahasiswa`**: `NIM` (char 10, PK), `Nama` (varchar 50), `Alamat` (varchar 200).
6. **`periode`**: `TA` (varchar 9, PK), `Semester` (varchar 8, PK).
7. **`krs` / `krss`**: `TA` (varchar 9, PK), `Semester` (varchar 8, PK), `NIM` (char 10, PK), `TglKRS` (date).
8. **`detil_krs`**: `TA` (varchar 9, PK), `Semester` (varchar 8, PK), `NIM` (char 10, PK), `KodeMTK` (char 5, PK).
9. **`jadwal`**: `id_jadwal` (int 11, PK, Auto Increment), `TA`, `Semester`, `nip`, `kode_ruang`, `hari`, `kode_sesi`, `kode_sesi_selesai`, `kode_mtk`, `kelompok`.
10. **`kprs`**: `TA` (varchar 9, PK), `Semester` (varchar 8, PK), `NIM` (char 10, PK), `TglKPRS` (date).
11. **`detil_kprs`**: `TA` (varchar 9, PK), `Semester` (varchar 8, PK), `NIM` (char 10, PK), `KodeMTK` (char 5, PK), `kelompok` (varchar 5).

## 4. Standar Kode & Antarmuka Pengguna (GUI)
1. **LARANGAN KERAS Anonymous Inner Class (`$$`)**:
   - Dilarang keras menggunakan *anonymous inner class* untuk event listener (seperti `new MouseAdapter(){...}`, `new ActionListener(){...}`) ataupun untuk Model JTable. 
   - Penggunaan ini akan menghasilkan file `.class` bernama seperti `FrmKPRS$1.class` yang kerap kali gagal dibaca oleh struktur proyek dan menimbulkan `NoClassDefFoundError` (error gagal jalan).
   - Seluruh event listener harus diimplementasikan pada deklarasi kelas form utamanya (`implements ActionListener, MouseListener`, dll) atau menggunakan *Named Nested Class* yang proper.
2. **Kompatibilitas NetBeans GUI Builder (`.form`)**:
   - Saat mengubah properti GUI atau menambahkan komponen secara programatis (seperti `lblClose`), **pastikan juga memodifikasi file `.form`** (bisa menggunakan *python script* XML patcher) agar perubahan tidak tertimpa/hilang ketika pengguna membukanya di antarmuka desain visual NetBeans IDE.
3. **Penanganan Form Undecorated**:
   - Jika JFrame di-set `setUndecorated(true)`, form tersebut tidak memiliki *title bar* dan tombol *close* bawaan OS. Tombol penutup (X) buatan (misal `lblClose`) harus disertakan secara manual di pojok kanan atas (*absolute positioning*) beserta event `dispose()`.
4. **Validasi Spasi & Foregin Key Constraints**:
   - Terapkan fungsi `.trim()` secara agresif pada input dan hasil query untuk tipe data `CHAR`/`VARCHAR`.
   - Ketika melakukan migrasi/menyalin data dalam jumlah massal (seperti `copyKrsToKprs`), sertakan proteksi validasi data kotor (misal `WHERE KodeMTK IN (SELECT KodeMTK FROM matakuliah)`) agar proses eksekusi terhindar dari *Foreign Key Constraint Failures* jika terdapat data rujukan kosong (`''`) di tabel asal.
5. **Layout & Komponen Tambahan**:
   - `AbsoluteLayout` banyak digunakan demi fleksibilitas pemosisian. 
   - Nilai kunci seperti ID di baris JTable dapat disembunyikan menggunakan indeks atau diletakkan pada model tanpa dirender secara visual.
