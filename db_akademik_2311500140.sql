-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2026 at 09:28 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_akademik_2311500140`
--

-- --------------------------------------------------------

--
-- Table structure for table `detil_krs`
--

CREATE TABLE `detil_krs` (
  `TA` varchar(9) NOT NULL DEFAULT '',
  `Semester` varchar(8) NOT NULL DEFAULT '',
  `NIM` char(10) NOT NULL DEFAULT '',
  `KodeMTK` char(5) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detil_krs`
--

INSERT INTO `detil_krs` (`TA`, `Semester`, `NIM`, `KodeMTK`) VALUES
('2025-2026', 'GASAL', '2311500111', 'KP001'),
('2025-2026', 'GASAL', '2311500111', 'KP061'),
('2025-2026', 'GASAL', '2311500140', 'KP001'),
('2025-2026', 'GASAL', '2311500140', 'KP002'),
('2025-2026', 'GENAP', '2311500140', 'KP001'),
('2025-2026', 'GENAP', '2311500140', 'KP002');

-- --------------------------------------------------------

--
-- Table structure for table `krs`
--

CREATE TABLE `krs` (
  `TA` varchar(9) NOT NULL DEFAULT '',
  `Semester` varchar(8) NOT NULL DEFAULT '',
  `NIM` char(10) NOT NULL DEFAULT '',
  `TglKRS` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `krs`
--

INSERT INTO `krs` (`TA`, `Semester`, `NIM`, `TglKRS`) VALUES
('-Pilih-', '-Pilih-', '2311500140', '2026-05-25'),
('2025-2026', 'GASAL', '2311500111', '2026-03-09'),
('2025-2026', 'GASAL', '2311500140', '2026-05-25'),
('2025-2026', 'GENAP', '2311500111', '2026-08-14'),
('2025-2026', 'GENAP', '2311500140', '2026-05-25');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `NIM` char(10) NOT NULL DEFAULT '',
  `Nama` varchar(50) DEFAULT NULL,
  `Alamat` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`NIM`, `Nama`, `Alamat`) VALUES
('2311500140', 'Fredy Dwi Saputra', 'Kebayoran Lama');

-- --------------------------------------------------------

--
-- Table structure for table `matakuliah`
--

CREATE TABLE `matakuliah` (
  `KodeMTK` char(5) NOT NULL DEFAULT '',
  `NamaMTK` varchar(50) DEFAULT NULL,
  `SKS` int(11) DEFAULT NULL,
  `KodePrasyarat` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matakuliah`
--

INSERT INTO `matakuliah` (`KodeMTK`, `NamaMTK`, `SKS`, `KodePrasyarat`) VALUES
('KP001', 'Algoritma dan Struktur Data 1', 3, 'KP002'),
('KP002', 'IPBO', 3, 'KP061'),
('KP061', 'Pemgoraman Berorientasi Objek', 3, 'KP001');

-- --------------------------------------------------------

--
-- Table structure for table `periode`
--

CREATE TABLE `periode` (
  `TA` varchar(9) NOT NULL DEFAULT '',
  `Semester` varchar(8) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `periode`
--

INSERT INTO `periode` (`TA`, `Semester`) VALUES
('', 'GENAP'),
('2025-2026', 'GASAL'),
('2025-2026', 'GENAP'),
('2025-2026', 'REMEDIAL'),
('2026-2027', 'GASAL');

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `nip` varchar(20) NOT NULL,
  `nama_dosen` varchar(100) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`nip`, `nama_dosen`, `no_hp`) VALUES
('10012 ', 'M. Anif', '0818849849 '),
('10013', 'Yuliazmi', '0818766252 ');

-- --------------------------------------------------------

--
-- Table structure for table `ruang`
--

CREATE TABLE `ruang` (
  `kode_ruang` varchar(10) NOT NULL,
  `keterangan` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ruang`
--

INSERT INTO `ruang` (`kode_ruang`, `keterangan`) VALUES
('211 ', 'Unit 2, Lantai 1, Ruang ke 1	'),
('431 ', 'Unit 4, Lantai 3, Ruang ke 1	');

-- --------------------------------------------------------

--
-- Table structure for table `sesi`
--

CREATE TABLE `sesi` (
  `kode_sesi` int(11) NOT NULL,
  `jam_mulai` varchar(5) DEFAULT NULL,
  `jam_selesai` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sesi`
--

INSERT INTO `sesi` (`kode_sesi`, `jam_mulai`, `jam_selesai`) VALUES
(0, '07:05', '07:55'),
(1, '08:00', '08:50'),
(2, '08:50', '09:40'),
(3, '09:45', '10:35'),
(4, '10:40', '11:30'),
(5, '11:35', '12:25'),
(6, '12:30', '13:20'),
(7, '13:25', '14:15'),
(8, '14:20', '15:10'),
(9, '15:15', '16:05'),
(10, '16:10', '17:00'),
(11, '17:05', '17:55'),
(12, '18:00', '18:50'),
(13, '18:55', '19:45'),
(14, '19:50', '20:40'),
(15, '20:45', '21:35');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detil_krs`
--
ALTER TABLE `detil_krs`
  ADD PRIMARY KEY (`TA`,`Semester`,`NIM`,`KodeMTK`);

--
-- Indexes for table `krs`
--
ALTER TABLE `krs`
  ADD PRIMARY KEY (`TA`,`Semester`,`NIM`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`NIM`);

--
-- Indexes for table `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`KodeMTK`);

--
-- Indexes for table `periode`
--
ALTER TABLE `periode`
  ADD PRIMARY KEY (`TA`,`Semester`);

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `ruang`
--
ALTER TABLE `ruang`
  ADD PRIMARY KEY (`kode_ruang`);

--
-- Indexes for table `sesi`
--
ALTER TABLE `sesi`
  ADD PRIMARY KEY (`kode_sesi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
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
