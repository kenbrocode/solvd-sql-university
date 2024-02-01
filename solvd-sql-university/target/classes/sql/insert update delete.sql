use university;
insert into health_records(medical_report) value ('Good');
insert into students(name, surname, phone_number, email, health_record_id) values ('Olya', 'Polykova', 'i2i3uu23u', 'olya@gmail.com', 2);
insert into students(name, surname, phone_number, email) values ('Nastya', 'Kamenskih', '078723761', 'nastya@gmail.com');
insert into specialities(title, cafedra_id) values ('126', 1), ('0', '1');
insert into prices(cost, speciality_id) values ('0', 2), ('-5', '2');
insert into faculties(title, dekan, university_id) values ('FBF', 'Yurii', 1);
insert into proffessors(name, surname, cafedra_id) values ('Ol', '0', 1), ('Polina', 'Yazenuk', 1);
insert into subjects(title) value ('English');
insert into proffessors_subjects(proffessor_id, subject_id) values (3, 4);
insert into universities(title, rector) values ('Pl', 'a');
insert into cafedries (title, faculty_id) values ('OT', 2), ('RO', 2);
insert into specialities_subjects(speciality_id, subject_id) values (7, 1), (7,2), (7,3);
insert into exams(title, data, speciality_id) values ('history', '2023-12-24', 7);

update specialities set title = '121' where title = 0;
update prices set cost = '120' where cost = 0;
update prices set cost = '100' where cost = -5;
update proffessors set name = 'Kostya', surname = 'Ivanov' where name = 'Ol';
update universities set title = 'VBU', rector = 'Dmitriy' where title = 'Pl';
update faculties set dekan = 'Oleg' where title = 'FBF';
update cafedries set title = 'IT' where title = 'RO';
update proffessors_subjects set proffessor_id = '2' where proffessor_id = 3;
update students set phone_number = '07323131344' where name = 'Nastya';
update students set email = 'Olga@gmail.com' where name = 'Olya';
update exams set speciality_id = 7 where speciality_id = 1;


drop table exams_subjects;
delete from exams_students where id = 9 or id = 11;
delete from students where name = 'John' and email != 'john@gmail.com';
delete from universities where title = 'VBU';
delete from specialities_subjects where speciality_id =7 and subject_id = 1;
delete from  exams where speciality_id = 7;
delete from allergies where title = 'Cats' and id!=1;
delete from vaccines where title = 'tetanus' and id!=1;
delete from payment where price_id = 1;
delete from prices where speciality_id = 1;
delete from exams where title = 'history';

alter table exams
	add column time int;
alter table payment
	add column data date;
alter table vaccines
	change column years data date;
alter table allergies
	drop column pills;
alter table students
	modify column name varchar(20);



select 
		st.id, st.name, st.surname, hr.medical_report, vc.title, al.title,
		pm.bank_title, pc.cost, sp.title, sb.title,
		ex.title, cf.title, fc.title, un.title,
        pf.name, pf.surname, ex.title, ex.data
	from students st
    join health_records hr on hr.id =  st.health_record_id
    join vaccines vc on hr.id =  vc.health_record_id
    join allergies al on hr.id =  al.health_record_id
    join payment pm on pm.student_id =  st.id
    join prices pc on pc.id =  pm.price_id
    join specialities sp on pc.speciality_id =  sp.id
    join specialities_subjects sp_sb on sp_sb.speciality_id =  sp.id
    join subjects sb on sp_sb.subject_id =  sb.id
    join exams_students ex_st on st.id =  ex_st.student_id
    join exams ex on ex_st.exam_id = ex.id
	join cafedries cf on cf.id =  sp.cafedra_id
    join faculties fc on fc.id =  cf.faculty_id
    join universities un on fc.university_id =  un.id
    join proffessors_subjects pf_sb on sb.id =  pf_sb.subject_id
    join proffessors pf on pf_sb.proffessor_id =  pf.id
    where st.id =  8;






