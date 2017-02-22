CREATE DATABASE  IF NOT EXISTS `ecoteh` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ecoteh`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: ecoteh
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(300) NOT NULL DEFAULT '',
  `google_maps` text NOT NULL,
  `validated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_category` int(10) unsigned DEFAULT NULL,
  `title` varchar(200) NOT NULL DEFAULT '',
  `url` varchar(200) NOT NULL DEFAULT '',
  `number` varchar(100) NOT NULL DEFAULT '',
  `description` text NOT NULL,
  `text` text NOT NULL,
  `keywords` text NOT NULL,
  `date` varchar(30) NOT NULL DEFAULT '',
  `validated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`),
  UNIQUE KEY `url` (`url`),
  KEY `FK7i4rryg7kqwyyrr08temnc71e` (`id_category`),
  CONSTRAINT `FK7i4rryg7kqwyyrr08temnc71e` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKhwfil8cjqxoipv99opeq8x2np` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`),
  CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL DEFAULT '',
  `url` varchar(200) NOT NULL DEFAULT '',
  `description` text NOT NULL,
  `keywords` text NOT NULL,
  `photo` varchar(200) NOT NULL DEFAULT '',
  `validated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_contacts` int(10) unsigned DEFAULT NULL,
  `id_address` int(10) unsigned DEFAULT NULL,
  `type` enum('MAIN','PARTNER') NOT NULL DEFAULT 'PARTNER',
  `title` varchar(100) NOT NULL DEFAULT '',
  `domain` varchar(200) NOT NULL DEFAULT '',
  `url` varchar(200) NOT NULL DEFAULT '',
  `tagline` text NOT NULL,
  `description` text NOT NULL,
  `information` text NOT NULL,
  `keywords` text NOT NULL,
  `sender_email` varchar(200) NOT NULL DEFAULT '',
  `sender_pass` varchar(100) NOT NULL DEFAULT '',
  `work_time_from` varchar(10) NOT NULL DEFAULT '',
  `work_time_to` varchar(10) NOT NULL DEFAULT '',
  `logo` varchar(200) NOT NULL DEFAULT '',
  `favicon` varchar(200) NOT NULL DEFAULT '',
  `validated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  UNIQUE KEY `url` (`url`),
  KEY `FKm3v3tb7ksypjb9p8i7x28jh3t` (`id_address`),
  KEY `FK13ugl6ne6gmtegfy0efpwyvgg` (`id_contacts`),
  CONSTRAINT `FK13ugl6ne6gmtegfy0efpwyvgg` FOREIGN KEY (`id_contacts`) REFERENCES `contacts` (`id`),
  CONSTRAINT `FKm3v3tb7ksypjb9p8i7x28jh3t` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id`),
  CONSTRAINT `companies_ibfk_1` FOREIGN KEY (`id_contacts`) REFERENCES `contacts` (`id`),
  CONSTRAINT `companies_ibfk_2` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mobile_phone` varchar(100) NOT NULL DEFAULT '',
  `landline_phone` varchar(100) NOT NULL DEFAULT '',
  `fax` varchar(100) NOT NULL DEFAULT '',
  `email` varchar(100) NOT NULL DEFAULT '',
  `vkontakte` varchar(200) NOT NULL DEFAULT '',
  `facebook` varchar(200) NOT NULL DEFAULT '',
  `twitter` varchar(200) NOT NULL DEFAULT '',
  `skype` varchar(100) NOT NULL DEFAULT '',
  `validated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `type` enum('STATIC','SLIDE','OTHER') NOT NULL DEFAULT 'OTHER',
  `url` varchar(200) NOT NULL DEFAULT '',
  `validated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_user` int(10) unsigned NOT NULL,
  `subject` varchar(100) NOT NULL DEFAULT '',
  `text` text NOT NULL,
  `date` varchar(30) NOT NULL DEFAULT '',
  `validated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `FK5956sop4d9xepfqwxe5q01uly` (`id_user`),
  CONSTRAINT `FK5956sop4d9xepfqwxe5q01uly` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `responses`
--

DROP TABLE IF EXISTS `responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responses` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL DEFAULT '',
  `text` text NOT NULL,
  `date` varchar(30) NOT NULL DEFAULT '',
  `validated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_contacts` int(10) unsigned DEFAULT NULL,
  `id_photo` int(10) unsigned DEFAULT NULL,
  `role` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) NOT NULL DEFAULT '',
  `url` varchar(100) NOT NULL DEFAULT '',
  `login` varchar(300) NOT NULL DEFAULT '',
  `password` varchar(300) NOT NULL DEFAULT '',
  `description` text NOT NULL,
  `validated` bit(1) NOT NULL DEFAULT b'1',
  `mailing` bit(1) NOT NULL DEFAULT b'1',
  `locked` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `password` (`password`),
  KEY `FK85x6pdqrmu9n51vykkortx3ao` (`id_contacts`),
  KEY `FK56eb0cjr9ihsahgglyoxt6beu` (`id_photo`),
  CONSTRAINT `FK56eb0cjr9ihsahgglyoxt6beu` FOREIGN KEY (`id_photo`) REFERENCES `files` (`id`),
  CONSTRAINT `FK85x6pdqrmu9n51vykkortx3ao` FOREIGN KEY (`id_contacts`) REFERENCES `contacts` (`id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_contacts`) REFERENCES `contacts` (`id`),
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`id_photo`) REFERENCES `files` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-22 12:26:02
