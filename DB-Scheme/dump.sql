-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `system_name` VARCHAR(255) NOT NULL COMMENT 'Системное название',
  `display_name` VARCHAR(255) NOT NULL COMMENT 'Отображаемое название',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL COMMENT 'Логин',
  `password` VARCHAR(255) NOT NULL COMMENT 'Пароль',
  `first_name` VARCHAR(255) NOT NULL COMMENT 'Имя',
  `last_name` VARCHAR(255) NOT NULL COMMENT 'Фамилия',
  `role` INT NOT NULL COMMENT 'Роль',
  `phone` VARCHAR(45) NULL COMMENT 'Номер телефона',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_user_1_idx` (`role` ASC) VISIBLE,
  CONSTRAINT `fk_user_1`
    FOREIGN KEY (`role`)
    REFERENCES `mydb`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`privilegy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`privilegy` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `system_name` VARCHAR(255) NOT NULL COMMENT 'Системное название',
  `display_name` VARCHAR(255) NOT NULL COMMENT 'Отображаемое название',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`role_privilegy_map`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role_privilegy_map` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` INT NOT NULL COMMENT 'Роль',
  `privilegy` INT NOT NULL COMMENT 'Привилегия',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_role_privilegy_map_1_idx` (`role` ASC) VISIBLE,
  INDEX `fk_role_privilegy_map_2_idx` (`privilegy` ASC) VISIBLE,
  CONSTRAINT `fk_role_privilegy_map_1`
    FOREIGN KEY (`role`)
    REFERENCES `mydb`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_privilegy_map_2`
    FOREIGN KEY (`privilegy`)
    REFERENCES `mydb`.`privilegy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`task_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`task_status` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `system_name` VARCHAR(255) NOT NULL COMMENT 'Системное название',
  `display_name` VARCHAR(255) NOT NULL COMMENT 'Отображаемое название',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `login` VARCHAR(255) NOT NULL COMMENT 'Логин',
  `email` VARCHAR(255) NOT NULL COMMENT 'Email',
  `phone` VARCHAR(255) NOT NULL COMMENT 'Телефон',
  `is_editing` TINYINT NOT NULL DEFAULT 0 COMMENT 'Открыт ли кем-то в режиме редактирования',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`task` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` TEXT NOT NULL COMMENT 'Название',
  `price` FLOAT NOT NULL COMMENT 'Цена',
  `deadline` DATETIME NOT NULL COMMENT 'Планируемая дата завершения',
  `status` INT NOT NULL COMMENT 'Статус',
  `responsible` INT NOT NULL COMMENT 'Ответственный',
  `client` INT NOT NULL COMMENT 'Клиент',
  `is_editing` TINYINT NOT NULL DEFAULT 0 COMMENT 'Открыта ли кем то в режиме редактирования',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_task_1_idx` (`status` ASC) VISIBLE,
  INDEX `fk_task_2_idx` (`responsible` ASC) VISIBLE,
  INDEX `fk_task_3_idx` (`client` ASC) VISIBLE,
  CONSTRAINT `fk_task_1`
    FOREIGN KEY (`status`)
    REFERENCES `mydb`.`task_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_2`
    FOREIGN KEY (`responsible`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_3`
    FOREIGN KEY (`client`)
    REFERENCES `mydb`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`attachment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`attachment` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `path` TEXT NOT NULL COMMENT 'Путь к файлу',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tasks_attachments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tasks_attachments` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `task_id` INT NOT NULL COMMENT 'Задача',
  `attachment_id` INT NOT NULL COMMENT 'Вложение',
  PRIMARY KEY (`id`),
  INDEX `index2` () VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_tasks_attachments_1_idx` (`task_id` ASC) VISIBLE,
  INDEX `fk_tasks_attachments_2_idx` (`attachment_id` ASC) VISIBLE,
  CONSTRAINT `fk_tasks_attachments_1`
    FOREIGN KEY (`task_id`)
    REFERENCES `mydb`.`task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_attachments_2`
    FOREIGN KEY (`attachment_id`)
    REFERENCES `mydb`.`attachment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`task_dialog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`task_dialog` (
  `id` INT UNSIGNED NOT NULL,
  `task_id` INT NOT NULL,
  `avatar_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_dialog_1_idx` (`task_id` ASC) VISIBLE,
  INDEX `fk_task_dialog_1_idx` (`avatar_id` ASC) VISIBLE,
  CONSTRAINT `fk_dialog_1`
    FOREIGN KEY (`task_id`)
    REFERENCES `mydb`.`task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_dialog_1`
    FOREIGN KEY (`avatar_id`)
    REFERENCES `mydb`.`attachment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`task_dialog_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`task_dialog_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` TEXT NOT NULL,
  `sender_id` INT NOT NULL,
  `dialog_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_task_dialog_message_1_idx` (`sender_id` ASC) VISIBLE,
  INDEX `fk_task_dialog_message_2_idx` (`dialog_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_dialog_message_1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_dialog_message_2`
    FOREIGN KEY (`dialog_id`)
    REFERENCES `mydb`.`task_dialog` (`task_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`task_dialog_messages_attachments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`task_dialog_messages_attachments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message_id` INT NOT NULL,
  `attachment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_task_dialog_messages_attachments_1_idx` (`message_id` ASC) VISIBLE,
  INDEX `fk_task_dialog_messages_attachments_2_idx` (`attachment_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_dialog_messages_attachments_1`
    FOREIGN KEY (`message_id`)
    REFERENCES `mydb`.`task_dialog_message` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_dialog_messages_attachments_2`
    FOREIGN KEY (`attachment_id`)
    REFERENCES `mydb`.`attachment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`client_dialog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client_dialog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `avatar_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_client_dialog_1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_client_dialog_2_idx` (`avatar_id` ASC) VISIBLE,
  CONSTRAINT `fk_client_dialog_1`
    FOREIGN KEY (`client_id`)
    REFERENCES `mydb`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_dialog_2`
    FOREIGN KEY (`avatar_id`)
    REFERENCES `mydb`.`attachment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`client_dialog_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client_dialog_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` TEXT NOT NULL,
  `sender_id` INT NOT NULL,
  `dialog_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_client_dialog_message_1_idx` (`sender_id` ASC) VISIBLE,
  INDEX `fk_client_dialog_message_3_idx` (`dialog_id` ASC) VISIBLE,
  CONSTRAINT `fk_client_dialog_message_1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `mydb`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_dialog_message_2`
    FOREIGN KEY (`sender_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_dialog_message_3`
    FOREIGN KEY (`dialog_id`)
    REFERENCES `mydb`.`client_dialog` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`client_dialog_messages_attachments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client_dialog_messages_attachments` (
  `id` INT NOT NULL,
  `message_id` INT NOT NULL,
  `attachment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_client_dialog_messages_attachments_1_idx` (`message_id` ASC) VISIBLE,
  INDEX `fk_client_dialog_messages_attachments_2_idx` (`attachment_id` ASC) VISIBLE,
  CONSTRAINT `fk_client_dialog_messages_attachments_1`
    FOREIGN KEY (`message_id`)
    REFERENCES `mydb`.`client_dialog_message` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_dialog_messages_attachments_2`
    FOREIGN KEY (`attachment_id`)
    REFERENCES `mydb`.`attachment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`personal_employee_dialog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`personal_employee_dialog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `companion_1` INT NOT NULL,
  `companion_2` INT NOT NULL,
  `avatar_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_personal_employee_dialog_1_idx` (`companion_1` ASC) VISIBLE,
  INDEX `fk_personal_employee_dialog_2_idx` (`companion_2` ASC) VISIBLE,
  INDEX `fk_personal_employee_dialog_3_idx` (`avatar_id` ASC) VISIBLE,
  CONSTRAINT `fk_personal_employee_dialog_1`
    FOREIGN KEY (`companion_1`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_employee_dialog_2`
    FOREIGN KEY (`companion_2`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_employee_dialog_3`
    FOREIGN KEY (`avatar_id`)
    REFERENCES `mydb`.`attachment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`personal_employee_dialog_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`personal_employee_dialog_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` TEXT NOT NULL,
  `sender_id` INT NOT NULL,
  `dialog_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_personal_employee_dialog_message_1_idx` (`sender_id` ASC) VISIBLE,
  INDEX `fk_personal_employee_dialog_message_2_idx` (`dialog_id` ASC) VISIBLE,
  CONSTRAINT `fk_personal_employee_dialog_message_1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_employee_dialog_message_2`
    FOREIGN KEY (`dialog_id`)
    REFERENCES `mydb`.`personal_employee_dialog` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`personal_employee_dialog_messages_attachments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`personal_employee_dialog_messages_attachments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message_id` INT NOT NULL,
  `attachment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_personal_employee_dialog_messages_attachments_1_idx` (`message_id` ASC) VISIBLE,
  INDEX `fk_personal_employee_dialog_messages_attachments_2_idx` (`attachment_id` ASC) VISIBLE,
  CONSTRAINT `fk_personal_employee_dialog_messages_attachments_1`
    FOREIGN KEY (`message_id`)
    REFERENCES `mydb`.`personal_employee_dialog_message` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_employee_dialog_messages_attachments_2`
    FOREIGN KEY (`attachment_id`)
    REFERENCES `mydb`.`attachment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users_tasks_mapper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users_tasks_mapper` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_users_tasks_mapper_1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_users_tasks_mapper_2_idx` (`task_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_tasks_mapper_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_tasks_mapper_2`
    FOREIGN KEY (`task_id`)
    REFERENCES `mydb`.`task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
