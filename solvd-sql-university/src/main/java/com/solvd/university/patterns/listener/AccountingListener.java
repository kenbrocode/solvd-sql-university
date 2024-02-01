package com.solvd.university.patterns.listener;

public class AccountingListener implements SpecialityListener {

    @Override
    public void onNewSpeciality(Speciality speciality) {
        System.out.println("Collect a speciality");
    }

    @Override
    public void onDismissSpeciality(Speciality speciality) {
        System.out.println("Revoce speciality");
    }
}
