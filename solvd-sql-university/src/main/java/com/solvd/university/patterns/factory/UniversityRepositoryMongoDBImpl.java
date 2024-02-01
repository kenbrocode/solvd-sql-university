package com.solvd.university.patterns.factory;

import com.solvd.university.dao.UniversityRepository;
import com.solvd.university.model.University;

import java.util.List;
import java.util.Optional;

public class UniversityRepositoryMongoDBImpl implements UniversityRepository {
    @Override
    public void create(University university) {

    }

    @Override
    public List<Optional<University>> findAll() {
        return null;
    }
}
