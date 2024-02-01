package com.solvd.university.dao.impl.myBatis;

import com.solvd.university.dao.ConfigMyBatis;
import com.solvd.university.dao.StudentRepository;
import com.solvd.university.model.Student;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryMyBatisImpl implements StudentRepository {

    @Override
    public void create(Student student) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            studentRepository.create(student);
        }
    }

    @Override
    public void delete(Student student) {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            studentRepository.delete(student);
        }
    }

    @Override
    public List<Optional<Student>> findAll() {
        try (SqlSession sqlSession = ConfigMyBatis.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            return studentRepository.findAll();
        }
    }
}

