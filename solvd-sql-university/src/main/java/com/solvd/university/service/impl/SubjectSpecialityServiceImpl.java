package com.solvd.university.service.impl;

import com.solvd.university.dao.SubjectsSpecialitiesRepository;
import com.solvd.university.dao.impl.myBatis.SubjectsSpecialityRepositoryMyBatisImpl;
import com.solvd.university.model.Speciality;
import com.solvd.university.model.Subject;
import com.solvd.university.service.ManyToManyService;

public class SubjectSpecialityServiceImpl implements ManyToManyService<Speciality, Subject> {
    private final SubjectsSpecialitiesRepository subjectsSpecialitiesRepository;

    public SubjectSpecialityServiceImpl() {
        this.subjectsSpecialitiesRepository = new SubjectsSpecialityRepositoryMyBatisImpl();
    }

    @Override
    public void create(Speciality speciality, Subject subject) {
        subjectsSpecialitiesRepository.create(subject.getId(), speciality.getId());
    }
}

