package solvd.laba.domain;


import java.util.List;

public class Speciality {

    private int id;
    private String name;
    private List<Integer> departmentIds;

    public Speciality() {
    }

    public Speciality(int id, String name, List<Integer> departmentIds) {
        this.id = id;
        this.name = name;
        this.departmentIds = departmentIds;
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

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentIds=" + departmentIds +
                '}';
    }
}