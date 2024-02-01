package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.SubjectsProfessorsRepository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

public class SubjectsProfessorsRepositoryMyBatisImpl implements SubjectsProfessorsRepository {
    @Override
    public void create(@Param("subjectId") Long subjectId, @Param("professorId") Long professorId) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            SubjectsProfessorsRepository subjectProfessorRepository =
                    sqlSession.getMapper(SubjectsProfessorsRepository.class);
            subjectProfessorRepository.create(subjectId, professorId);
        }
    }
}
