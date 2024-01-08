package solvd.laba.domain;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Exam {

    private int id;
    private Date date;
    private List<Integer> courseIds;
    private List<Integer> subjectIds;

    public Exam(int id, Date date, List<Integer> courseIds, List<Integer> subjectIds) {
        this.id = id;
        this.date = date;
        this.courseIds = courseIds;
        this.subjectIds = subjectIds;
    }

    public Exam() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Integer> courseIds) {
        this.courseIds = courseIds;
    }

    public List<Integer> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(List<Integer> subjectIds) {
        this.subjectIds = subjectIds;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Exam{" +
                "id=" + id +
                ", date=" + simpleDateFormat.format(date) +
                ", courseIds=" + courseIds +
                ", subjectIds=" + subjectIds +
                '}';
    }
}