-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.13-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for yurt
CREATE DATABASE IF NOT EXISTS `yurt` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yurt`;

-- Dumping structure for table yurt.bina
CREATE TABLE IF NOT EXISTS `bina` (
  `bina_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `oda_sayisi` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adres` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`bina_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.bina: ~0 rows (approximately)
/*!40000 ALTER TABLE `bina` DISABLE KEYS */;
/*!40000 ALTER TABLE `bina` ENABLE KEYS */;

-- Dumping structure for table yurt.category
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.category: ~0 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table yurt.duyurlar_turu
CREATE TABLE IF NOT EXISTS `duyurlar_turu` (
  `tur_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `isim` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`tur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.duyurlar_turu: ~0 rows (approximately)
/*!40000 ALTER TABLE `duyurlar_turu` DISABLE KEYS */;
/*!40000 ALTER TABLE `duyurlar_turu` ENABLE KEYS */;

-- Dumping structure for table yurt.duyurular
CREATE TABLE IF NOT EXISTS `duyurular` (
  `duyuru_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bilgi` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `duyurlar_turu` int(10) unsigned NOT NULL,
  PRIMARY KEY (`duyuru_id`),
  KEY `duyuru_turu` (`duyurlar_turu`),
  CONSTRAINT `fk_duyur_tur` FOREIGN KEY (`duyurlar_turu`) REFERENCES `duyurlar_turu` (`tur_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.duyurular: ~0 rows (approximately)
/*!40000 ALTER TABLE `duyurular` DISABLE KEYS */;
/*!40000 ALTER TABLE `duyurular` ENABLE KEYS */;

-- Dumping structure for table yurt.kantin
CREATE TABLE IF NOT EXISTS `kantin` (
  `malzeme_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `isim` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tur_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`malzeme_id`),
  KEY `id_tur` (`tur_id`),
  CONSTRAINT `FK_kantin_k_melzeme_turu` FOREIGN KEY (`tur_id`) REFERENCES `kantin_tur` (`tur_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.kantin: ~0 rows (approximately)
/*!40000 ALTER TABLE `kantin` DISABLE KEYS */;
/*!40000 ALTER TABLE `kantin` ENABLE KEYS */;

-- Dumping structure for table yurt.kantin_tur
CREATE TABLE IF NOT EXISTS `kantin_tur` (
  `tur_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tur_isim` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`tur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.kantin_tur: ~0 rows (approximately)
/*!40000 ALTER TABLE `kantin_tur` DISABLE KEYS */;
/*!40000 ALTER TABLE `kantin_tur` ENABLE KEYS */;

-- Dumping structure for table yurt.kurslar
CREATE TABLE IF NOT EXISTS `kurslar` (
  `kurs_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kurs_adi` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `kurs_tarihi` year(4) NOT NULL,
  PRIMARY KEY (`kurs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.kurslar: ~0 rows (approximately)
/*!40000 ALTER TABLE `kurslar` DISABLE KEYS */;
/*!40000 ALTER TABLE `kurslar` ENABLE KEYS */;

-- Dumping structure for table yurt.kutuphane
CREATE TABLE IF NOT EXISTS `kutuphane` (
  `kitab_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kitab_adi` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `year` year(4) NOT NULL,
  `language_id` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`kitab_id`),
  KEY `FK_kutuphane_language` (`language_id`),
  CONSTRAINT `FK_kutuphane_language` FOREIGN KEY (`language_id`) REFERENCES `language` (`language_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.kutuphane: ~0 rows (approximately)
/*!40000 ALTER TABLE `kutuphane` DISABLE KEYS */;
/*!40000 ALTER TABLE `kutuphane` ENABLE KEYS */;

-- Dumping structure for table yurt.kutup_category
CREATE TABLE IF NOT EXISTS `kutup_category` (
  `kitab_id` int(3) unsigned NOT NULL,
  `category_id` int(3) unsigned NOT NULL,
  PRIMARY KEY (`kitab_id`,`category_id`),
  KEY `FK_many_categories` (`category_id`),
  CONSTRAINT `FK_many_categories` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_many_kutuphane` FOREIGN KEY (`kitab_id`) REFERENCES `kutuphane` (`kitab_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.kutup_category: ~0 rows (approximately)
/*!40000 ALTER TABLE `kutup_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `kutup_category` ENABLE KEYS */;

-- Dumping structure for table yurt.language
CREATE TABLE IF NOT EXISTS `language` (
  `language_id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.language: ~0 rows (approximately)
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
/*!40000 ALTER TABLE `language` ENABLE KEYS */;

-- Dumping structure for table yurt.odalar
CREATE TABLE IF NOT EXISTS `odalar` (
  `oda_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `oda_no` int(10) unsigned NOT NULL,
  `bina_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`oda_id`),
  KEY `FK_odalar_bina` (`bina_id`),
  CONSTRAINT `FK_odalar_bina` FOREIGN KEY (`bina_id`) REFERENCES `bina` (`bina_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.odalar: ~0 rows (approximately)
/*!40000 ALTER TABLE `odalar` DISABLE KEYS */;
/*!40000 ALTER TABLE `odalar` ENABLE KEYS */;

-- Dumping structure for table yurt.odemeler
CREATE TABLE IF NOT EXISTS `odemeler` (
  `odeme_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ogrenciAdi` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ucret` int(11) NOT NULL,
  PRIMARY KEY (`odeme_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.odemeler: ~0 rows (approximately)
/*!40000 ALTER TABLE `odemeler` DISABLE KEYS */;
/*!40000 ALTER TABLE `odemeler` ENABLE KEYS */;

-- Dumping structure for table yurt.ogrenci
CREATE TABLE IF NOT EXISTS `ogrenci` (
  `ogr_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `adi` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `soyadi` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `oda_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`ogr_id`),
  KEY `oda` (`oda_id`),
  CONSTRAINT `FK_ogr_oda` FOREIGN KEY (`oda_id`) REFERENCES `odalar` (`oda_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.ogrenci: ~0 rows (approximately)
/*!40000 ALTER TABLE `ogrenci` DISABLE KEYS */;
/*!40000 ALTER TABLE `ogrenci` ENABLE KEYS */;

-- Dumping structure for table yurt.personel
CREATE TABLE IF NOT EXISTS `personel` (
  `personel_id` int(11) NOT NULL AUTO_INCREMENT,
  `isim` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `soyad` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tc` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tur_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`personel_id`),
  KEY `tur` (`tur_id`),
  CONSTRAINT `FK_personel_personel_tur` FOREIGN KEY (`tur_id`) REFERENCES `personel_tur` (`tur_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.personel: ~0 rows (approximately)
/*!40000 ALTER TABLE `personel` DISABLE KEYS */;
/*!40000 ALTER TABLE `personel` ENABLE KEYS */;

-- Dumping structure for table yurt.personel_tur
CREATE TABLE IF NOT EXISTS `personel_tur` (
  `tur_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tur_ismi` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`tur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.personel_tur: ~0 rows (approximately)
/*!40000 ALTER TABLE `personel_tur` DISABLE KEYS */;
/*!40000 ALTER TABLE `personel_tur` ENABLE KEYS */;

-- Dumping structure for table yurt.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uname` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `userTuru` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `c` int(11) DEFAULT NULL,
  `r` int(11) DEFAULT NULL,
  `u` int(11) DEFAULT NULL,
  `d` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.user: ~4 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `uname`, `password`, `userTuru`, `c`, `r`, `u`, `d`) VALUES
	(24, 'mmmm', '12345', 'Admin', 0, 0, 0, 0),
	(25, 'onur', '12345', 'Admin', NULL, NULL, NULL, NULL),
	(26, 'onur', '12345', 'User', NULL, NULL, NULL, NULL),
	(27, 'munir', '12345', 'User', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table yurt.yemek
CREATE TABLE IF NOT EXISTS `yemek` (
  `ek_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ek_isim` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ek_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table yurt.yemek: ~0 rows (approximately)
/*!40000 ALTER TABLE `yemek` DISABLE KEYS */;
/*!40000 ALTER TABLE `yemek` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
