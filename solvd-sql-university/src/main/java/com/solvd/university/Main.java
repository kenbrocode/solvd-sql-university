package com.solvd.university;

import com.solvd.university.dao.CafedraRepository;
import com.solvd.university.model.*;
import com.solvd.university.service.ManyToManyService;
import com.solvd.university.service.Service;
import com.solvd.university.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger("Main");

    public static void main(String[] args) {

        //Creatig lists

        List<Faculty> faculties;
        List<Cafedra> cafedries;
        List<Speciality> specialities;


        //Creating instances


        University university = new University("Kyiv NATIONAL Collage", "Anton");
        Faculty faculty = new Faculty("KRT", "Good faculty", "Misha", null);
        Cafedra cafedra = new Cafedra("Mathematics", "Cafedra about mathematic", null);
        Speciality speciality = new Speciality("121", "Cool speciality", null);
        Speciality speciality2 = new Speciality("126", "Awesome speciality", null);
        Price price = new Price(35000, null);

        Student student = new Student("ALEX", "ZELENSKIY", "026313161",
                "potap@gmail.com", null);
        Professor professor = new Professor("Leonardo", "Di Caprio", null);

        Subject subject = new Subject("Art", "Very interisting art");

        Exam exam = new Exam("Programming", new Date(2023 - 23 - 05), "Exam for programming", null, 40);
        Payment payment = new Payment("Monobank", null, null, new Date(2023 - 12 - 01));

        //initializing lists and seting them to instances

        faculties = List.of(faculty);
        cafedries = List.of(cafedra);
        specialities = List.of(speciality, speciality2);


        university.setFaculties(faculties);
        faculty.setCafedries(cafedries);
        cafedra.setSpecialities(specialities);

        //Creating services



        Service cafedraService = new CafedraServiceImpl();

        Service examService = new ExamServiceImpl();

        Service facultyService = new FacultyServiceImpl();

        Service paymentService = new PaymentServiceImpl();

        Service priceService = new PriceServiceImpl();

        Service professorService = new ProfessorServiceImpl();

        Service specialityService = new SpecialityServiceImpl();

        Service studentService = new StudentServiceImpl();

        Service subjectService = new SubjectServiceImpl();

        Service universityService = new UniversityServiceImpl();

        ManyToManyService studentsExamsService = new StudentsExamsServiceImpl();

        ManyToManyService subjectsSpecialitiesService = new SubjectSpecialityServiceImpl();

        ManyToManyService subjectsProfessorsService = new SubjectsProfessorsServiceImpl();


        //creating to database

        priceService.create(price);
        paymentService.create(payment);




        universityService.create(university);
        faculty.setUniversityId(university.getId());
        facultyService.create(faculty);

        cafedra.setFacultyId(faculty.getId());
        cafedraService.create(cafedra);
        speciality.setCafedraId(cafedra.getId());
        specialityService.create(speciality);
        speciality2.setCafedraId(cafedra.getId());
        specialityService.create(speciality2);
        price.setSpecialityId(speciality.getId());
        priceService.create(price);


        studentService.create(student);
        professor.setCafedraId(cafedra.getId());
        professorService.create(professor);

        exam.setSpecialityId(speciality2.getId());
        examService.create(exam);
        payment.setPriceId(price.getId());
        payment.setStudentId(student.getId());
        paymentService.create(payment);

        subjectService.create(subject);


        //many-to-many
        studentsExamsService.create(student, exam);
        subjectsProfessorsService.create(subject, professor);
        subjectsSpecialitiesService.create(speciality, subject);

        //logging


        LOGGER.info("University id " + university.getId());
        LOGGER.info("Faculty id " + faculty.getId());
        LOGGER.info("Cafedra id " + cafedra.getId());
        LOGGER.info("Speciality id " + speciality.getId());
        LOGGER.info("Price id " + price.getId());
        LOGGER.info("Student id " + student.getId());
        LOGGER.info("Professor id " + professor.getId());
        LOGGER.info("Exam id " + exam.getId());
        LOGGER.info("Payment id " + payment.getId());

        //other methods to database


        List<University> universities = universityService.findAll();
        LOGGER.info(universities.get(0));
        LOGGER.info(university.getFaculties().get(0));
        //List<Faculty> faculties = facultyService.findAll();
        specialityService.update(speciality);
        subjectService.findById(1l);



    }
}