package com.solvd.university.dao;

import com.solvd.university.model.Exam;

public interface ExamRepository {
    void create(Exam exam);

    Exam findById(Long examId);
}
