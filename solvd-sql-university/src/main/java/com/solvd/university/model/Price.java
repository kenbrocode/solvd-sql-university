package com.solvd.university.model;

public class Price {
    private Long id;
    private int cost;
    private Long specialityId;

    public Price() {
    }

    public Price(Integer cost, Long specialityId) {
        this.cost = cost;
        this.specialityId = specialityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }
}
