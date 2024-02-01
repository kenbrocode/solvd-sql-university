package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.CafedraRepository;
import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.model.Cafedra;
import org.apache.ibatis.session.SqlSession;

public class CafedraRepositoryMyBatisImpl implements CafedraRepository {

    @Override
    public void create(Cafedra cafedra) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            CafedraRepository cafedraRepository = sqlSession.getMapper(CafedraRepository.class);
            cafedraRepository.create(cafedra);
        }
    }


    @Override
    public void delete(Cafedra cafedra) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            CafedraRepository cafedraRepository = sqlSession.getMapper(CafedraRepository.class);
            cafedraRepository.delete(cafedra);
        }
    }
}
