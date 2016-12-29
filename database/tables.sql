USE ecoteh;

/*--- 1) Photos -------------------------------------------------------------------------*/

CREATE TABLE `photos` (
  `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`    VARCHAR(100)          DEFAULT NULL,
  `url`      VARCHAR(200) NOT NULL,
  `is_valid` BIT(1)       NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 2) Videos --------------------------------------------------------------------------*/

CREATE TABLE `videos` (
  `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`    VARCHAR(100)          DEFAULT NULL,
  `url`      VARCHAR(200) NOT NULL,
  `is_valid` BIT(1)       NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 3) Users -------------------------------------------------------------------------------*/

CREATE TABLE `users` (
  `id`          INT UNSIGNED                        NOT NULL  AUTO_INCREMENT,
  `name`        VARCHAR(100)                        NOT NULL,
  `url`         VARCHAR(100)                        NOT NULL,
  `login`       VARCHAR(300)                                  DEFAULT NULL,
  `password`    VARCHAR(300)                                  DEFAULT NULL,
  `email`       VARCHAR(100)                        NOT NULL,
  `phone`       VARCHAR(100)                        NOT NULL,
  `vkontakte`   VARCHAR(200)                                  DEFAULT NULL,
  `facebook`    VARCHAR(200)                                  DEFAULT NULL,
  `twitter`     VARCHAR(200)                                  DEFAULT NULL,
  `skype`       VARCHAR(100)                                  DEFAULT NULL,
  `description` TEXT                                          DEFAULT NULL,
  `photo_id`    INT UNSIGNED                                  DEFAULT NULL,
  `role`        ENUM ('ADMIN', 'CLIENT', 'ANOTHER') NOT NULL,
  `is_valid`    BIT(1)                              NOT NULL  DEFAULT 1,
  `is_mailing`  BIT(1)                              NOT NULL  DEFAULT 1,
  `is_locked`   BIT(1)                              NOT NULL  DEFAULT 0,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`),
  UNIQUE (`url`),
  UNIQUE (`login`),
  UNIQUE (`password`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 4) Sections -------------------------------------------------------------------------------*/

CREATE TABLE `sections` (
  `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`       VARCHAR(200) NOT NULL,
  `url`         VARCHAR(200) NOT NULL,
  `description` TEXT                  DEFAULT NULL,
  `keywords`    TEXT         NOT NULL,
  `photo_id`    INT UNSIGNED          DEFAULT NULL,
  `is_valid`    BIT(1)       NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`),
  UNIQUE (`title`),
  UNIQUE (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 5) Categories -------------------------------------------------------------------------------*/

CREATE TABLE `categories` (
  `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`       VARCHAR(200) NOT NULL,
  `url`         VARCHAR(200) NOT NULL,
  `description` TEXT                  DEFAULT NULL,
  `keywords`    TEXT         NOT NULL,
  `photo_id`    INT UNSIGNED          DEFAULT NULL,
  `section_id`  INT UNSIGNED          DEFAULT NULL,
  `is_valid`    BIT(1)       NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`),
  FOREIGN KEY (`section_id`) REFERENCES `sections` (`id`),
  UNIQUE (`title`),
  UNIQUE (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 6) Articles -------------------------------------------------------------------------------*/

CREATE TABLE `articles` (
  `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`         VARCHAR(200) NOT NULL,
  `url`           VARCHAR(200) NOT NULL,
  `number`        VARCHAR(100) NOT NULL,
  `description`   TEXT                  DEFAULT NULL,
  `text`          TEXT                  DEFAULT NULL,
  `keywords`      TEXT                  DEFAULT NULL,
  `date`          VARCHAR(30)  NOT NULL,
  `main_photo_id` INT UNSIGNED          DEFAULT NULL,
  `category_id`   INT UNSIGNED          DEFAULT NULL,
  `is_valid`      BIT(1)       NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  UNIQUE (`number`),
  UNIQUE (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 7) Companies -------------------------------------------------------------------------------*/

CREATE TABLE `companies` (
  `id`             INT UNSIGNED             NOT NULL AUTO_INCREMENT,
  `type`           ENUM ('MAIN', 'PARTNER') NOT NULL,
  `title`          VARCHAR(100)             NOT NULL,
  `domain`         VARCHAR(200)                      DEFAULT NULL,
  `url`            VARCHAR(200)                      DEFAULT NULL,
  `tagline`        TEXT                              DEFAULT NULL,
  `description`    TEXT                     NOT NULL,
  `advantages`     TEXT                              DEFAULT NULL,
  `information`    TEXT                              DEFAULT NULL,
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
  `address`        VARCHAR(300)             NOT NULL,
  `keywords`       TEXT                     NOT NULL,
  `work_time_from` VARCHAR(10)                       DEFAULT NULL,
  `work_time_to`   VARCHAR(10)                       DEFAULT NULL,
  `google_maps`    TEXT                              DEFAULT NULL,
  `logo_id`        INT UNSIGNED                      DEFAULT NULL,
  `favicon_id`     INT UNSIGNED                      DEFAULT NULL,
  `is_valid`       BIT(1)                   NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`logo_id`) REFERENCES `photos` (`id`),
  FOREIGN KEY (`favicon_id`) REFERENCES `photos` (`id`),
  UNIQUE (`title`),
  UNIQUE (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 8) Article photo -------------------------------------------------------------------------------*/

CREATE TABLE `article_photo` (
  `article_id` INT UNSIGNED NOT NULL,
  `photo_id`   INT UNSIGNED NOT NULL,
  FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 9) Article video ------------------------------------------------------------------------------*/

CREATE TABLE `article_video` (
  `article_id` INT UNSIGNED NOT NULL,
  `video_id`   INT UNSIGNED NOT NULL,
  FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`),
  FOREIGN KEY (`video_id`) REFERENCES `videos` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 10) Company photo ---------------------------------------------------------------------------------------*/

CREATE TABLE `company_photo` (
  `company_id` INT UNSIGNED NOT NULL,
  `photo_id`   INT UNSIGNED NOT NULL,
  FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*--- 11) Responses ---------------------------------------------------------------------------------------*/

CREATE TABLE `responses` (
  `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `text`     TEXT         NOT NULL,
  `date`     VARCHAR(30)  NOT NULL,
  `is_valid` BIT(1)       NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*------------------------------------------------------------------------------------------*/
