DROP DATABASE IF EXISTS `ecoteh`;
CREATE DATABASE IF NOT EXISTS `ecoteh` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ecoteh`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: ecoteh
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`       VARCHAR(200)     NOT NULL,
  `url`         VARCHAR(200)     NOT NULL,
  `number`      VARCHAR(100)     NOT NULL,
  `description` TEXT,
  `text`        TEXT,
  `keywords`    TEXT,
  `date`        VARCHAR(30)      NOT NULL,
  `category_id` INT(10) UNSIGNED          DEFAULT NULL,
  `validated`   BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`),
  UNIQUE KEY `url` (`url`),
  KEY `FK7i4rryg7kqwyyrr08temnc71e` (`category_id`),
  CONSTRAINT `FK7i4rryg7kqwyyrr08temnc71e` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`       VARCHAR(200)     NOT NULL,
  `url`         VARCHAR(200)     NOT NULL,
  `description` TEXT,
  `keywords`    TEXT             NOT NULL,
  `photo`       VARCHAR(200)              DEFAULT NULL,
  `validated`   BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  UNIQUE KEY `url` (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies` (
  `id`             INT(10) UNSIGNED         NOT NULL AUTO_INCREMENT,
  `type`           ENUM ('MAIN', 'PARTNER') NOT NULL,
  `title`          VARCHAR(100)             NOT NULL,
  `domain`         VARCHAR(200)                      DEFAULT NULL,
  `url`            VARCHAR(200)                      DEFAULT NULL,
  `tagline`        TEXT,
  `description`    TEXT,
  `information`    TEXT,
  `keywords`       TEXT,
  `mobile_phone`   VARCHAR(100)                      DEFAULT NULL,
  `landline_phone` VARCHAR(100)                      DEFAULT NULL,
  `fax`            VARCHAR(100)                      DEFAULT NULL,
  `email`          VARCHAR(200)                      DEFAULT NULL,
  `sender_email`   VARCHAR(200)                      DEFAULT NULL,
  `sender_pass`    VARCHAR(100)                      DEFAULT NULL,
  `vkontakte`      VARCHAR(200)                      DEFAULT NULL,
  `facebook`       VARCHAR(200)                      DEFAULT NULL,
  `twitter`        VARCHAR(200)                      DEFAULT NULL,
  `skype`          VARCHAR(100)                      DEFAULT NULL,
  `address`        VARCHAR(300)                      DEFAULT NULL,
  `work_time_from` VARCHAR(10)                       DEFAULT NULL,
  `work_time_to`   VARCHAR(10)                       DEFAULT NULL,
  `google_maps`    TEXT,
  `logo`           VARCHAR(200)                      DEFAULT NULL,
  `favicon`        VARCHAR(200)                      DEFAULT NULL,
  `slides`         TEXT,
  `validated`      BIT(1)                   NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  UNIQUE KEY `url` (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id`        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`     VARCHAR(100)              DEFAULT NULL,
  `url`       VARCHAR(200)     NOT NULL,
  `validated` BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `responses`
--

DROP TABLE IF EXISTS `responses`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responses` (
  `id`        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username`  VARCHAR(100)     NOT NULL,
  `text`      TEXT             NOT NULL,
  `date`      VARCHAR(30)      NOT NULL,
  `validated` BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(100)     NOT NULL,
  `url`         VARCHAR(100)     NOT NULL,
  `login`       VARCHAR(300)              DEFAULT NULL,
  `password`    VARCHAR(300)              DEFAULT NULL,
  `email`       VARCHAR(100)     NOT NULL,
  `phone`       VARCHAR(100)     NOT NULL,
  `vkontakte`   VARCHAR(200)              DEFAULT NULL,
  `facebook`    VARCHAR(200)              DEFAULT NULL,
  `twitter`     VARCHAR(200)              DEFAULT NULL,
  `skype`       VARCHAR(100)              DEFAULT NULL,
  `description` TEXT,
  `photo`       VARCHAR(100)              DEFAULT NULL,
  `role`        VARCHAR(100)     NOT NULL,
  `validated`   BIT(1)           NOT NULL DEFAULT b'1',
  `mailing`     BIT(1)           NOT NULL DEFAULT b'1',
  `locked`      BIT(1)           NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `password` (`password`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2017-02-13 12:31:07

