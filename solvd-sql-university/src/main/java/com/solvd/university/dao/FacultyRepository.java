package com.solvd.university.dao;

import com.solvd.university.model.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository {
    void create(Faculty faculty);

    List<Optional<Faculty>> findAll();
}
