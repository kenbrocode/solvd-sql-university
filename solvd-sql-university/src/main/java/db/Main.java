package db;

import db.dao.StudentDAO;
import db.models.Exam;
import db.models.Student;
import db.models.Subject;
import db.services.ExamsMysqlService;
import db.services.StudentMysqlService;
import db.services.SubjectMysqlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        // Initialize ExamsMysqlService
        ExamsMysqlService examsMysqlService = new ExamsMysqlService();

        try {
            // Get an exam by ID
            Exam examById = examsMysqlService.getById(1);
            LOGGER.info("Exam by ID: " + examById);

            // Create a new exam
            Exam newExam = new Exam(); // Create a new Exam object with necessary details
            // Set necessary attributes of the new exam
            newExam.setId(2);
            newExam.setDate(new Date(System.currentTimeMillis())); // Set date as current date for example
            newExam.setCourseId(3);
            newExam.setSubjectId(4);

            examsMysqlService.insert(newExam);
            LOGGER.info("New exam inserted: " + newExam);

            // Get all exams
            List<Exam> allExams = examsMysqlService.getAll();
            LOGGER.info("All exams:");
            for (Exam exam : allExams) {
                LOGGER.info(exam);
            }

        } catch (SQLException e) {
            LOGGER.error("Error performing operations with exams: " + e.getMessage());
        }
    }
}