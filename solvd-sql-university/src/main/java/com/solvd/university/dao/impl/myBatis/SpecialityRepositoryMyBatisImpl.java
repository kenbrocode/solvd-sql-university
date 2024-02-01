package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.SpecialityRepository;
import com.solvd.university.model.Speciality;
import org.apache.ibatis.session.SqlSession;

public class SpecialityRepositoryMyBatisImpl implements SpecialityRepository {

    @Override
    public void create(Speciality speciality) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            SpecialityRepository specialityRepository = sqlSession.getMapper(SpecialityRepository.class);
            specialityRepository.create(speciality);
        }
    }

    @Override
    public void update(Speciality speciality) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            SpecialityRepository specialityRepository = sqlSession.getMapper(SpecialityRepository.class);
            specialityRepository.update(speciality);
        }
    }
}
