package db.models;


import java.text.SimpleDateFormat;
import java.util.Date;


public class Exam {

    private int id;
    private Date date;
    private int courseId;
    private int subjectId;


    public Exam(int id, Date date, int courseId, int subjectId) {
        this.id = id;
        this.date = date;
        this.courseId = courseId;
        this.subjectId = subjectId;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Exam{" +
                "id=" + id +
                ", date=" + simpleDateFormat.format(date) +
                ", course=" + courseId +
                ", subject=" + subjectId +
                '}';
    }
}
