package solvd.laba.persistence.impl.mybatis;

import org.apache.ibatis.session.SqlSession;
import solvd.laba.config.PersistenceConfig;
import solvd.laba.domain.Specialty;
import solvd.laba.persistence.ISpecialtyDAO;

import java.util.Collections;
import java.util.List;

public class SpecialtyMyBatisImpl implements ISpecialtyDAO {

    @Override
    public Specialty getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            return specialtyDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Specialty> getAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            return specialtyDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void create(Specialty specialty, Long departmentId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialtyDAO.create(specialty, departmentId);
        }
    }

    @Override
    public void update(Specialty specialty) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialtyDAO.update(specialty);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            specialtyDAO.delete(id);
        }
    }

    @Override
    public List<Specialty> getByDepartment(Long departmentId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISpecialtyDAO specialtyDAO = sqlSession.getMapper(ISpecialtyDAO.class);
            return specialtyDAO.getByDepartment(departmentId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Implement other methods as needed
}