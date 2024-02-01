package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.UniversityRepository;
import com.solvd.university.model.University;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class UniversityRepositoryMyBatisImpl implements UniversityRepository {

    @Override
    public void create(University university) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            UniversityRepository universityRepository = sqlSession.getMapper(UniversityRepository.class);
            universityRepository.create(university);
        }
    }

    @Override
    public List<Optional<University>> findAll() {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            UniversityRepository universityRepository = sqlSession.getMapper(UniversityRepository.class);
            return universityRepository.findAll();
        }
    }
}
