-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema PA_TP
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `PA_TP` ;

-- -----------------------------------------------------
-- Schema PA_TP
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PA_TP` DEFAULT CHARACTER SET utf8 ;
USE `PA_TP` ;

-- -----------------------------------------------------
-- Table `PA_TP`.`Roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Roles` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Users` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `username` VARCHAR(128) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `email` VARCHAR(256) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 0,
  `deleted` TINYINT NOT NULL DEFAULT 0,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_Users_Role1_idx` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_Users_Role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `PA_TP`.`Roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Managers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Managers` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Managers` (
  `user_id` BIGINT NOT NULL,
  INDEX `fk_Managers_Users_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_Managers_Users`
    FOREIGN KEY (`user_id`)
    REFERENCES `PA_TP`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Reviewers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Reviewers` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Reviewers` (
  `user_id` BIGINT NOT NULL,
  `nif` VARCHAR(9) NOT NULL,
  `phone` VARCHAR(9) NOT NULL,
  `address` VARCHAR(256) NOT NULL,
  `graduation` VARCHAR(128) NOT NULL,
  `specialization` VARCHAR(128) NOT NULL,
  INDEX `fk_Reviewers_Users1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `nif_UNIQUE` (`nif` ASC) VISIBLE,
  CONSTRAINT `fk_Reviewers_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `PA_TP`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`LiteraryStyles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`LiteraryStyles` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`LiteraryStyles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `literary_style` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Authors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Authors` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Authors` (
  `user_id` BIGINT NOT NULL,
  `nif` VARCHAR(9) NOT NULL,
  `phone` VARCHAR(9) NOT NULL,
  `address` VARCHAR(256) NOT NULL,
  `activity_begin_date` DATE NOT NULL DEFAULT (CURRENT_DATE),
  `literary_style_id` INT NOT NULL,
  INDEX `fk_Authors_Users1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Authors_LiteracyStyles1_idx` (`literary_style_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `nif_UNIQUE` (`nif` ASC) VISIBLE,
  CONSTRAINT `fk_Authors_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `PA_TP`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Authors_LiteracyStyles1`
    FOREIGN KEY (`literary_style_id`)
    REFERENCES `PA_TP`.`LiteraryStyles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Books` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(256) NOT NULL,
  `subtitle` VARCHAR(256) NULL,
  `pages` INT NOT NULL,
  `words` INT NOT NULL,
  `isbn` VARCHAR(13) NOT NULL,
  `edition` VARCHAR(128) NULL,
  `submission_date` DATE NOT NULL DEFAULT (CURRENT_DATE),
  `approval_date` DATE NULL,
  `literary_style_id` INT NOT NULL,
  `publication_type` VARCHAR(128) NOT NULL,
  `author_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  INDEX `fk_Books_LiteracyStyles1_idx` (`literary_style_id` ASC) VISIBLE,
  INDEX `fk_Books_Authors1_idx` (`author_id` ASC) VISIBLE,
  UNIQUE INDEX `isbn_UNIQUE` (`isbn` ASC) VISIBLE,
  CONSTRAINT `fk_Books_LiteracyStyles1`
    FOREIGN KEY (`literary_style_id`)
    REFERENCES `PA_TP`.`LiteraryStyles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Books_Authors1`
    FOREIGN KEY (`author_id`)
    REFERENCES `PA_TP`.`Authors` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Logs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Logs` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Logs` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `datetime` DATETIME NOT NULL DEFAULT NOW(),
  `action` VARCHAR(512) NOT NULL,
  INDEX `fk_Logs_Users1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Logs_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `PA_TP`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Reviews` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Reviews` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `random_code` INT NOT NULL,
  `serial_number` VARCHAR(128) NOT NULL,
  `submission_date` DATE NOT NULL DEFAULT (CURRENT_DATE),
  `approval_date` DATE NULL,
  `completion_date` DATE NULL,
  `elapsed_time` INT NULL,
  `observations` VARCHAR(512) NULL,
  `cost` FLOAT NULL,
  `book_id` BIGINT NOT NULL,
  `author_id` BIGINT NOT NULL,
  `manager_id` BIGINT NULL,
  `reviewer_id` BIGINT NULL,
  `status` VARCHAR(128) NOT NULL DEFAULT 'Iniciado',
  PRIMARY KEY (`id`),
  INDEX `fk_Reviews_Books1_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_Reviews_Authors1_idx` (`author_id` ASC) VISIBLE,
  INDEX `fk_Reviews_Managers1_idx` (`manager_id` ASC) VISIBLE,
  INDEX `fk_Reviews_Reviewers1_idx` (`reviewer_id` ASC) VISIBLE,
  UNIQUE INDEX `serial_number_UNIQUE` (`serial_number` ASC) VISIBLE,
  CONSTRAINT `fk_Reviews_Books1`
    FOREIGN KEY (`book_id`)
    REFERENCES `PA_TP`.`Books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reviews_Authors1`
    FOREIGN KEY (`author_id`)
    REFERENCES `PA_TP`.`Authors` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reviews_Managers1`
    FOREIGN KEY (`manager_id`)
    REFERENCES `PA_TP`.`Managers` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reviews_Reviewers1`
    FOREIGN KEY (`reviewer_id`)
    REFERENCES `PA_TP`.`Reviewers` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Notes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Notes` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Notes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(512) NULL,
  `page` INT NOT NULL,
  `paragraph` INT NOT NULL,
  `date` DATE NOT NULL DEFAULT (CURRENT_DATE),
  `review_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Notes_Reviews1_idx` (`review_id` ASC) VISIBLE,
  CONSTRAINT `fk_Notes_Reviews1`
    FOREIGN KEY (`review_id`)
    REFERENCES `PA_TP`.`Reviews` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Licenses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Licenses` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Licenses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `designation` VARCHAR(128) NOT NULL,
  `expire_date` DATE NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`ReviewsLicenses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`ReviewsLicenses` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`ReviewsLicenses` (
  `license_id` INT NOT NULL,
  `review_id` BIGINT NOT NULL,
  PRIMARY KEY (`license_id`, `review_id`),
  INDEX `fk_Reviews_has_Licenses_Licenses1_idx` (`license_id` ASC) VISIBLE,
  INDEX `fk_ReviewsLicenses_Reviews1_idx` (`review_id` ASC) VISIBLE,
  CONSTRAINT `fk_Reviews_has_Licenses_Licenses1`
    FOREIGN KEY (`license_id`)
    REFERENCES `PA_TP`.`Licenses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReviewsLicenses_Reviews1`
    FOREIGN KEY (`review_id`)
    REFERENCES `PA_TP`.`Reviews` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`Notifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`Notifications` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`Notifications` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `read` TINYINT NOT NULL DEFAULT 0,
  `content` VARCHAR(256) NOT NULL,
  `datetime` DATETIME NOT NULL DEFAULT NOW(),
  INDEX `fk_Notifications_Users1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Notifications_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `PA_TP`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PA_TP`.`RoleNotifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PA_TP`.`RoleNotifications` ;

CREATE TABLE IF NOT EXISTS `PA_TP`.`RoleNotifications` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `read` TINYINT NOT NULL DEFAULT 0,
  `content` VARCHAR(256) NOT NULL,
  `datetime` DATETIME NOT NULL DEFAULT NOW(),
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_RoleNotifications_Role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_RoleNotifications_Role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `PA_TP`.`Roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
