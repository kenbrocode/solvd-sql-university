package com.solvd.university.service.impl;

import com.solvd.university.dao.ProfessorRepository;
import com.solvd.university.dao.impl.myBatis.ProfessorRepositoryMyBatisImpl;
import com.solvd.university.model.Professor;
import com.solvd.university.service.Service;

import java.util.List;
import java.util.Optional;

public class ProfessorServiceImpl implements Service<Professor> {
    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl() {
        this.professorRepository = new ProfessorRepositoryMyBatisImpl();
    }

    public void create(Professor professor) {
        if (professor.getName() != null & professor.getSurname() != null) {
            professor.setId(null);
            professorRepository.create(professor);
        }
    }

    public List<Optional<Professor>> findAll() {
        return professorRepository.findAll();
    }

    public void delete(Professor professor) {
        professorRepository.delete(professor);
    }
}
