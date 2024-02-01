package com.solvd.university.service.impl;

import com.solvd.university.dao.SubjectRepository;
import com.solvd.university.dao.impl.myBatis.SubjectRepositoryMyBatisImpl;
import com.solvd.university.model.Subject;
import com.solvd.university.service.Service;

public class SubjectServiceImpl implements Service<Subject> {
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl() {
        this.subjectRepository = new SubjectRepositoryMyBatisImpl();
    }

    public void create(Subject subject) {
        if (subject.getTitle() != null) {
            subject.setId(null);
            subjectRepository.create(subject);
        }
    }

    public Subject findById(Long id) {
        return (Subject) subjectRepository.findById(id);
    }
}
