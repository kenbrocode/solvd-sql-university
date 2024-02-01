package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.StudentsExamsRepository;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentsExamsRepositoryImpl implements StudentsExamsRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long studentId, Long examId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO exams_students(exam_id, student_id) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, examId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessException("Can`t create a exams_students", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
