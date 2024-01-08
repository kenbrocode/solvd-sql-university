DROP DATABASE IF EXISTS university;
CREATE DATABASE university;
USE university;

DROP TABLE IF EXISTS Departments;
CREATE TABLE Departments (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(45),
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Specialities;
CREATE TABLE Specialities (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(45),
	Departments_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (Departments_id) REFERENCES Departments(id) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Subjects;
CREATE TABLE Subjects (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(45),
	Specialities_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (Specialities_id) REFERENCES Specialities(id) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Courses;
CREATE TABLE Courses (
	id INT NOT NULL AUTO_INCREMENT,
	startDate DATE,
	name VARCHAR(45),
	cost DOUBLE,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Exams;
CREATE TABLE Exams (
	id INT NOT NULL AUTO_INCREMENT,
	date DATE,
	Courses_id INT NOT NULL,
    Subjects_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (Courses_id) REFERENCES Courses (id) ON UPDATE CASCADE,
    FOREIGN KEY (Subjects_id) REFERENCES Subjects (id) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Students;
CREATE TABLE Students (
	id INT NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(45),
	lastName VARCHAR(45),
	email VARCHAR(45),
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Professors;
CREATE TABLE Professors (
	id INT NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(45),
	lastName VARCHAR(45),
	degree VARCHAR(45),
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Classrooms;
CREATE TABLE Classrooms (
	id INT NOT NULL AUTO_INCREMENT,
	size INT,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Classes;
CREATE TABLE Classes (
	id INT NOT NULL AUTO_INCREMENT,
    Professors_id INT NOT NULL,
    Courses_id INT NOT NULL,
    date DATE,
    Classrooms_id INT NOT NULL,
    Subjects_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (Professors_id) REFERENCES Professors (id) ON UPDATE CASCADE,
    FOREIGN KEY (Courses_id) REFERENCES Courses (id) ON UPDATE CASCADE,
    FOREIGN KEY (Classrooms_id) REFERENCES Classrooms (id) ON UPDATE CASCADE,
    FOREIGN KEY (Subjects_id) REFERENCES Subjects (id) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Group_of_Students;
CREATE TABLE Group_of_Students (
	id INT NOT NULL AUTO_INCREMENT,
    Classes_id INT NOT NULL,
    Students_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (Classes_id) REFERENCES Classes (id) ON UPDATE CASCADE,
    FOREIGN KEY (Students_id) REFERENCES Students (id) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Enrollments;
CREATE TABLE Enrollments (
	id INT NOT NULL AUTO_INCREMENT,
	Courses_id INT NOT NULL,
    Students_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (Courses_id) REFERENCES Courses (id) ON UPDATE CASCADE,
    FOREIGN KEY (Students_id) REFERENCES Students (id) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Grades;
CREATE TABLE Grades (
	id INT NOT NULL AUTO_INCREMENT,
    Students_id INT NOT NULL,
    Exams_id INT NOT NULL,
    grade INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (Students_id) REFERENCES Students (id) ON UPDATE CASCADE,
    FOREIGN KEY (Exams_id) REFERENCES Exams (id) ON UPDATE CASCADE
);









