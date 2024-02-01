package com.solvd.university.dao;

import com.solvd.university.model.University;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository {
    void create(University university);

    List<Optional<University>> findAll();
}
