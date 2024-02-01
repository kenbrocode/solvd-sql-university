package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.SubjectRepository;
import com.solvd.university.model.Subject;
import org.apache.ibatis.session.SqlSession;

public class SubjectRepositoryMyBatisImpl implements SubjectRepository {
    @Override
    public void create(Subject subject) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            SubjectRepository subjectRepository = sqlSession.getMapper(SubjectRepository.class);
            subjectRepository.create(subject);
        }
    }

    @Override
    public Subject findById(Long id) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            SubjectRepository subjectRepository = sqlSession.getMapper(SubjectRepository.class);
            return subjectRepository.findById(id);
        }
    }
}
