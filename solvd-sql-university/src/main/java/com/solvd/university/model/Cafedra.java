package com.solvd.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Cafedra {
    @XmlAttribute(name = "id")
    private Long id;
    private String title;
    @JsonIgnore
    private String description;
    @JsonIgnore
    private Long facultyId;
    @XmlElementWrapper(name = "specialities")
    @XmlElement(name = "speciality")
    private List<Speciality> specialities;

    public Cafedra() {
    }

    public Cafedra(String title, String description, Long facultyId) {
        this.title = title;
        this.description = description;
        this.facultyId = facultyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
