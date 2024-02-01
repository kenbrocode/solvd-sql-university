package com.solvd.university.model;

public class Professor {
    private Long id;
    private String name;
    private String surname;
    private Long cafedraId;

    public Professor() {
    }

    public Professor(String name, String surname, Long cafedraId) {
        this.name = name;
        this.surname = surname;
        this.cafedraId = cafedraId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getCafedraId() {
        return cafedraId;
    }

    public void setCafedraId(Long cafedraId) {
        this.cafedraId = cafedraId;
    }
}
