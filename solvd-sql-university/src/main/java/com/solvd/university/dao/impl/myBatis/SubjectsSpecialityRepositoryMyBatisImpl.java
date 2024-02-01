package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.SubjectsSpecialitiesRepository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

public class SubjectsSpecialityRepositoryMyBatisImpl implements SubjectsSpecialitiesRepository {

    @Override
    public void create(@Param("subjectId") Long subjectId, @Param("specialityId") Long specialityId) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            SubjectsSpecialitiesRepository subjectSpecialityRepository = sqlSession.getMapper
                    (SubjectsSpecialitiesRepository.class);
            subjectSpecialityRepository.create(subjectId, specialityId);
        }
    }
}
