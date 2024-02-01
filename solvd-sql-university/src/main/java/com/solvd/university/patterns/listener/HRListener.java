package com.solvd.university.patterns.listener;

public class HRListener implements SpecialityListener {

    @Override
    public void onNewSpeciality(Speciality speciality) {
        System.out.println("Provide speciality funding");
    }

    @Override
    public void onDismissSpeciality(Speciality speciality) {
        System.out.println("Removed funding");
    }
}
