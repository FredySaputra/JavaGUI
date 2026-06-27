# Spesifikasi Pengembangan Fitur: Transaksi KPRS

**Status Dokumen**: Wajib Dieksekusi Secara Berurutan (Phase 1 s/d Phase 4).
**Target Lingkungan**: Java 8, NetBeans IDE 8.2, MySQL.

## Phase 1: Migrasi Database
Skema *database* saat ini belum memiliki tabel untuk KPRS. Eksekusi *query* berikut sebelum membuat kelas Java apa pun. Dilarang mengubah tabel lama.

CREATE TABLE `kprs` (
  `TA` varchar(9) NOT NULL,
  `Semester` varchar(8) NOT NULL,
  `NIM` char(10) NOT NULL,
  `TglKPRS` date DEFAULT NULL,
  PRIMARY KEY (`TA`,`Semester`,`NIM`),
  CONSTRAINT `fk_kprs_mhs` FOREIGN KEY (`NIM`) REFERENCES `mahasiswa` (`NIM`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `detil_kprs` (
  `TA` varchar(9) NOT NULL,
  `Semester` varchar(8) NOT NULL,
  `NIM` char(10) NOT NULL,
  `KodeMTK` char(5) NOT NULL,
  `kelompok` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`TA`,`Semester`,`NIM`,`KodeMTK`),
  CONSTRAINT `fk_dkprs_kprs` FOREIGN KEY (`TA`, `Semester`, `NIM`) REFERENCES `kprs` (`TA`, `Semester`, `NIM`) ON UPDATE CASCADE,
  CONSTRAINT `fk_dkprs_mtk` FOREIGN KEY (`KodeMTK`) REFERENCES `matakuliah` (`KodeMTK`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

## Phase 2: Pembuatan Model dan DAO
Buat komponen berikut sesuai standar proyek `AGENTS.md`:

1. **Model**: Buat `varKPRS.java` dan `varDetilKPRS.java` dengan properti yang memetakan tabel di Phase 1. Tambahkan fungsi *getter* dan *setter*.
2. **DAO**: Buat `DAO_KPRS.java` (implementasi `DAO_Interface`).
   * Wajib menangani *query* penyalinan dari `krs` ke `kprs` (satu *query* INSERT SELECT).
   * Wajib menangani penyalinan dari `detil_krs` ke `detil_kprs`. Pastikan kolom `kelompok` diset *NULL* atau string kosong saat penyalinan awal.

## Phase 3: Logika Bisnis (Controller_KPRS.java)
Logika utama sistem berada di kelas ini. Implementasikan logika secara prosedural untuk *event* berikut:

### A. Event "Enter" pada Field NIM
Saat tombol *Enter* ditekan pada `txtNim` (`KeyPressed` event menangkap `VK_ENTER`):
1. **Validasi Mahasiswa**: *Query* ke tabel `mahasiswa` (`SELECT Nama FROM mahasiswa WHERE NIM = ?`). Gunakan `.trim()` pada parameter pencarian.
   * Jika gagal (tidak ditemukan) -> Tampilkan `JOptionPane`: "Mahasiswa tidak terdaftar", hentikan eksekusi, bersihkan form.
2. **Validasi KPRS Eksisting**: Cek tabel `kprs`.
   * Jika ada -> Langsung `SELECT` dari `detil_kprs` dan tampilkan ke `JTable`.
3. **Proses Copy dari KRS** (Jika langkah 2 kosong): Cek tabel `krs`.
   * Jika `krs` ada -> Eksekusi DAO untuk simpan *header* KPRS (tanggal hari ini) -> Eksekusi DAO untuk *copy* `detil_krs` ke `detil_kprs` -> Tampilkan ke `JTable`.
   * Jika `krs` kosong -> Tampilkan pesan "Data KRS tidak ditemukan. Mahasiswa belum mengisi KRS."

### B. Pengecekan Bentrok Jadwal (Event Tombol Tambah/Ubah)
Pengecekan bentrok wajib membandingkan waktu irisan jadwal baru yang akan diinput dengan seluruh jadwal yang sudah tersimpan di `detil_kprs` mahasiswa tersebut. 

Rumus irisan interval yang digunakan:
(SesiMulai_Baru <= SesiSelesai_Lama) AND (SesiSelesai_Baru >= SesiMulai_Lama)

**Implementasi Cek Bentrok via Database (Direkomendasikan):**
Gunakan *query* SQL ini di dalam metode DAO `cekBentrokJadwal(...)`. *Query* ini akan mencari apakah ada jadwal lama (yang sudah di KPRS) yang bersinggungan hari dan sesinya dengan jadwal baru yang mau dipilih.

SELECT j_lama.kode_mtk, mk.NamaMTK, j_lama.hari 
FROM jadwal j_lama
JOIN detil_kprs dk ON j_lama.kode_mtk = dk.KodeMTK AND j_lama.kelompok = dk.kelompok
JOIN matakuliah mk ON j_lama.kode_mtk = mk.KodeMTK
WHERE dk.NIM = ? 
  AND dk.TA = ? 
  AND dk.Semester = ?
  AND j_lama.hari = ? -- Parameter Hari dari jadwal mata kuliah baru
  AND ( ? <= j_lama.kode_sesi_selesai ) -- Parameter Sesi Mulai jadwal baru
  AND ( ? >= j_lama.kode_sesi )         -- Parameter Sesi Selesai jadwal baru
  AND j_lama.kode_mtk != ?;             -- Abaikan MTK yang sedang diedit (jika proses Ubah)

* Jika *query* ini mengembalikan data (ada *ResultSet*), artinya **BENTROK**. Tampilkan `JOptionPane`: "Bentrok dengan mata kuliah: [NamaMTK] pada hari [Hari]". Batalkan proses simpan/ubah.
* Jika *ResultSet* kosong, jadwal aman. Lanjutkan proses *Update*/*Insert*.

## Phase 4: Spesifikasi View (FrmKPRS.java)
1. **Form Builder**: Desain GUI di NetBeans mengikuti referensi dengan ketat. Jangan memaksakan `GroupLayout` jika sulit dikendalikan, gunakan `AbsoluteLayout`.
2. **Model Tabel**: Buat kelas khusus untuk model tabel (contoh: `KPRSTableModel extends DefaultTableModel`), DILARANG menggunakan *anonymous inner class*. Inisialisasi model ini di dalam konstruktor.
3. **Injeksi Event**: Pasang `KeyListener` untuk NIM dan `ActionListener` untuk tombol Tambah/Ubah tepat di bawah panggilan `initComponents()`. Dilarang mengklik *event* langsung dari UI Designer NetBeans untuk logika *database*.
4. **Karakter Spasi**: Nilai yang diambil dari *JComboBox* (KodeMTK, Kelompok, TA, Semester) wajib di-*parse* dengan `.toString().trim()` sebelum dikirim ke parameter metode Controller/DAO.