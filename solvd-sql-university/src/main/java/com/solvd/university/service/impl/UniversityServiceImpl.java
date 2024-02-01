package com.solvd.university.service.impl;

import com.solvd.university.dao.UniversityRepository;
import com.solvd.university.dao.impl.myBatis.UniversityRepositoryMyBatisImpl;
import com.solvd.university.model.University;
import com.solvd.university.service.Service;

import java.util.List;
import java.util.Optional;

public class UniversityServiceImpl implements Service<University> {
    private final UniversityRepository universityRepository;

    public UniversityServiceImpl() {
        this.universityRepository = new UniversityRepositoryMyBatisImpl();
    }

    public void create(University university) {
        if (university.getTitle() != null && university.getRector() != null) {
            university.setId(null);
            universityRepository.create(university);
        }
    }

    public List<Optional<University>> findAll() {
        return universityRepository.findAll();
    }
}
