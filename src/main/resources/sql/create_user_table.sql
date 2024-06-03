CREATE TABLE `demo-app`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(10) NULL,
  `first_name` VARCHAR(20) NULL,
  `last_name` VARCHAR(20) NULL,
  `mobile` VARCHAR(10) NULL,
  `email` VARCHAR(45) NULL,
  `created_by` INT NULL,
  `created_at` DATETIME NULL,
  `updated_by` INT NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
