package solvd.laba.domain;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Course {
    private int id;
    private Date startDate;
    private String name;
    private double cost;
    private List<Student> students;
    private List<Class> classes;

    public Course() {
    }

    public Course(int id, Date startDate, String name, double cost, List<Student> students, List<Class> classes) {
        this.id = id;
        this.startDate = startDate;
        this.name = name;
        this.cost = cost;
        this.students = students;
        this.classes = classes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Course{" +
                "id=" + id +
                ", startDate=" + simpleDateFormat.format(startDate) +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", students=" + students +
                ", classes=" + classes +
                '}';
    }
}