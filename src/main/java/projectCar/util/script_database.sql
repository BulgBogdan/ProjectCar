-- -----------------------------------------------------
-- Schema ProjectCar
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ProjectCar`;

-- -----------------------------------------------------
-- Schema ProjectCar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ProjectCar` DEFAULT CHARACTER SET utf8;
USE `ProjectCar`;

-- -----------------------------------------------------
-- Table `ProjectCar`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`users`
(
  `id`          INT          NOT NULL AUTO_INCREMENT,
  `login`       VARCHAR(100) NOT NULL,
  `password`    VARCHAR(100) NOT NULL,
  `email`       VARCHAR(100) NOT NULL,
  `first_name`  VARCHAR(100) NOT NULL,
  `second_name` VARCHAR(100) NOT NULL,
  `birthday`    DATE         NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProjectCar`.`cars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`cars`
(
  `id`       INT          NOT NULL AUTO_INCREMENT,
  `name_car` VARCHAR(100) NOT NULL,
  `mileage`  INT          NOT NULL,
  `FK_users` INT          NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `car-user`
    FOREIGN KEY (`FK_users`)
      REFERENCES `ProjectCar`.`users` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX `fk_cars_users_idx` ON `ProjectCar`.`cars` (`FK_users` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ProjectCar`.`parameters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`parameters`
(
  `id`                  INT          NOT NULL AUTO_INCREMENT,
  `mark`                VARCHAR(100) NOT NULL,
  `model`               VARCHAR(100) NOT NULL,
  `first_mileage`       INT          NULL,
  `year`                INT          NOT NULL,
  `mass`                INT          NOT NULL,
  `color`               VARCHAR(100) NOT NULL,
  `average_rate`        DOUBLE       NOT NULL,
  `vin`                 VARCHAR(45)  NOT NULL,
  `registration_number` VARCHAR(45)  NOT NULL,
  `FK_cars`             INT          NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `parameter-cars`
    FOREIGN KEY (`FK_cars`)
      REFERENCES `ProjectCar`.`cars` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX `fk_parameterCar_cars1_idx` ON `ProjectCar`.`parameters` (`FK_cars` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ProjectCar`.`currency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`currency`
(
  `id`    INT         NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

insert into projectcar.currency (title)
values ('BYN');
insert into projectcar.currency (title)
values ('USD');
-- -----------------------------------------------------
-- Table `ProjectCar`.`documents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`documents`
(
  `id`              INT          NOT NULL AUTO_INCREMENT,
  `name_document`   VARCHAR(100) NOT NULL,
  `cost_document`   DOUBLE       NOT NULL,
  `start_date`      DATE         NOT NULL,
  `end_date`        DATE         NULL,
  `number_of_month` INT          NULL,
  `number_of_days`  INT          NULL,
  `FK_cars`         INT          NOT NULL,
  `FK_currency`     INT          NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `document_car`
    FOREIGN KEY (`FK_cars`)
      REFERENCES `ProjectCar`.`cars` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  CONSTRAINT `documents_currency`
    FOREIGN KEY (`FK_currency`)
      REFERENCES `ProjectCar`.`currency` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX `document-costCar_idx` ON `ProjectCar`.`documents` (`FK_cars` ASC) VISIBLE;

CREATE INDEX `fk_documents_currency1_idx` ON `ProjectCar`.`documents` (`FK_currency` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ProjectCar`.`fuel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`fuel`
(
  `id`            INT    NOT NULL AUTO_INCREMENT,
  `liter_cost`    DOUBLE NOT NULL,
  `liter_value`   DOUBLE NULL,
  `summ`          DOUBLE NULL,
  `fuel_distance` INT    NULL,
  `date_fuel`     DATE   NULL,
  `FK_cars`       INT    NOT NULL,
  `FK_currency`   INT    NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fuel_car`
    FOREIGN KEY (`FK_cars`)
      REFERENCES `ProjectCar`.`cars` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  CONSTRAINT `fuel_currency`
    FOREIGN KEY (`FK_currency`)
      REFERENCES `ProjectCar`.`currency` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX `fuel_car_idx` ON `ProjectCar`.`fuel` (`FK_cars` ASC) VISIBLE;

CREATE INDEX `fk_fuel_currency1_idx` ON `ProjectCar`.`fuel` (`FK_currency` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ProjectCar`.`repairs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`repairs`
(
  `id`            INT                                                         NOT NULL AUTO_INCREMENT,
  `name_repair`   VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `start_mileage` INT                                                         NOT NULL,
  `end_mileage`   INT                                                         NOT NULL,
  `cost_repair`   DOUBLE                                                      NOT NULL,
  `service_life`  INT                                                         NULL,
  `FK_cars`       INT                                                         NOT NULL,
  `FK_currency`   INT                                                         NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `repair_car`
    FOREIGN KEY (`FK_cars`)
      REFERENCES `ProjectCar`.`cars` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  CONSTRAINT `repairs_currency`
    FOREIGN KEY (`FK_currency`)
      REFERENCES `ProjectCar`.`currency` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX `repair_car_idx` ON `ProjectCar`.`repairs` (`FK_cars` ASC) VISIBLE;

CREATE INDEX `fk_repairs_currency1_idx` ON `ProjectCar`.`repairs` (`FK_currency` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ProjectCar`.`other_costs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`other_costs`
(
  `id`              INT          NOT NULL AUTO_INCREMENT,
  `name_other_cost` VARCHAR(100) NOT NULL,
  `cost_date`       DATE         NOT NULL,
  `cost`            DOUBLE       NOT NULL,
  `other_costscol`  VARCHAR(45)  NOT NULL,
  `FK_cars`         INT          NOT NULL,
  `FK_currency`     INT          NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `other_car`
    FOREIGN KEY (`FK_cars`)
      REFERENCES `ProjectCar`.`cars` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  CONSTRAINT `other_currency`
    FOREIGN KEY (`FK_currency`)
      REFERENCES `ProjectCar`.`currency` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX `other-costCar_idx` ON `ProjectCar`.`other_costs` (`FK_cars` ASC) VISIBLE;

CREATE INDEX `fk_other_costs_currency1_idx` ON `ProjectCar`.`other_costs` (`FK_currency` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ProjectCar`.`registration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjectCar`.`registration`
(
  `id`                 INT    NOT NULL AUTO_INCREMENT,
  `price_car`          INT    NOT NULL,
  `price_registration` DOUBLE NOT NULL,
  `FK_cars`            INT    NOT NULL,
  `FK_currency`        INT    NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `registration-car`
    FOREIGN KEY (`FK_cars`)
      REFERENCES `ProjectCar`.`cars` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  CONSTRAINT `registration_currency`
    FOREIGN KEY (`FK_currency`)
      REFERENCES `ProjectCar`.`currency` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX `fk_registration_cars1_idx` ON `ProjectCar`.`registration` (`FK_cars` ASC) VISIBLE;

CREATE INDEX `fk_registration_currency1_idx` ON `ProjectCar`.`registration` (`FK_currency` ASC) VISIBLE;