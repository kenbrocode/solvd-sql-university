package solvd.laba.parsers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import solvd.laba.parsers.model.Subject;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        File jsonFile = new File("src/main/resources/parsers/json/subject.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            Subject subject = mapper.readValue(jsonFile, Subject.class);
            System.out.println(subject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
