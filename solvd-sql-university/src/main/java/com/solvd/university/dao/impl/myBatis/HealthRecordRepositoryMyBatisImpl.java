package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.HealthRecordRepository;
import com.solvd.university.model.HealthRecord;
import org.apache.ibatis.session.SqlSession;

public class HealthRecordRepositoryMyBatisImpl implements HealthRecordRepository {

    @Override
    public void create(HealthRecord healthRecord) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            HealthRecordRepository healthRecordRepository = sqlSession.getMapper(HealthRecordRepository.class);
            healthRecordRepository.create(healthRecord);
        }
    }

    @Override
    public void update(HealthRecord healthRecord) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            HealthRecordRepository healthRecordRepository = sqlSession.getMapper(HealthRecordRepository.class);
            healthRecordRepository.update(healthRecord);
        }
    }

    @Override
    public HealthRecord findById(Long id) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            HealthRecordRepository healthRecordRepository = sqlSession.getMapper(HealthRecordRepository.class);
            return healthRecordRepository.findById(id);
        }
    }
}
