# Spesifikasi Teknis: Form Transaksi Jadwal Matakuliah & Refactoring Database

## 1. Perubahan Struktur Database (PENTING)
Sesuai instruksi, prefix `tb_` pada tabel master dihilangkan. Agen AI wajib menjalankan *query* berikut dan **MEMPERBARUI** semua sintaks SQL pada modul CRUD Java master yang sudah ada sebelumnya:

RENAME TABLE tb_dosen TO dosen;
RENAME TABLE tb_ruang TO ruang;
RENAME TABLE tb_sesi TO sesi;

Tabel `jadwal` belum ada di dalam database. Agen AI wajib membuat tabel ini beserta relasinya, dan **HARUS** menyertakan `id_jadwal` (Auto Increment) sebagai *Primary Key* agar fungsi *Update* dan *Delete* pada aplikasi dapat berjalan tanpa bug:

CREATE TABLE jadwal (
    id_jadwal INT AUTO_INCREMENT PRIMARY KEY,
    TA VARCHAR(9) NOT NULL,
    Semester VARCHAR(8) NOT NULL,
    nip VARCHAR(20) NOT NULL,
    kode_ruang VARCHAR(10) NOT NULL,
    hari VARCHAR(10) NOT NULL,
    kode_sesi INT NOT NULL,
    kode_mtk CHAR(5) NOT NULL,
    kelompok VARCHAR(5) NOT NULL,
    FOREIGN KEY (nip) REFERENCES dosen(nip) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (kode_ruang) REFERENCES ruang(kode_ruang) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (kode_sesi) REFERENCES sesi(kode_sesi) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (kode_mtk) REFERENCES matakuliah(KodeMTK) ON UPDATE CASCADE ON DELETE RESTRICT
);


## 2. Kebutuhan Komponen UI (Form Transaksi Jadwal)
Buat `JFrame` baru dengan struktur berikut:
* **Dropdown (JComboBox):**
    * `cbTA`: Memuat data `TA` (Gunakan `SELECT DISTINCT TA FROM periode WHERE TA != ''`).
    * `cbSemester`: Memuat data `Semester` (Gunakan `SELECT DISTINCT Semester FROM periode WHERE Semester != ''`).
    * `cbRuang`: Memuat data `kode_ruang` dari tabel `ruang`.
    * `cbHari`: Hardcoded *Item* `["Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"]`.
    * `cbSesi`: Memuat data `kode_sesi` dari tabel `sesi`.
    * `cbDosen`: Memuat data `nip` dari tabel `dosen`.
    * `cbMtk`: Memuat data `KodeMTK` dari tabel `matakuliah`.
* **Input Teks (JTextField):**
    * `txtNamaDosen`: (Read-Only / `setEditable(false)`).
    * `txtNamaMtk`: (Read-Only / `setEditable(false)`).
    * `txtKelompok`: Input teks reguler untuk mengetik kelompok kelas (misal: "AA").
* **Tabel & Tombol Operasi:**
    * `JTable tblJadwal`: Untuk menampilkan data jadwal. Kolom wajib: ID Jadwal (bisa disembunyikan *width* 0), TA, Semester, Ruang, Hari, Waktu (Gabungan jam_mulai - jam_selesai), Dosen, Matakuliah, Kelompok.
    * `JButton`: Tambah, Ubah, Hapus, Bersih.

## 3. Logika Program (Event Handlers)
1. **Auto-Fill Data Master (Wajib):**
   Tambahkan `ItemListener` atau `ActionListener` pada `cbDosen`. Ketika NIP dipilih, jalankan *query* `SELECT nama_dosen FROM dosen WHERE nip = ?` dan masukkan ke `txtNamaDosen`. 
   Lakukan hal serupa pada `cbMtk`: ambil `NamaMTK` dari tabel `matakuliah` dan masukkan ke `txtNamaMtk`.
2. **CRUD Operasi dengan ID Spesifik:**
   Saat baris `tblJadwal` diklik (`MouseListener`), tangkap nilai `id_jadwal` ke dalam variabel kelas global (contoh: `int selectedId`). Gunakan `selectedId` ini pada klausa `WHERE id_jadwal = ?` saat mengeksekusi *PreparedStatement* untuk fungsi `Ubah` (UPDATE) dan `Hapus` (DELETE).
3. **Validasi:**
   Cegah eksekusi `Tambah` atau `Ubah` jika `txtKelompok` kosong atau *ComboBox* belum dipilih.
## 4. Catatan Perbaikan (Bug Fixes & Refactoring)
1. **NetBeans GUI Builder Compatibility**: Event bindings (seperti ddActionListener, ddMouseListener, dll) telah diekstraksi ke *method* mandiri. Untuk menjamin kompabilitas kompilasi manual/ant tanpa membuka NetBeans GUI Builder, kode *event listener* di-*inject* secara terprogram tepat setelah initComponents() di *constructor* View.
2. **Penghapusan Anonymous Inner Class**: Untuk menghindari error *NoClassDefFoundError* ($1.class, $2.class hilang) akibat proses kompilasi NetBeans yang gagal di tengah jalan (misal karena *JasperReports*), seluruh DefaultTableModel yang sebelumnya diinstansiasi secara *anonymous* telah diubah menjadi _named class_ (contoh: MyTableModel).
3. **Trailing Spaces pada ComboBox**: Saat mengisi nilai pada komponen JComboBox dengan data dari database (setSelectedItem), fungsi *trim()* diimplementasikan agresif lewat metode bantu setComboItem() untuk menangkal karakter *trailing spaces* siluman dari tipe data VARCHAR/CHAR MySQL yang sering menyebabkan item gagal terseleksi (terutama NIP Dosen).


## 5. Fitur Jadwal Lanjutan (Validasi & Layout)
1. **Perubahan Skema**: Tabel jadwal ditambahkan kolom kode_sesi_selesai untuk menampung batas akhir sesi berdasarkan SKS.
2. **Perhitungan Otomatis**: Ketika Matakuliah dan Sesi Mulai dipilih, sistem otomatis menghitung Sesi Selesai (kode_sesi_mulai + SKS - 1) dan menampilkannya pada kolom read-only \	xtJamSelesai\.
3. **Validasi Real-time Bentrok Jadwal**: Diterapkan \ItemStateChangeListener\ pada semua ComboBox (TA, Semester, Ruang, Hari, Sesi, Dosen, Mtk) untuk menjalankan validasi secara instan (\ealTimeValidation\). Jika rentang sesi bertabrakan dengan jadwal existing di ruang atau dosen yang sama pada hari yang sama, akan muncul \JOptionPane\ peringatan.
4. **Refactoring UI**: Tata letak vertikal (GroupLayout) dirombak total menjadi AbsoluteLayout (2 kolom horizontal) di \FrmJadwal.java\ agar form lebih proporsional.

