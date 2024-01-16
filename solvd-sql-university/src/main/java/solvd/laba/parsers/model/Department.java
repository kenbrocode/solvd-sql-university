package solvd.laba.parsers.model;


import jakarta.xml.bind.annotation.*;
import solvd.laba.domain.Specialty;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Department {

    @XmlElement
    private long id;

    @XmlElement
    private String name;

    @XmlElementWrapper(name = "specialities")
    @XmlElement(name = "specialty")
    private List<Specialty> specialities;



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


    public List<Specialty> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Specialty> specialities) {
        this.specialities = specialities;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialities=" + specialities +
                '}';
    }
}