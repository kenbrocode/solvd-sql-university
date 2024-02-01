package com.solvd.university.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "university")
@XmlAccessorType(XmlAccessType.FIELD)
public class University {
    @XmlAttribute(name = "id")
    private Long id;
    private String title;
    private String rector;
    @XmlElementWrapper(name = "faculties")
    @XmlElement(name = "faculty")
    private List<Faculty> faculties;

    public University() {
    }

    public University(String title, String rector) {
        this.title = title;
        this.rector = rector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRector() {
        return rector;
    }

    public void setRector(String rector) {
        this.rector = rector;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
