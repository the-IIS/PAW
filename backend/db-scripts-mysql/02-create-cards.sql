-- -----------------------------------------------------
-- Schema full-stack-trello
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `full-stack-trello`;

CREATE SCHEMA `full-stack-trello`;
USE `full-stack-trello` ;

-- -----------------------------------------------------
-- Table `full-stack-trello`.`card_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-stack-trello`.`card_list` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `list_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
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

INSERT INTO CARD_LIST(LIST_NAME) VALUES ('WALKING_SKELETON');

INSERT INTO CARD (TITLE, DESCRIPTION, LIST_ID)
VALUES ('THE CONNECTION', 'IS WORKING!!!', 1);

