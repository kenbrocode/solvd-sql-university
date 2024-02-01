package com.solvd.university.dao;

import com.solvd.university.model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {
    void create(Professor professor);

    void delete(Professor professor);

    List<Optional<Professor>> findAll();
}
