package solvd.laba.domain;

import java.util.List;

public class Subject {

    private int id;
    private String name;
    private List<Integer> specialityIds;

    public Subject() {
    }

    public Subject(int id, String name, List<Integer> specialityIds) {
        this.id = id;
        this.name = name;
        this.specialityIds = specialityIds;
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

    public List<Integer> getSpecialityIds() {
        return specialityIds;
    }

    public void setSpecialityIds(List<Integer> specialityIds) {
        this.specialityIds = specialityIds;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialityIds=" + specialityIds +
                '}';
    }
}