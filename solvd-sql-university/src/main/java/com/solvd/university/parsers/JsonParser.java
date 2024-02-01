package com.solvd.university.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.university.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser {
    private static final Logger LOGGER = LogManager.getLogger("Parser");

    public static void main(String[] args) {
        File fileUniversity = new File
                ("D:\\Course_testimg\\University\\University\\src\\main\\resources\\json\\university.json");
        File fileSubject = new File
                ("D:\\Course_testimg\\University\\University\\src\\main\\resources\\json\\subject.json");
        University university = universityParse(fileUniversity);
        LOGGER.info("University Title: " + university.getTitle());
        LOGGER.info("University Rector: " + university.getRector());

        List<Faculty> faculties = university.getFaculties();
        for (Faculty faculty : faculties) {
            LOGGER.info("\nFaculty Title: " + faculty.getTitle());
            LOGGER.info("Faculty Description: " + faculty.getDescription());
            LOGGER.info("Faculty Dekan: " + faculty.getDekan());
            LOGGER.info("Faculty date Creating " + faculty.getDateCreatingFaculty());

            List<Cafedra> cafedras = faculty.getCafedries();
            for (Cafedra cafedra : cafedras) {
                LOGGER.info("\nCafedra Title: " + cafedra.getTitle());


                List<Speciality> specialities = cafedra.getSpecialities();
                for (Speciality speciality : specialities) {
                    LOGGER.info("\nSpeciality Title: " + speciality.getTitle());
                    LOGGER.info("Speciality Description: " + speciality.getDescription());
                }
            }
        }
        Subject subject = subjectParse(fileSubject);
        LOGGER.info("Subject title " + subject.getTitle());
        LOGGER.info("Subect description " + subject.getDescription());
    }

    public static University universityParse(File universityFile) {
        University university;
        ObjectMapper mapper = new ObjectMapper();
        try {
            university = mapper.readValue(universityFile, University.class);
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return university;
    }

    public static Subject subjectParse(File subjectFile) {
        Subject subject;
        ObjectMapper mapper = new ObjectMapper();
        try {
            subject = mapper.readValue(subjectFile, Subject.class);
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return subject;
    }
}

