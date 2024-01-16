package solvd.laba.parsers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;
import solvd.laba.domain.Exam;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("subject")
public class Subject {
    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @XmlElementWrapper(name = "classes")
    @XmlElement(name = "class")
    @JsonProperty
    private List<Class> classes;

    @XmlElementWrapper(name = "exams")
    @XmlElement(name = "exam")
    @JsonProperty
    private List<Exam> exams;

    public Subject() {
        this.classes = new ArrayList<>();
        this.exams = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}