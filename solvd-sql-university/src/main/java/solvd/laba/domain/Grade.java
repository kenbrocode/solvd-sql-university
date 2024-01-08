package solvd.laba.domain;

import java.util.List;

public class Grade {
    private int id;
    private List<Integer> studentIds;
    private List<Integer> examIds;
    private int grade;

    public Grade() {
    }

    public Grade(int id, List<Integer> studentIds, List<Integer> examIds, int grade) {
        this.id = id;
        this.studentIds = studentIds;
        this.examIds = examIds;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }

    public List<Integer> getExamIds() {
        return examIds;
    }

    public void setExamIds(List<Integer> examIds) {
        this.examIds = examIds;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", studentIds=" + studentIds +
                ", examIds=" + examIds +
                ", grade=" + grade +
                '}';
    }
}