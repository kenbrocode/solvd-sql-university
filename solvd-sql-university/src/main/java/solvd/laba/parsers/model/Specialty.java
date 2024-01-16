package solvd.laba.parsers.model;


import jakarta.xml.bind.annotation.*;
import solvd.laba.domain.Subject;

import java.util.List;

@XmlRootElement(name = "specialty")
@XmlAccessorType(XmlAccessType.FIELD)
public class Specialty {

    @XmlElement
    private long id;

    @XmlElement
    private String name;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private List<Subject> subjects;


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


    public List<solvd.laba.domain.Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }


}