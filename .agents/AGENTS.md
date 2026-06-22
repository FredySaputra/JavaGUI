# Spesifikasi Teknis & Panduan Pengembangan: Sistem Akademik

Dokumen ini berisi panduan dan konvensi utama untuk proyek Sistem Akademik ini. Panduan ini mencerminkan state project saat ini dan wajib dipatuhi oleh Agen AI saat melakukan modifikasi atau penambahan fitur di masa mendatang.

## 1. Lingkungan Pengembangan (Wajib Dipatuhi)
* **IDE**: NetBeans IDE versi 8.2.
* **JDK**: Java 8.
* **Catatan Penting**: DILARANG mengubah versi Java atau IDE. Kode yang dihasilkan harus selalu kompatibel dengan Java 8 dan NetBeans 8.2 GUI Builder (`.form`).

## 2. Arsitektur Proyek (MVC)
Proyek ini menggunakan pola arsitektur **Model-View-Controller (MVC)** klasik dipadukan dengan pola **Data Access Object (DAO)**. 

Struktur direktori pada `src/` terbagi menjadi:
1. **`Model`**: Berisi kelas representasi entitas (POJO).
   * Konvensi penamaan: `var<NamaEntitas>.java` (contoh: `varJadwal.java`, `varDosen.java`).
2. **`DAO`**: Berisi kelas untuk operasi database (CRUD).
   * Konvensi penamaan: `DAO_<NamaEntitas>.java` (contoh: `DAO_Jadwal.java`).
   * Setiap DAO wajib mengimplementasikan interface `DAO_Interface<T>` yang memuat fungsi standar seperti `insert`, `update`, `delete`, `getAll`, dan `getCari`.
3. **`Controller`**: Berisi kelas yang menghubungkan View dan DAO, serta mengelola logika bisnis.
   * Konvensi penamaan: `Controller_<NamaEntitas>.java` (contoh: `Controller_Jadwal.java`).
4. **`View`**: Berisi komponen antarmuka pengguna (GUI) berbasis Java Swing.
   * Konvensi penamaan: `Frm<NamaEntitas>.java` beserta file form NetBeans `Frm<NamaEntitas>.form` (contoh: `FrmJadwal.java`).

## 3. Konvensi Database
* **Nama Database**: `db_akademik_2311500140`
* Proyek telah melalui proses *refactoring* di mana prefiks `tb_` telah dihapus dari tabel master. Nama tabel saat ini antara lain: `dosen`, `ruang`, `sesi`, `matakuliah`, `mahasiswa`, dan `jadwal`.
* **Tabel `jadwal`**: Memiliki *Primary Key* `id_jadwal` yang bersifat *Auto Increment*. Tabel ini juga menampung komputasi akhir sesi pada kolom `kode_sesi_selesai`.

### Skema Tabel Database Terkini:
1. **`dosen`**: `nip` (varchar 20, PK), `nama_dosen` (varchar 100), `no_hp` (varchar 15).
2. **`ruang`**: `kode_ruang` (varchar 10, PK), `keterangan` (text).
3. **`sesi`**: `kode_sesi` (int 11, PK), `jam_mulai` (varchar 5), `jam_selesai` (varchar 5).
4. **`matakuliah`**: `KodeMTK` (char 5, PK), `NamaMTK` (varchar 50), `SKS` (int 11), `KodePrasyarat` (char 5).
5. **`mahasiswa`**: `NIM` (char 10, PK), `Nama` (varchar 50), `Alamat` (varchar 200).
6. **`periode`**: `TA` (varchar 9, PK), `Semester` (varchar 8, PK).
7. **`krs`**: `TA` (varchar 9, PK), `Semester` (varchar 8, PK), `NIM` (char 10, PK), `TglKRS` (date).
8. **`detil_krs`**: `TA` (varchar 9, PK), `Semester` (varchar 8, PK), `NIM` (char 10, PK), `KodeMTK` (char 5, PK).
9. **`jadwal`**: `id_jadwal` (int 11, PK, Auto Increment), `TA` (varchar 9), `Semester` (varchar 8), `nip` (varchar 20, FK), `kode_ruang` (varchar 10, FK), `hari` (varchar 10), `kode_sesi` (int 11, FK), `kode_sesi_selesai` (int 11, FK), `kode_mtk` (char 5, FK), `kelompok` (varchar 5).

## 4. Standar Kode & Antarmuka Pengguna (GUI)
1. **Kompatibilitas NetBeans GUI Builder**: 
   Penyuntingan komponen UI sebaiknya tetap mempertahankan kompatibilitas dengan file `.form`. Untuk logika event (seperti *ActionListener*, *MouseListener*, *ItemListener*), implementasinya di-*inject* secara terprogram tepat setelah `initComponents()` pada konstruktor *View* agar terhindar dari konflik *code generation* bawaan NetBeans.
2. **Penanganan `DefaultTableModel`**:
   Dilarang menggunakan *anonymous inner class* untuk model tabel. Gunakan *named class* (kelas dengan nama yang jelas) untuk mencegah *error* `NoClassDefFoundError` ($1.class hilang) saat proses kompilasi NetBeans gagal di tengah jalan (misalnya saat membangun JasperReports).
3. **Penanganan Karakter Spasi (Trailing Spaces)**:
   Data yang diambil dari MySQL bertipe `CHAR` atau `VARCHAR` terkadang memiliki spasi ekstra di akhir string. Wajib menerapkan fungsi `.trim()` secara agresif, khususnya saat memuat dan mencocokkan nilai ke dalam komponen `JComboBox` agar item tidak gagal terseleksi (terutama NIP Dosen / Kode entitas lainnya).
4. **Validasi & Interaksi Pengguna (Khusus Transaksi/Jadwal)**:
   * Event `ItemStateChangeListener` sering digunakan pada `JComboBox` untuk validasi bentrok (*real-time validation*) dan kalkulasi otomatis (contoh: perhitungan jam selesai).
   * Nilai kunci baris seperti `id_jadwal` atau id *primary* lainnya di tabel Swing sering disimpan secara tersembunyi (width = 0) atau disimpan dalam variabel level kelas saat baris diklik (`MouseListener`).
5. **Layouts**: Layout kompleks seperti form transaksi jadwal menggunakan `AbsoluteLayout` (seringkali dibantu script *refactoring* eksternal) untuk membagi porsi form agar lebih responsif dan proporsional. Hindari pemakaian `GroupLayout` yang kaku jika dibutuhkan form yang fleksibel.
6. **Pembaruan Terbaru Form Transaksi Jadwal**: Terdapat ekstensi field untuk detail sesi (meliputi `txtJamMulai`, `txtSesiSelesai`, dan `txtJamSelesai`) serta field khusus detail matakuliah (`txtSks` dengan teks berakhiran " SKS"). Logika pengisian field ini dikelola di dalam `Controller_Jadwal.java` (seperti pada metode `calculateJamSelesai()` dan `fetchNamaMtk()`), sedangkan posisi/lebar komponen diperbarui di file `.form` NetBeans dan `FrmJadwal.java` secara sinkron agar teks tidak terpotong.
