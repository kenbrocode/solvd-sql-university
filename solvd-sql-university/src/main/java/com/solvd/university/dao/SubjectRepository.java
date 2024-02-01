package com.solvd.university.dao;

import com.solvd.university.model.Subject;

public interface SubjectRepository {
    void create(Subject subject);

    Subject findById(Long subjectId);
}
