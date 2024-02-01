package com.solvd.university.service.impl;

import com.solvd.university.dao.SubjectsProfessorsRepository;
import com.solvd.university.dao.impl.myBatis.SubjectsProfessorsRepositoryMyBatisImpl;
import com.solvd.university.model.Professor;
import com.solvd.university.model.Subject;
import com.solvd.university.service.ManyToManyService;

public class SubjectsProfessorsServiceImpl implements ManyToManyService<Subject, Professor> {
    private final SubjectsProfessorsRepository subjectsProfessorsRepository;

    public SubjectsProfessorsServiceImpl() {
        this.subjectsProfessorsRepository = new SubjectsProfessorsRepositoryMyBatisImpl();
    }

    @Override
    public void create(Subject subject, Professor professor) {
        subjectsProfessorsRepository.create(subject.getId(), professor.getId());
    }
}

