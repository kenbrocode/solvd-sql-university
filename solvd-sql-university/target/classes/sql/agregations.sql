select cafedries.title, count(specialities.id) as count
from cafedries
left join specialities on cafedries.id = specialities.cafedra_id
group by cafedries.title;

select sp.title, count(sb.title)
from specialities sp 
left join specialities_subjects sp_sb on sp.id = sp_sb.speciality_id
left join subjects sb on sp_sb.subject_id =  sb.id
group by sp.title;

select  fc.title, count(cf.title)
from faculties fc
left join cafedries cf on fc.id = cf.faculty_id
group by fc.title;

select un.title, count(fc.title)
from universities un
left join faculties fc on fc.university_id =  un.id
group by un.title;

select pf.name, pf.surname, count(sb.title) as subjects_number
from proffessors pf 
left join proffessors_subjects pf_sb on pf.id = pf_sb.proffessor_id
left join subjects sb on pf_sb.subject_id =  sb.id
group by pf.name, pf.surname;

select  hr.medical_report, count(al.title and vc.title)
from health_records hr
left join allergies al  on hr.id = al.health_record_id
left join vaccines vc  on hr.id = vc.health_record_id
group by hr.medical_report;

select pm.data, avg(pr.cost)
from prices pr
left join payment pm on pr.id =  pm.price_id
group by pm.data;




select sb.title, count(pf.name and pf.surname) as proffessors_number
from subjects sb 
left join proffessors_subjects pf_sb on sb.id = pf_sb.subject_id
left join proffessors pf on pf_sb.proffessor_id = pf.id
group by sb.title having proffessors_number >=2 ;

select st.name, count(ex.title) as exams_number
from students st 
left join exams_students ex_st on st.id = ex_st.student_id
left join exams ex on ex_st.exam_id = ex.id
group by st.name having exams_number<1;

select st.name, min(ex.data) as exams_earlier
from students st 
left join exams_students ex_st on st.id = ex_st.student_id
left join exams ex on ex_st.exam_id = ex.id
group by st.name having exams_earlier < '2023-12-24';


select st.name, avg(hr.medical_report) as report
from students st 
inner join   health_records hr on hr.id  = st.health_record_id
group by st.name  having report = 'Bad'
