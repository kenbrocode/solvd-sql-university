package solvd.laba.domain;


import java.util.ArrayList;
import java.util.List;

public class Department {

    private int id;
    private String name;
    private List<Speciality> specialities;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.specialities = new ArrayList<>();
    }

    public Department() {
        this.specialities = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void addSpeciality(Speciality speciality) {
        specialities.add(speciality);
    }

    public void removeSpeciality(Speciality speciality) {
        specialities.remove(speciality);
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