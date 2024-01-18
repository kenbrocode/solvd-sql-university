package solvd.laba.parsers.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import solvd.laba.parsers.model.Subject;

import java.io.File;

public class MainJaxb {

    public static void main(String[] args) {

        File xmlFile = new File("src/main/resources/parsers/xml/subject.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(Subject.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Subject subject = (Subject) unmarshaller.unmarshal(xmlFile);
            System.out.println(subject);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}