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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `address`     VARCHAR(300)     NOT NULL DEFAULT '',
  `google_maps` TEXT             NOT NULL DEFAULT '',
  `validated`   BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `logo_id`     INT(10) UNSIGNED          DEFAULT NULL,
  `category_id` INT(10) UNSIGNED          DEFAULT NULL,
  `title`       VARCHAR(200)     NOT NULL DEFAULT '',
  `url`         VARCHAR(200)     NOT NULL DEFAULT '',
  `number`      VARCHAR(100)     NOT NULL DEFAULT '',
  `description` TEXT             NOT NULL DEFAULT '',
  `text`        TEXT             NOT NULL DEFAULT '',
  `keywords`    TEXT             NOT NULL DEFAULT '',
  `date`        VARCHAR(30)      NOT NULL DEFAULT '',
  `validated`   BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE (`number`, `url`),
  FOREIGN KEY (`logo_id`) REFERENCES `files` (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)

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
  `logo_id`     INT(10) UNSIGNED          DEFAULT NULL,
  `title`       VARCHAR(200)     NOT NULL DEFAULT '',
  `url`         VARCHAR(200)     NOT NULL DEFAULT '',
  `description` TEXT             NOT NULL DEFAULT '',
  `keywords`    TEXT             NOT NULL DEFAULT '',
  `validated`   BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE (`title`, `url`),
  FOREIGN KEY (`logo_id`) REFERENCES `files` (`id`)
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
  `logo_id`        INT(10) UNSIGNED                  DEFAULT NULL,
  `contacts_id`    INT(10) UNSIGNED                  DEFAULT NULL,
  `address_id`     INT(10) UNSIGNED                  DEFAULT NULL,
  `type`           ENUM ('MAIN', 'PARTNER') NOT NULL DEFAULT 'PARTNER',
  `title`          VARCHAR(100)             NOT NULL DEFAULT '',
  `domain`         VARCHAR(200)             NOT NULL DEFAULT '',
  `url`            VARCHAR(200)             NOT NULL DEFAULT '',
  `tagline`        TEXT                     NOT NULL DEFAULT '',
  `description`    TEXT                     NOT NULL DEFAULT '',
  `information`    TEXT                     NOT NULL DEFAULT '',
  `keywords`       TEXT                     NOT NULL DEFAULT '',
  `sender_email`   VARCHAR(200)             NOT NULL DEFAULT '',
  `sender_pass`    VARCHAR(100)             NOT NULL DEFAULT '',
  `work_time_from` VARCHAR(10)              NOT NULL DEFAULT '',
  `work_time_to`   VARCHAR(10)              NOT NULL DEFAULT '',
  `validated`      BIT(1)                   NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE (`title`, `url`),
  FOREIGN KEY (`logo_id`) REFERENCES `files` (`id`),
  FOREIGN KEY (`contacts_id`) REFERENCES `contacts` (`id`),
  FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `id`             INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `mobile_phone`   VARCHAR(100)     NOT NULL DEFAULT '',
  `landline_phone` VARCHAR(100)     NOT NULL DEFAULT '',
  `fax`            VARCHAR(100)     NOT NULL DEFAULT '',
  `email`          VARCHAR(100)     NOT NULL DEFAULT '',
  `vkontakte`      VARCHAR(200)     NOT NULL DEFAULT '',
  `facebook`       VARCHAR(200)     NOT NULL DEFAULT '',
  `twitter`        VARCHAR(200)     NOT NULL DEFAULT '',
  `skype`          VARCHAR(100)     NOT NULL DEFAULT '',
  `validated`      BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
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
  `id`        INT(10) UNSIGNED                             NOT NULL AUTO_INCREMENT,
  `title`     VARCHAR(100)                                 NOT NULL DEFAULT '',
  `type`      ENUM ('FAVICON', 'STATIC', 'SLIDE', 'OTHER') NOT NULL DEFAULT 'OTHER',
  `url`       VARCHAR(200)                                 NOT NULL DEFAULT '',
  `validated` BIT(1)                                       NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id`        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id`   INT(10) UNSIGNED NOT NULL,
  `subject`   VARCHAR(100)     NOT NULL DEFAULT '',
  `text`      TEXT             NOT NULL DEFAULT '',
  `date`      VARCHAR(30)      NOT NULL DEFAULT '',
  `validated` BIT(1)           NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
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
  `username`  VARCHAR(100)     NOT NULL DEFAULT '',
  `text`      TEXT             NOT NULL DEFAULT '',
  `date`      VARCHAR(30)      NOT NULL DEFAULT '',
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
  `id`          INT(10) UNSIGNED                    NOT NULL AUTO_INCREMENT,
  `photo_id`    INT(10) UNSIGNED                             DEFAULT NULL,
  `contacts_id` INT(10) UNSIGNED                             DEFAULT NULL,
  `role`        ENUM ('ADMIN', 'CLIENT', 'ANOTHER') NOT NULL DEFAULT 'ANOTHER',
  `name`        VARCHAR(100)                        NOT NULL DEFAULT '',
  `url`         VARCHAR(100)                        NOT NULL DEFAULT '',
  `login`       VARCHAR(300)                        NOT NULL DEFAULT '',
  `password`    VARCHAR(300)                        NOT NULL DEFAULT '',
  `description` TEXT                                NOT NULL,
  `validated`   BIT(1)                              NOT NULL DEFAULT b'1',
  `mailing`     BIT(1)                              NOT NULL DEFAULT b'1',
  `locked`      BIT(1)                              NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `files` (`id`),
  FOREIGN KEY (`contacts_id`) REFERENCES `contacts` (`id`)
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

-- Dump completed on 2017-02-22 12:26:02
