create database if not exists university;
use university;    
create table if not exists health_records(
	id serial,
    allergy varchar(45),
    vaccine varchar(45),
    medical_report varchar(45) not null,
    primary key(id)
    );
create table if not exists  students(
	id serial,
    name varchar(45)  not null ,
    surname varchar(45) not null,
    phone_number varchar(45) not null unique,
    email varchar(45) not null unique,
    primary key (id),
    health_record_id bigint unsigned,
    constraint fk_student_health_record foreign key(health_record_id) references health_records(id) on update no action on delete no action
);

create table if not exists allergies(
	id serial,
    pills varchar(60),
    title varchar(20),
    years date,
    primary key(id),
    health_record_id bigint unsigned,
    constraint fk_allergie_health_record foreign key(health_record_id) references health_records(id) on update no action on delete no action
    );

create table if not exists vaccines(
	id serial,
    title varchar(20) not null,
    description text,
    years date not null,
    primary key(id),
    health_record_id bigint unsigned,
    constraint fk_vaccine_health_record foreign key(health_record_id) references health_records(id) on update no action on delete no action
);

create table if not exists universities(
	id serial, 
    title varchar(45) not null unique,
    rector varchar(60) not null unique,
    primary key(id)
);

create table if not exists faculties(
	id serial, 
    title varchar(45)not null unique,
    description text,
    dekan varchar(60) not null unique,
    primary key(id),
    university_id bigint unsigned,
    constraint fk_faculty_university foreign key(university_id) references universities(id) on update no action on delete no action
);

create table if not exists cafedries(
	id serial,
    title varchar(45) not null,
    description text,
    primary key(id),
    faculty_id bigint unsigned,
    constraint fk_cafedra_faculty foreign key(faculty_id) references faculties(id) on update no action on delete no action
);
create table if not exists proffessors(
	id serial,
    name varchar (15),
    surname varchar (20),
    primary key(id),
    cafedra_id bigint unsigned,
    constraint fk_proffessor_cafedra foreign key(cafedra_id) references cafedries(id) on update no action on delete no action
);

create table if not exists specialities(
	id serial,
    title varchar(45) not null,
    description text,
    primary key (id),
    cafedra_id bigint unsigned,
    constraint fk_speciality_cafedra foreign key(cafedra_id) references cafedries(id) on update no action on delete no action
);
create table if not exists subjects(
	id serial, 
    title varchar(20) not null unique,
    decription text,
    primary key(id)
);
create table if not exists specialities_subjects(
	speciality_id bigint unsigned,
    subject_id bigint unsigned,
    constraint fk_specialities_subjects_speciality_id foreign key(speciality_id) references specialities(id) on update no action on delete no action,
    constraint fk_specialities_subjects_subject_id foreign key(subject_id) references subjects(id) on update no action on delete no action
);
create table if not exists proffessors_subjects(
	proffessor_id bigint unsigned,
    subject_id bigint unsigned,
    constraint fk_proffessors_subjects_proffessor_id foreign key(proffessor_id) references proffessors(id) on update no action on delete no action,
	constraint fk_proffessors_subjects_subject_id foreign key(subject_id) references subjects(id) on update no action on delete no action
);
create table if not exists prices(
	id serial,
    cost int not null,
    primary key(id),
    speciality_id bigint unsigned,
    constraint fk_price_speciality foreign key(speciality_id) references specialities(id) on update no action on delete no action
);
create table if not exists payment(
	id serial,
    bank_title varchar(45) not null,
    price_id bigint unsigned,
    student_id bigint unsigned,
    primary key(id),
    constraint fk_payment_price foreign key(price_id) references prices(id) on update no action on delete no action,
    constraint fk_payment_student foreign key(student_id) references students(id) on update no action on delete no action
);
create table if not exists exams(
	id serial,
    title varchar(45) not null unique,
    data date not null,
    descripton text,
    speciality_id bigint unsigned,
    primary key(id),
    constraint fk_exam_speciality foreign key(speciality_id) references specialities(id) on update no action on delete no action
);
create table if not exists exams_students(
	exam_id bigint unsigned,
    student_id bigint unsigned,
    constraint fk_exams_students_exam_id foreign key(exam_id) references exams(id) on update no action on delete no action,
	constraint fk_exams_students_students_id foreign key(student_id) references students(id) on update no action on delete no action
);






    