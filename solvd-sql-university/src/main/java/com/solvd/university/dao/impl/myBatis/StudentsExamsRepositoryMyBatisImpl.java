package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.StudentsExamsRepository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

public class StudentsExamsRepositoryMyBatisImpl implements StudentsExamsRepository {

    @Override
    public void create(@Param("studentId") Long studentId, @Param("examId") Long examId) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            StudentsExamsRepository studentExamRepository = sqlSession.getMapper(StudentsExamsRepository.class);
            studentExamRepository.create(studentId, examId);
        }
    }
}
