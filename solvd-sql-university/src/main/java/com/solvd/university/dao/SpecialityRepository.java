package com.solvd.university.dao;

import com.solvd.university.model.Speciality;

public interface SpecialityRepository {
    void create(Speciality speciality);

    void update(Speciality speciality);
}
