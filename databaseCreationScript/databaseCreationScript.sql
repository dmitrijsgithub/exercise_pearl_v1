SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `exercise_pearl` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `exercise_pearl` ;

-- -----------------------------------------------------
-- Table `exercise_pearl`.`feeds`
-- -----------------------------------------------------
DROP TABLE IF EXISTS`exercise_pearl`.`feeds`;
CREATE TABLE IF NOT EXISTS `exercise_pearl`.`feeds` (

  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) UNIQUE NULL,
  `title` VARCHAR(255) NULL,
  `last_update` DATETIME NULL,
  `feed_name` VARCHAR(255) NULL,

  PRIMARY KEY (`id`),
  INDEX `FEEDNAME` (`feed_name` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `exercise_pearl`.`items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exercise_pearl`.`items`;
CREATE TABLE IF NOT EXISTS `exercise_pearl`.`items` (


  `id` INT NOT NULL AUTO_INCREMENT,
  `feed_id`  INT(11) NULL,
  `title` VARCHAR(255) NULL,
  `link` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `published` DATETIME NULL,

  PRIMARY KEY (`id`),
  INDEX `fk_items_1` (`feed_id` ASC),
  CONSTRAINT `fk_items_1` FOREIGN KEY (`feed_id`) REFERENCES `exercise_pearl`.`feeds` (`id`)

    ON DELETE CASCADE
    ON UPDATE CASCADE )

  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




