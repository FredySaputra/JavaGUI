# Panduan Implementasi Layout Form KPRS (NetBeans 8.2 GUI Builder)

## 1. Pendekatan Layout (Saran Utama)
Gunakan `AbsoluteLayout` pada container utama (JFrame/JPanel utama) untuk presisi ukuran, atau bagi layar menjadi 4 `JPanel` yang disusun vertikal secara berurutan.

## 2. Pemetaan Komponen per Zona (Dari Atas ke Bawah)

### Zona 1: Judul Form
*   **Komponen**: `JLabel` atau `JPanel` dengan warna latar belakang oranye/kuning gelap.
*   **Teks**: "Form Entri Data KPRS"
*   **Posisi**: Membentang penuh di bagian paling atas.

### Zona 2: Header KPRS (Informasi Dasar)
Zona ini terbagi menjadi dua kolom utama (kiri dan kanan).

*   **Baris 1:**
    *   (Kiri) `JLabel` "TA :" bersebelahan dengan `JComboBox` -> Nama variabel: `cmbTA`
    *   (Kanan) `JLabel` "Tgl. KPRS :" bersebelahan dengan `JTextField` -> Nama variabel: `txtTglKPRS`
*   **Baris 2:**
    *   (Kiri) `JLabel` "Semester :" bersebelahan dengan `JComboBox` -> Nama variabel: `cmbSemester`
    *   (Kanan) `JLabel` "NIM :" bersebelahan dengan DUA `JTextField`.
        *   `JTextField` pertama (pendek, untuk input NIM) -> Nama variabel: `txtNim`
        *   `JTextField` kedua (panjang, untuk menampilkan Nama Mahasiswa) -> Nama variabel: `txtNamaMhs` (Wajib diset: `editable = false`)

### Zona 3: Input Detail Matakuliah
Zona ini disusun dalam satu baris horizontal. Letakkan `JLabel` di atas komponen inputnya masing-masing.

*   **Kolom 1:** `JLabel` "Kode Mtk :" berada di atas `JComboBox` -> Nama variabel: `cmbKodeMtk`
*   **Kolom 2:** `JLabel` "Nama Matakuliah :" berada di atas `JTextField` -> Nama variabel: `txtNamaMtk` (Wajib diset: `editable = false`)
*   **Kolom 3:** `JLabel` "SKS :" berada di atas `JTextField` -> Nama variabel: `txtSks` (Wajib diset: `editable = false`)
*   **Kolom 4:** `JLabel` "Kode Prasyarat" berada di atas `JTextField` -> Nama variabel: `txtPrasyarat` (Wajib diset: `editable = false`)
*   **Kolom 5:** `JLabel` "Kelompok :" berada di atas `JComboBox` -> Nama variabel: `cmbKelompok`

### Zona 4: Tombol Aksi (Action Buttons)
Disusun secara horizontal di bawah Zona 3. Jarak antar tombol dibuat seragam, kecuali tombol "Selesai" yang diletakkan rata kanan (align right).
*   `JButton` "Tambah" -> Nama variabel: `btnTambah`
*   `JButton` "Ubah" -> Nama variabel: `btnUbah`
*   `JButton` "Hapus" -> Nama variabel: `btnHapus`
*   `JButton` "Bersih" -> Nama variabel: `btnBersih`
*   (Spasi kosong/Gap)
*   `JButton` "Selesai" -> Nama variabel: `btnSelesai`

### Zona 5: Tabel Data
Membentang penuh di bagian paling bawah form.
*   **Komponen**: `JTable` di dalam `JScrollPane`.
*   **Nama variabel JTable**: `tblKPRS`
*   **Kolom Tabel (berurutan dari kiri ke kanan)**: 
    1. Kode Mtk
    2. Nama Matakuliah
    3. SKS
    4. Kode Prasyarat
    5. Kelompok
*   **Catatan Kritis untuk AI Agent**: JANGAN gunakan default/anonymous inner class untuk *Table Model* hasil *generate* NetBeans. Buat *named class* (misal: `KPRSTableModel`) yang inherit dari `DefaultTableModel` lalu set model tersebut pada konstruktor form setelah `initComponents()`.

## 3. Catatan Properti Khusus
1. Berikan border (garis batas) luar berwarna hitam tebal pada keseluruhan form untuk menyamai desain referensi.
2. Warna header pada kolom JTable harus disesuaikan menjadi kuning/oranye menggunakan kustomisasi `DefaultTableCellRenderer` pada `JTableHeader` (jika memungkinkan).