package solvd.laba.persistence.impl.mybatis;

import org.apache.ibatis.session.SqlSession;
import solvd.laba.config.PersistenceConfig;
import solvd.laba.domain.Department;
import solvd.laba.domain.Specialty;
import solvd.laba.persistence.IDepartmentDAO;

import java.util.Collections;
import java.util.List;

public class DepartmentMyBatisImpl implements IDepartmentDAO {

    @Override
    public Department getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IDepartmentDAO departmentDAO = sqlSession.getMapper(IDepartmentDAO.class);
            return departmentDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Department> getAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IDepartmentDAO departmentDAO = sqlSession.getMapper(IDepartmentDAO.class);
            return departmentDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void create(Department department) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IDepartmentDAO departmentDAO = sqlSession.getMapper(IDepartmentDAO.class);
            departmentDAO.create(department);
        }
    }

    @Override
    public void update(Department department) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IDepartmentDAO departmentDAO = sqlSession.getMapper(IDepartmentDAO.class);
            departmentDAO.update(department);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IDepartmentDAO departmentDAO = sqlSession.getMapper(IDepartmentDAO.class);
            departmentDAO.delete(id);
        }
    }

    @Override
    public List<Specialty> getSpecialitiesByDepartment(Long departmentId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IDepartmentDAO departmentDAO = sqlSession.getMapper(IDepartmentDAO.class);
            return departmentDAO.getSpecialitiesByDepartment(departmentId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}