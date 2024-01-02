-- MySQL Script generated by MySQL Workbench
-- Wed Dec 20 01:33:53 2023
-- Model: New Model    Version: 1.0
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
-- Table `mydb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`category` (
  `category_id` VARCHAR(10) NOT NULL,
  `category_description` VARCHAR(100) NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE INDEX `category_id_UNIQUE` (`category_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`courses` (
  `id` BIGINT NOT NULL,
  `course_description` VARCHAR(100) NOT NULL,
  `abstract` TEXT(1000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_courses_category1`
    FOREIGN KEY (`id`)
    REFERENCES `mydb`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cycles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cycles` (
  `cycle_id` BIGINT NOT NULL,
  `cycle_description` VARCHAR(100) NULL,
  `cycle_end_date` DATE NULL,
  `cycle_start_date` DATE NULL,
  `vacation_start_date` DATE NULL,
  `vacation_end_date` DATE NULL,
  PRIMARY KEY (`cycle_id`),
  UNIQUE INDEX `cycle_id_UNIQUE` (`cycle_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`courses_per_cycle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`courses_per_cycle` (
  `id` BIGINT NOT NULL,
  `cycle_id` BIGINT NOT NULL,
  `course_start_date` DATE NOT NULL,
  `courses_end_date` DATE NOT NULL,
  PRIMARY KEY (`id`, `cycle_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `cycle_id_UNIQUE` (`cycle_id` ASC) VISIBLE,
  CONSTRAINT `fk_courses_per_cycle_courses1`
    FOREIGN KEY (`cycle_id`)
    REFERENCES `mydb`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_courses_per_cycle_cycles1`
    FOREIGN KEY (`cycle_id`)
    REFERENCES `mydb`.`cycles` (`cycle_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`students` (
  `student_id` BIGINT NOT NULL,
  `student_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `birthday` DATE NULL,
  `phone_number` VARCHAR(30) NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `student_id_UNIQUE` (`student_id` ASC) VISIBLE,
  UNIQUE INDEX `student_name_UNIQUE` (`student_name` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`enrollments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`enrollments` (
  `id` BIGINT NOT NULL,
  `cycle_id` BIGINT NOT NULL,
  `student_id` BIGINT NOT NULL,
  `enrollment_date` DATE NULL,
  `cancelled` TINYINT NULL,
  `cancellation_reason` VARCHAR(100) NULL,
  PRIMARY KEY (`id`, `cycle_id`),
  INDEX `fk_enrollments_courses_per_cycle2_idx` (`cycle_id` ASC) VISIBLE,
  UNIQUE INDEX `cycle_id_UNIQUE` (`cycle_id` ASC) VISIBLE,
  UNIQUE INDEX `student_id_UNIQUE` (`student_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_enrollments_courses_per_cycle2`
    FOREIGN KEY (`cycle_id`)
    REFERENCES `mydb`.`courses_per_cycle` (`cycle_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_enrollments_students1`
    FOREIGN KEY (`cycle_id`)
    REFERENCES `mydb`.`students` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_enrollments_courses_per_cycle1`
    FOREIGN KEY (`cycle_id`)
    REFERENCES `mydb`.`courses_per_cycle` (`cycle_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tests` (
  `id` BIGINT NOT NULL,
  `cycle_id` BIGINT NOT NULL,
  `test_number` INT NOT NULL,
  `test_date` DATE NULL,
  `test_time` TIME NULL,
  `agenda` TEXT(1000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cycle_id_UNIQUE` (`cycle_id` ASC) VISIBLE,
  UNIQUE INDEX `test_number_UNIQUE` (`test_number` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_tests_enrollments1`
    FOREIGN KEY (`id`)
    REFERENCES `mydb`.`enrollments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`test_scores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`test_scores` (
  `id` BIGINT NOT NULL,
  `cycle_id` BIGINT NOT NULL,
  `test_number` INT NOT NULL,
  `student_id` BIGINT NOT NULL,
  `score` DECIMAL(5,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_test_scores_students1_idx` (`student_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `cycle_id_UNIQUE` (`cycle_id` ASC) VISIBLE,
  UNIQUE INDEX `test_number_UNIQUE` (`test_number` ASC) VISIBLE,
  UNIQUE INDEX `student_id_UNIQUE` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_test_scores_tests1`
    FOREIGN KEY (`id`)
    REFERENCES `mydb`.`tests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_test_scores_students1`
    FOREIGN KEY (`student_id`)
    REFERENCES `mydb`.`students` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`teachers` (
  `teacher_id` BIGINT NOT NULL,
  `teacher_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(100) NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE INDEX `teacher_id_UNIQUE` (`teacher_id` ASC) VISIBLE,
  UNIQUE INDEX `teacher_name_UNIQUE` (`teacher_name` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`teachers_per_course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`teachers_per_course` (
  `id` BIGINT NOT NULL,
  `cycle_id` BIGINT NOT NULL,
  `teacher_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_teachers_per_course_teachers1_idx` (`teacher_id` ASC) VISIBLE,
  UNIQUE INDEX `cycle_id_UNIQUE` (`cycle_id` ASC) VISIBLE,
  UNIQUE INDEX `teacher_id_UNIQUE` (`teacher_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_teachers_per_course_teachers1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `mydb`.`teachers` (`teacher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_teachers_per_course_courses_per_cycle1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `mydb`.`courses_per_cycle` (`cycle_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`classes` (
  `id` BIGINT NOT NULL,
  `cycle_id` BIGINT NOT NULL,
  `class_number` INT NOT NULL,
  `teacher_id` BIGINT NOT NULL,
  `class_title` VARCHAR(100) NULL,
  `class_date` DATE NULL,
  `start_time` TIME NULL,
  `end_time` TIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_classes_courses_per_cycle1_idx` (`class_number` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `cycle_id_UNIQUE` (`cycle_id` ASC) VISIBLE,
  UNIQUE INDEX `class_number_UNIQUE` (`class_number` ASC) VISIBLE,
  UNIQUE INDEX `teacher_id_UNIQUE` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `fk_classes_courses_per_cycle1`
    FOREIGN KEY (`class_number`)
    REFERENCES `mydb`.`courses_per_cycle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_classes_teachers1`
    FOREIGN KEY (`cycle_id`)
    REFERENCES `mydb`.`teachers` (`teacher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`attendance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`attendance` (
  `id` BIGINT NOT NULL,
  `cycle_id` BIGINT NOT NULL,
  `class_number` INT NOT NULL,
  `student_id` BIGINT NOT NULL,
  `time_arrive` TIME NULL,
  `time_leave` TIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_attendance_students1_idx` (`student_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `cycle_id_UNIQUE` (`cycle_id` ASC) VISIBLE,
  UNIQUE INDEX `class_number_UNIQUE` (`class_number` ASC) VISIBLE,
  UNIQUE INDEX `student_id_UNIQUE` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_attendance_students1`
    FOREIGN KEY (`student_id`)
    REFERENCES `mydb`.`students` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_attendance_classes1`
    FOREIGN KEY (`student_id`)
    REFERENCES `mydb`.`classes` (`class_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
