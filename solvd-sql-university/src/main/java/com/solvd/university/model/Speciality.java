package com.solvd.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Speciality {
    @XmlAttribute(name = "id")
    private Long id;
    private String title;
    private String description;
    @JsonIgnore
    private Long cafedraId;

    public Speciality() {
    }

    public Speciality(String title, String description, Long cafedraId) {
        this.title = title;
        this.description = description;
        this.cafedraId = cafedraId;
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

    public Long getCafedraId() {
        return cafedraId;
    }

    public void setCafedraId(Long cafedraId) {
        this.cafedraId = cafedraId;
    }
}
