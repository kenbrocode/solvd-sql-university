package solvd.laba.persistence.impl.mybatis;

import org.apache.ibatis.session.SqlSession;
import solvd.laba.config.PersistenceConfig;
import solvd.laba.domain.Exam;
import solvd.laba.domain.Subject;
import solvd.laba.persistence.ISubjectDAO;

import java.util.Collections;
import java.util.List;

public class SubjectMyBatisImpl implements ISubjectDAO {

    @Override
    public List<Subject> getAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISubjectDAO subjectDAO = sqlSession.getMapper(ISubjectDAO.class);
            return subjectDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Subject getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISubjectDAO subjectDAO = sqlSession.getMapper(ISubjectDAO.class);
            return subjectDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Subject subject) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISubjectDAO subjectDAO = sqlSession.getMapper(ISubjectDAO.class);
            subjectDAO.create(subject);
        }
    }

    @Override
    public void update(Subject subject) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISubjectDAO subjectDAO = sqlSession.getMapper(ISubjectDAO.class);
            subjectDAO.update(subject);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISubjectDAO subjectDAO = sqlSession.getMapper(ISubjectDAO.class);
            subjectDAO.delete(id);
        }
    }

    @Override
    public List<Class> getClassesBySubject(Long subjectId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISubjectDAO subjectDAO = sqlSession.getMapper(ISubjectDAO.class);
            return subjectDAO.getClassesBySubject(subjectId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Exam> getExamsBySubject(Long subjectId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ISubjectDAO subjectDAO = sqlSession.getMapper(ISubjectDAO.class);
            return subjectDAO.getExamsBySubject(subjectId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}