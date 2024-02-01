package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.ExamRepository;
import com.solvd.university.model.Exam;
import org.apache.ibatis.session.SqlSession;

public class ExamRepositoryMyBatisImpl implements ExamRepository {
    @Override
    public void create(Exam exam) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            ExamRepository examRepository = sqlSession.getMapper(ExamRepository.class);
            examRepository.create(exam);
        }
    }

    @Override
    public Exam findById(Long id) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            ExamRepository examRepository = sqlSession.getMapper(ExamRepository.class);
            return examRepository.findById(id);
        }
    }
}
