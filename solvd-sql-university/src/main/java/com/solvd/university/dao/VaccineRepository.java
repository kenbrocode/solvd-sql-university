package com.solvd.university.dao;

import com.solvd.university.model.Vaccine;

public interface VaccineRepository {
    void create(Vaccine vaccine);

    void update(Vaccine vaccine);
}
