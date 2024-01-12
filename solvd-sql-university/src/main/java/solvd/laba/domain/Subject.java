package solvd.laba.domain;

import java.util.List;

public class Subject {

    private int id;
    private String name;
    private List<Class> classes;
    private List<Exam> exams;

    public Subject() {
    }

    public Subject(int id, String name, List<Class> classes, List<Exam> exams) {
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.exams = exams;
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
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classes=" + classes +
                ", exams=" + exams +
                '}';
    }
}