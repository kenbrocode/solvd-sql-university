package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.ProfessorRepository;
import com.solvd.university.model.Professor;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ProfessorRepositoryMyBatisImpl implements ProfessorRepository {
    @Override
    public void create(Professor professor) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            ProfessorRepository professorRepository = sqlSession.getMapper(ProfessorRepository.class);
            professorRepository.create(professor);
        }
    }

    @Override
    public void delete(Professor professor) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            ProfessorRepository professorRepository = sqlSession.getMapper(ProfessorRepository.class);
            professorRepository.delete(professor);
        }
    }

    @Override
    public List<Optional<Professor>> findAll() {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            ProfessorRepository professorRepository = sqlSession.getMapper(ProfessorRepository.class);
            return professorRepository.findAll();
        }
    }
}
