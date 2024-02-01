package com.solvd.university.service.impl;


import com.solvd.university.dao.StudentRepository;
import com.solvd.university.dao.impl.myBatis.StudentRepositoryMyBatisImpl;
import com.solvd.university.model.Student;
import com.solvd.university.service.Service;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements Service<Student> {
    private final StudentRepository studentRepository;

    public StudentServiceImpl() {
        this.studentRepository = new StudentRepositoryMyBatisImpl();
    }

    public void create(Student student) {
        if (student.getName() != null && student.getPhoneNumber() != null && student.getEmail() != null) {
            student.setId(null);
            studentRepository.create(student);
        }

    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public List<Optional<Student>> findAll() {
        return studentRepository.findAll();
    }
}
