package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.FacultyRepository;
import com.solvd.university.model.Faculty;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class FacultyRepositoryMyBatisImpl implements FacultyRepository {

    @Override
    public void create(Faculty faculty) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            FacultyRepository facultyRepository = sqlSession.getMapper(FacultyRepository.class);
            facultyRepository.create(faculty);
        }
    }

    @Override
    public List<Optional<Faculty>> findAll() {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            FacultyRepository facultyRepository = sqlSession.getMapper(FacultyRepository.class);
            return facultyRepository.findAll();
        }
    }
}
