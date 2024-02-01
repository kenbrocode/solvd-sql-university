package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.VaccineRepository;
import com.solvd.university.model.Vaccine;
import org.apache.ibatis.session.SqlSession;

public class VaccineRepositoryMyBatisImpl implements VaccineRepository {

    @Override
    public void create(Vaccine vaccine) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            VaccineRepository vaccineRepository = sqlSession.getMapper(VaccineRepository.class);
            vaccineRepository.create(vaccine);
        }
    }

    @Override
    public void update(Vaccine vaccine) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            VaccineRepository vaccineRepository = sqlSession.getMapper(VaccineRepository.class);
            vaccineRepository.update(vaccine);
        }
    }
}
