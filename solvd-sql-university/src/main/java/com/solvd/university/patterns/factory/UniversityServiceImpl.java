package com.solvd.university.patterns.factory;

import com.solvd.university.dao.UniversityRepository;
import com.solvd.university.model.Faculty;
import com.solvd.university.model.University;
import com.solvd.university.service.impl.FacultyServiceImpl;

import java.util.List;
import java.util.Optional;

public class UniversityServiceImpl {
    private final UniversityRepository universityRepository;
    private final FacultyServiceImpl facultyService;

    public UniversityServiceImpl(String bdType, String type, FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
        this.universityRepository = AbstractRepositoryFactory.createFactory(bdType).createUniversityRepository(type);
    }


    public List<Optional<University>> findAll() {
        return universityRepository.findAll();
    }

    public University create(University university) {
        universityRepository.create(university);
        for (Faculty faculty : university.getFaculties()) {
            facultyService.create(faculty);
        }
        return university;
    }
}
