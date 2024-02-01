package com.solvd.university.patterns.listener;

import java.util.ArrayList;
import java.util.List;

public class Cafedra {

    private String title;

    private List<Speciality> specialities;

    public Cafedra() {
        this.specialities = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void addSpeciality(Speciality speciality) {
        ListenersHolder.onNewSpeciality(speciality);
        specialities.add(speciality);
    }

    public void removeSpeciality(Speciality speciality) {
        ListenersHolder.onDismissSpeciality(speciality);
        specialities.remove(speciality);
    }

}
