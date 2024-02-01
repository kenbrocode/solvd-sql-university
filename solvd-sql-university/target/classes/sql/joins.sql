select students.name, students.surname, health_records.medical_report
from students
left join health_records
on students.health_record_id =  health_records.id;

select faculties.title, faculties.dekan, cafedries.title
from faculties
inner join cafedries
on cafedries.faculty_id = faculties.id;

select proffessors.name, proffessors.surname
from proffessors
right join cafedries
on proffessors.cafedra_id =  cafedries.id;

select specialities.title, cafedries.title
from specialities
cross join cafedries
on specialities.cafedra_id = cafedries.id;

select health_records.medical_report, allergies.title, vaccines.title
from health_records
left join allergies
on health_records.allergy =  allergies.id
right join vaccines
on health_records.vaccine =  vaccines.id;

select universities.title, faculties.university_id, cafedries.id
from universities
right join faculties
on faculties.university_id =  universities.id
left join cafedries
on cafedries.faculty_id =  faculties.id

select 
		st.id, st.name, st.surname, hr.medical_report, vc.title, al.title,
		pm.bank_title, pc.cost, sp.title, sb.title,
		ex.title, cf.title, fc.title, un.title,
        pf.name, pf.surname, ex.title, ex.data
	from students st
    join health_records hr on hr.id =  st.health_record_id
    join vaccines vc on hr.vaccine =  vc.id
    join allergies al on hr.allergy =  al.id
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