package com.solvd.university.patterns.listener;

import java.util.ArrayList;
import java.util.List;

public class ListenersHolder {
    private static final List<SpecialityListener> listeners = new ArrayList<>();

    public static void subcribe(SpecialityListener specialityListener) {
        listeners.add(specialityListener);
    }

    public static void unsubcribe(SpecialityListener specialityListener) {
        listeners.remove(specialityListener);
    }

    public static void onNewSpeciality(Speciality speciality) {
        for (SpecialityListener specialityListener : listeners) {
            specialityListener.onNewSpeciality(speciality);
        }
    }

    public static void onDismissSpeciality(Speciality speciality) {
        for (SpecialityListener specialityListener : listeners) {
            specialityListener.onDismissSpeciality(speciality);
        }
    }
}
