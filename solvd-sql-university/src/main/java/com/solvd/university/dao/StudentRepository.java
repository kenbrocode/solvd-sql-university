package com.solvd.university.dao;

import com.solvd.university.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void create(Student student);

    void delete(Student student);

    List<Optional<Student>> findAll();
}
