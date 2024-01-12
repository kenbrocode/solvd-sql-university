package solvd.laba.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.xml.bind.annotation.*;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class Exam {


    private int id;


    private Date date;




    private List<Grade> grades;

    public Exam() {
    }

    public Exam(int id, Date date, int courseId, int subjectId) {
        this.id = id;
        this.date = date;

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



    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Exam{" +
                "id=" + id +
                ", date=" + simpleDateFormat.format(date) +
                ", grades=" + grades +
                '}';
    }
}