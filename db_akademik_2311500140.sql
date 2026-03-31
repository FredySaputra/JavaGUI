# Host: localhost  (Version 8.0.30)
# Date: 2026-03-31 09:02:28
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "detil_krs"
#

DROP TABLE IF EXISTS `detil_krs`;
CREATE TABLE `detil_krs` (
  `TA` varchar(9) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `Semester` varchar(8) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `NIM` char(10) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `KodeMTK` char(5) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`TA`,`Semester`,`NIM`,`KodeMTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

#
# Data for table "detil_krs"
#

INSERT INTO `detil_krs` VALUES ('2025-2026','GASAL','2311500111','KP001'),('2025-2026','GASAL','2311500111','KP061');

#
# Structure for table "krs"
#

DROP TABLE IF EXISTS `krs`;
CREATE TABLE `krs` (
  `TA` varchar(9) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `Semester` varchar(8) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `NIM` char(10) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `TglKRS` date DEFAULT NULL,
  PRIMARY KEY (`TA`,`Semester`,`NIM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

#
# Data for table "krs"
#

INSERT INTO `krs` VALUES ('2025-2026','GASAL','2311500111','2026-03-09'),('2025-2026','GENAP','2311500111','2026-08-14');

#
# Structure for table "mahasiswa"
#

DROP TABLE IF EXISTS `mahasiswa`;
CREATE TABLE `mahasiswa` (
  `NIM` char(10) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `Nama` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Alamat` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`NIM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

#
# Data for table "mahasiswa"
#

INSERT INTO `mahasiswa` VALUES ('2311500111','Aji ','Kebayoran');

#
# Structure for table "matakuliah"
#

DROP TABLE IF EXISTS `matakuliah`;
CREATE TABLE `matakuliah` (
  `KodeMTK` char(5) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `NamaMTK` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `SKS` int DEFAULT NULL,
  `KodePrasyarat` char(5) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`KodeMTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

#
# Data for table "matakuliah"
#

INSERT INTO `matakuliah` VALUES ('KP001','Algoritma dan Struktur Data 1',3,'KP002'),('KP002','IPBO',3,'KP061'),('KP061','Pemgoraman Berorientasi Objek',3,'KP001');

#
# Structure for table "periode"
#

DROP TABLE IF EXISTS `periode`;
CREATE TABLE `periode` (
  `TA` varchar(9) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `Semester` varchar(8) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`TA`,`Semester`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

#
# Data for table "periode"
#

INSERT INTO `periode` VALUES ('','GENAP'),('2025-2026','GASAL'),('2025-2026','GENAP'),('2025-2026','REMEDIAL'),('2026-2027','GASAL'),('2026-2027','GENAP'),('2026-2027','REMEDIAL');
