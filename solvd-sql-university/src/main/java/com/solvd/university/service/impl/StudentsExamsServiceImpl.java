package com.solvd.university.service.impl;

import com.solvd.university.dao.StudentsExamsRepository;
import com.solvd.university.dao.impl.myBatis.StudentsExamsRepositoryMyBatisImpl;
import com.solvd.university.model.Exam;
import com.solvd.university.model.Student;
import com.solvd.university.service.ManyToManyService;

public class StudentsExamsServiceImpl implements ManyToManyService<Student, Exam> {
    private final StudentsExamsRepository studentsExamsRepository;

    public StudentsExamsServiceImpl() {
        this.studentsExamsRepository = new StudentsExamsRepositoryMyBatisImpl();
    }

    @Override
    public void create(Student student, Exam exam) {
        studentsExamsRepository.create(student.getId(), exam.getId());
    }
}
