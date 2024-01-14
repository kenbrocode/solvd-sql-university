package solvd.laba.domain;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Course {
    private int id;
    private Date startDate;
    private String name;
    private double cost;

    private List<Class> classes;
    private List<Exam> exams;

    public Course() {
    }

    public Course(int id, Date startDate, String name, double cost, List<Class> classes, List<Exam> exams) {
        this.id = id;
        this.startDate = startDate;
        this.name = name;
        this.cost = cost;
        this.classes = classes;
        this.exams=exams;
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

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Course{" +
                "id=" + id +
                ", startDate=" + simpleDateFormat.format(startDate) +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", classes=" + classes +
                ", exams=" + exams +
                '}';
    }
}