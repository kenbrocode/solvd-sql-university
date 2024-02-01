package com.solvd.university.parsers;


import com.solvd.university.model.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class JAXBParser {
    private static final Logger LOGGER = LogManager.getLogger("Parser");

    public static void main(String[] args) {
        File fileUniversity = new File
                ("D:\\Course_testimg\\University\\University\\src\\main\\resources\\xml\\university.xml");
        File fileSubject = new File
                ("D:\\Course_testimg\\University\\University\\src\\main\\resources\\xml\\subject.xml");


        universityParse(fileUniversity);
        subjectParse(fileSubject);

    }

    public static void universityParse(File fileUniversity) {
        try {
            //university
            JAXBContext context = JAXBContext.newInstance(University.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            University university = (University) unmarshaller.unmarshal(fileUniversity);
            LOGGER.info("University Title: " + university.getTitle());
            LOGGER.info("University Rector: " + university.getRector());

            List<Faculty> faculties = university.getFaculties();
            for (Faculty faculty : faculties) {
                LOGGER.info("\nFaculty Title: " + faculty.getTitle());
                LOGGER.info("Faculty Description: " + faculty.getDescription());
                LOGGER.info("Faculty Dekan: " + faculty.getDekan());
                LOGGER.info("Faculty date Creating: " + faculty.getDateCreatingFaculty() );

                List<Cafedra> cafedras = faculty.getCafedries();
                for (Cafedra cafedra : cafedras) {
                    LOGGER.info("\nCafedra Title: " + cafedra.getTitle());
                    LOGGER.info("Cafedra Description: " + cafedra.getDescription());

                    List<Speciality> specialities = cafedra.getSpecialities();
                    for (Speciality speciality : specialities) {
                        LOGGER.info("\nSpeciality Title: " + speciality.getTitle());
                        LOGGER.info("Speciality Description: " + speciality.getDescription());
                    }
                }
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void subjectParse(File fileSubject) {
        try {
            JAXBContext context = JAXBContext.newInstance(Subject.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Subject subject = (Subject) unmarshaller.unmarshal(fileSubject);

            LOGGER.info("\nSubject title " + subject.getTitle());
            LOGGER.info("Subect description " + subject.getDescription());

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
