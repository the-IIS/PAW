-- -----------------------------------------------------
-- Schema full-stack-trello
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `full-stack-trello`;

CREATE SCHEMA `full-stack-trello`;
USE `full-stack-trello`;

-- -----------------------------------------------------
-- Table `full-stack-trello`.`table_list`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `full-stack-trello`.`table_list` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `table_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `full-stack-trello`.`card_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-stack-trello`.`card_list` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `list_name` VARCHAR(255) NULL DEFAULT NULL,
  `ttable_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_table` (`ttable_id`),
  CONSTRAINT `fk_table` FOREIGN KEY (`ttable_id`) REFERENCES `table_list` (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `full-stack-trello`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-stack-trello`.`card` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `list_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_list` (`list_id`),
  CONSTRAINT `fk_list` FOREIGN KEY (`list_id`) REFERENCES `card_list` (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Add sample data
-- -----------------------------------------------------

INSERT INTO `full-stack-trello`.`table_list` (`table_name`) VALUES ("TABLE ONE");

INSERT INTO `full-stack-trello`.`card_list` (`list_name`, `ttable_id`) VALUES ("WALKING SKELETON", 1);

INSERT INTO `full-stack-trello`.`card` (`title`, `description`, `list_id`)
VALUES ("THE CONNECTION", "IS WORKING!!!", 1);

