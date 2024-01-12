package solvd.laba.domain;


import java.sql.Date;
import java.util.List;

public class Class {
    private int id;

    private Date date;


    public Class() {
    }

    public Class(int id, Date date) {
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


    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
