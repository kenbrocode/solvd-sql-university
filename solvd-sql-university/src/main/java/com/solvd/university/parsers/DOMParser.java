package com.solvd.university.parsers;

import com.solvd.university.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static jakarta.xml.bind.DatatypeConverter.parseDate;

public class DOMParser {
    private static final Logger LOGGER = LogManager.getLogger("Parser");

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        File fileUniversity = new File
                ("D:\\Course_testimg\\University\\University\\src\\main\\resources\\xml\\university.xml");
        File fileSubject = new File
                ("D:\\Course_testimg\\University\\University\\src\\main\\resources\\xml\\subject.xml");
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documentUniversity = builder.parse(fileUniversity);
            Document doscumentSubject = builder.parse(fileSubject);


            University university = universityParse(documentUniversity);
            LOGGER.info("University Title: " + university.getTitle());
            LOGGER.info("University Rector: " + university.getRector());

            List<Faculty> faculties = university.getFaculties();
            for (Faculty faculty : faculties) {
                LOGGER.info("\nFaculty Title: " + faculty.getTitle());
                LOGGER.info("Faculty Description: " + faculty.getDescription());
                LOGGER.info("Faculty Dekan: " + faculty.getDekan());
                LOGGER.info("Date creating Faculty " + faculty.getDateCreatingFaculty());

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
            Subject subject = subjectparse(doscumentSubject);
            LOGGER.info("Subject title " + subject.getTitle());
            LOGGER.info("Subect description " + subject.getDescription());

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }


    public static University universityParse(Document document) {
        List<Faculty> facultyList = new ArrayList<>();
        University university = null;
        NodeList universities = document.getElementsByTagName("university");
        for (int i = 0; i < universities.getLength(); i++) {
            Node univercityNode = universities.item(i);

            if (univercityNode.getNodeType() == Node.ELEMENT_NODE) {

                Element universityElement = (Element) univercityNode;
                String universityTitle = universityElement.getElementsByTagName("title").item(0).getTextContent();
                String universityRector = universityElement.getElementsByTagName("rector").item(0).getTextContent();

                university = new University(universityTitle, universityRector);

                NodeList facultiesList = universityElement.getElementsByTagName("faculties");
                for (int j = 0; j < facultiesList.getLength(); j++) {
                    Node facultyNode = facultiesList.item(j);

                    if (facultyNode.getNodeType() == Node.ELEMENT_NODE) {
                        Faculty faculty = facultyParse((Element) facultyNode);
                        facultyList.add(faculty);
                    }
                }
            }
        }
        university.setFaculties(facultyList);
        return university;
    }

    public static Faculty facultyParse(Element facultyElement) {
        List<Cafedra> cafedryList = new ArrayList<>();
        Faculty faculty = null;
        String facultyTitle = facultyElement.getElementsByTagName("title").item(0).getTextContent();
        String facultyDescription = facultyElement.getElementsByTagName("description").item(0).getTextContent();
        String facultyDekan = facultyElement.getElementsByTagName("dekan").item(0).getTextContent();
        String dateCreaingFacultyStr = facultyElement.getElementsByTagName
                ("dateCreatingFaculty").item(0).getTextContent();
        Date dateCreatingFaculty = parseSqlDate(dateCreaingFacultyStr);

        faculty = new Faculty(facultyTitle, facultyDescription, facultyDekan, null, dateCreatingFaculty);

        NodeList cafedriesList = facultyElement.getElementsByTagName("cafedries");
        for (int j = 0; j < cafedriesList.getLength(); j++) {
            Node cafedryNode = cafedriesList.item(j);

            if (cafedryNode.getNodeType() == Node.ELEMENT_NODE) {
                Cafedra cafedra = cafedraParse((Element) cafedryNode);
                cafedryList.add(cafedra);
            }
        }
        faculty.setCafedries(cafedryList);
        return faculty;
    }

    public static Cafedra cafedraParse(Element cafedraElement) {
        List<Speciality> specialities = new ArrayList<>();
        Cafedra cafedra = null;
        String cafedraTitle = cafedraElement.getElementsByTagName("title").item(0).getTextContent();
        String cafedraDescription = cafedraElement.getElementsByTagName("description").item(0).getTextContent();

        cafedra = new Cafedra(cafedraTitle, cafedraDescription, null);

        NodeList specialitiesList = cafedraElement.getElementsByTagName("specialities");
        for (int i = 0; i < specialitiesList.getLength(); i++) {
            Node specialityNode = specialitiesList.item(i);

            if (specialityNode.getNodeType() == Node.ELEMENT_NODE) {
                Speciality speciality = specialityParse((Element) specialityNode);
                specialities.add(speciality);
            }
        }
        cafedra.setSpecialities(specialities);
        return cafedra;
    }

    public static Speciality specialityParse(Element specialityElement) {
        Speciality speciality;
        String specialityTitle = specialityElement.getElementsByTagName("title").item(0).getTextContent();
        String specialityDescription = specialityElement.getElementsByTagName("description").item(0).getTextContent();

        speciality = new Speciality(specialityTitle, specialityDescription, null);

        return speciality;
    }

    public static Subject subjectparse(Document document) {
        Subject subject = null;
        NodeList subjects = document.getElementsByTagName("subject");
        for (int i = 0; i < subjects.getLength(); i++) {
            Node subjectNode = subjects.item(i);

            if (subjectNode.getNodeType() == Node.ELEMENT_NODE) {

                Element subjectElement = (Element) subjectNode;
                String subjectTitle = subjectElement.getElementsByTagName("title").item(0).getTextContent();
                String subjectDescription = subjectElement.getElementsByTagName("description").item(0).getTextContent();

                subject = new Subject(subjectTitle, subjectDescription);

            }
        }
        return subject;
    }

    private static Date parseSqlDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(dateStr);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            // Handle parsing exception
            return null;
        }
    }

}

