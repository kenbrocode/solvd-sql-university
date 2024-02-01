package com.solvd.university.model;

import java.sql.Date;

public class Payment {
    private Long id;
    private String bankTitle;
    private Long priceId;
    private Long studentId;
    private Date data;

    public Payment() {
    }

    public Payment(String bankTitle, Long priceId, Long studentId, Date data) {
        this.bankTitle = bankTitle;
        this.priceId = priceId;
        this.studentId = studentId;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankTitle() {
        return bankTitle;
    }

    public void setBankTitle(String bankTitle) {
        this.bankTitle = bankTitle;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
