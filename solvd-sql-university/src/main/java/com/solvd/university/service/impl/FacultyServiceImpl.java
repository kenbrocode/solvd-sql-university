package com.solvd.university.service.impl;

import com.solvd.university.dao.FacultyRepository;
import com.solvd.university.dao.impl.myBatis.FacultyRepositoryMyBatisImpl;
import com.solvd.university.model.Faculty;
import com.solvd.university.service.Service;

import java.util.List;
import java.util.Optional;

public class FacultyServiceImpl implements Service<Faculty> {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl() {
        this.facultyRepository = new FacultyRepositoryMyBatisImpl();
    }

    public void create(Faculty faculty) {
        if (faculty.getTitle() != null && faculty.getDekan() != null && faculty.getUniversityId() != null) {
            faculty.setId(null);
            facultyRepository.create(faculty);
        }
    }

    public List<Optional<Faculty>> findAll() {
        return facultyRepository.findAll();
    }
}
