use university;
SET SQL_SAFE_UPDATES = 0;

insert into students(name, surname, phone_number, email) values ('Ivan', 'Pitt', '1537123', 'ivan@gmail.com');

insert into health_records(medical_report) values('Healthy');
insert into allergies(pills, title, years, health_record_id) values ('no pills', 'Cats', '2013-01-01', 1);
insert into vaccines (title, description, years, health_record_id) values ('tetanus', 'Mae in the childhood', '2015-01-01', 1);

insert into students (name, surname, phone_number, email, health_record_id) values ('Anna', 'Grande', '02378723', 'anna@gmail.com', 1);
insert into universities(title, rector) values ('KYT', 'Petro Kolonuk');
insert into faculties(title, dekan, university_id) values ('FIOT', 'igor Pochusk', 1);
insert into cafedries(title, faculty_id) values ('IST', 1);
insert into specialities(title, cafedra_id) values ('121', 1);
insert into exams(title, data, speciality_id) values ('Programming', '2023-12-26', 1);
insert into exams(title, data, speciality_id) values ('Mathematincs', '2023-12-23', 1);
insert into exams_students(exam_id, student_id) values (1, 1);
insert into exams_students(exam_id, student_id) values (2, 1);
insert into exams_students(exam_id, student_id) values (2, 7);

insert into proffessors (name, surname, cafedra_id) values ('Yan', 'Polik', 1);
insert into subjects(title) value ('Mathmatics');
insert into subjects(title) value ('Programming');
insert into subjects(title) value ('History of Ukraine');

insert into proffessors_subjects(proffessor_id, subject_id) values (1, 1);
insert into proffessors_subjects(proffessor_id, subject_id) values (1, 2);
insert into proffessors_subjects(proffessor_id, subject_id) values (1, 3);

insert into prices(cost, speciality_id) values (100, 1), (100,2);

insert into payment(bank_title, price_id, student_id) values ('Privat', 1,1), ('Monobank', 2, 7);

