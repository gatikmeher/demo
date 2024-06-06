CREATE TABLE `demo-class`.`user_credentials` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` TEXT NULL,
  `password_salt` VARCHAR(50) NULL,
  `login_date_time` DATETIME NULL,
  `created_by` INT NULL,
  `created_at` DATETIME NULL,
  `updated_by` INT NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `password_salt_UNIQUE` (`password_salt` ASC) VISIBLE,
  INDEX `UC_User_FK_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `UC_User_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `demo-class`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
