package db;

import db.models.Exam;
import db.models.Speciality;
import db.models.Student;
import db.models.Subject;
import db.parsers.jaxb.Exams;
import db.parsers.jaxb.Jaxb;
import db.parsers.jaxb.Specialities;
import db.parsers.jaxb.Subjects;
import db.parsers.sax.ExamHandler;
import db.parsers.sax.SpecialityHandler;
import db.parsers.sax.SubjectHandler;
import db.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static final String EXAM_XML_PATH = "src/main/resources/files/exams.xml";
    private static final String SPECIALITY_XML_PATH = "src/main/resources/files/specialities.xml";
    private static final String SUBJECT_XML_PATH = "src/main/resources/files/subjects.xml";

    private static final String SUBJECT_JSON_PATH = "src/main/resources/files/subjects.json";

    private static final String EXAM_JAXB_XML = "src/main/resources/files/examsjaxb.xml";
    private static final String SUBJ_JAXB_XML = "src/main/resources/files/subjectsjaxb.xml";
    private static final String SPEC_JAXB_XML = "src/main/resources/files/specialitiesjaxb.xml";

    private static final String SUBJ_JACKSON_JSON = "src/main/resources/files/subjectsjackson.json";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public static void main(String[] args) throws SQLException {

        xmlJaxbParsing();
        xmlSaxParsing();
        dbOperations();

    }

    public static void xmlSaxParsing(){
        ExamHandler examHandler = new ExamHandler();
        try{
            List<Exam> exams = examHandler.readDataFromXML(EXAM_XML_PATH);
            for (Exam exam: exams) {
                LOGGER.info(exam);
            }
        }catch (IOException | ParserConfigurationException |  SAXException e){
            LOGGER.error("Failed reading EXAM XML");
            LOGGER.error(e.getMessage());
        }

        try{
            SpecialityHandler specialityHandler = new SpecialityHandler();
            List<Speciality> specialities = specialityHandler.readDataFromXML(SPECIALITY_XML_PATH);
            for (Speciality speciality: specialities) {
                LOGGER.info(speciality);
            }
        } catch (IOException | ParserConfigurationException |  SAXException e){
            LOGGER.error("Failed reading SPECIALITY XML");
            LOGGER.error(e.getMessage());
        }
        try{
            SubjectHandler subjectHandler = new SubjectHandler();
            List<Subject> subjects = subjectHandler.readDataFromXML(SUBJECT_XML_PATH);
            for (Subject subject: subjects) {
                LOGGER.info(subject);
            }
        } catch (IOException | ParserConfigurationException |  SAXException e) {
            LOGGER.error("Failed reading SUBJECT XML");
            LOGGER.error(e.getMessage());
        }
    }

    public static void xmlJaxbParsing(){
        try
        {
            Exams exams = new Exams();
            exams.setExams(new ArrayList<>());

            Exam exam = new Exam(1,simpleDateFormat.parse("2023-10-14"),1,8);

            Exam exam2 = new Exam(2,simpleDateFormat.parse("2023-10-15"),2,8);
            exams.getExams().add(exam);
            exams.getExams().add(exam2);

            Subjects subjects = new Subjects();
            subjects.setSubjects(new ArrayList<>());

            Subject subject = new Subject(1,"Mathematics",1);
            Subject subject2 = new Subject(2,"Computing",1);
            Subject subject3 = new Subject(3,"Administration",2);
            subjects.getSubjects().add(subject);
            subjects.getSubjects().add(subject2);
            subjects.getSubjects().add(subject3);

            Specialities specialities = new Specialities();
            specialities.setSpecialities(new ArrayList<>());

            Speciality speciality = new Speciality(1,"Informatics Engineering",1);
            Speciality speciality2 = new Speciality(2,"Accountant",1);
            specialities.getSpecialities().add(speciality);
            specialities.getSpecialities().add(speciality2);

            Jaxb jaxb = new Jaxb();
            jaxb.marshalling(exams,EXAM_JAXB_XML);
            Exams examsUnmarshalled = jaxb.unmarshalling(Exams.class,EXAM_JAXB_XML);
            for(Exam examI : examsUnmarshalled.getExams())
            {
                LOGGER.info(examI);
            }

            jaxb.marshalling(subjects,SUBJ_JAXB_XML);
            Subjects subjectsUnmarshalled = jaxb.unmarshalling(Subjects.class,SUBJ_JAXB_XML);
            for(Subject subjectI : subjectsUnmarshalled.getSubjects())
            {
                LOGGER.info(subjectI);
            }

            jaxb.marshalling(specialities,SPEC_JAXB_XML);
            Specialities specialitiesUnmarshalled = jaxb.unmarshalling(Specialities.class,SPEC_JAXB_XML);
            for(Speciality specialityI : specialitiesUnmarshalled.getSpecialities())
            {
                System.out.println(specialityI);
            }

        } catch (ParseException | JAXBException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void dbOperations() throws SQLException {

        IService studentService = null;
        try{
            studentService = ServiceFactory.create("db.services.StudentMyBatisService");
        }catch (Exception e){
            LOGGER.error("Error creating studentService");
            LOGGER.error(e.getMessage());
        }

        List<Student> students = (List<Student>) studentService.getAll();

        for (Student s: students) {
            LOGGER.info(s);
        }

        IService subjectService = null;
        try{
            subjectService = ServiceFactory.create("db.services.SubjectMyBatisService");
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            LOGGER.error("Error creating SubjectService");
            LOGGER.error(e.getMessage());
        }

        for (Object s: subjectService.getAll()) {
            LOGGER.info(s);
        }

        Subject subject = new Subject();
        subject.setName("Chemistry");
        subject.setSpecialityId(2);

        subjectService.insert(subject);

        for (Object s: subjectService.getAll()) {
            LOGGER.info(s);
        }

        // to delete the previous insertion
        subjectService.delete(12);

        for (Object s: subjectService.getAll()) {
            LOGGER.info(s);
        }

        IService examService = null;

        try{
            examService = ServiceFactory.create("db.services.ExamsMyBatisService");
        }catch (Exception e){
            LOGGER.error("Error creating ExamService");
            LOGGER.error(e.getMessage());
        }

        Exam exam = new Exam(11, Date.valueOf("2023-03-29"),9,3);

        assert examService != null;
        examService.update(exam);

        for (Object e: examService.getAll()) {
            LOGGER.info(e);
        }

    }


}
