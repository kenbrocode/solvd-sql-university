package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.ExamRepository;
import com.solvd.university.model.Exam;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class ExamRepositoryImpl implements ExamRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Exam exam) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO exams(title, data, descripton, speciality_id, time) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, exam.getTitle());
            preparedStatement.setDate(2, exam.getData());
            preparedStatement.setString(3, exam.getDescription());
            preparedStatement.setLong(4, exam.getSpecialityId());
            preparedStatement.setInt(5, exam.getTime());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                exam.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a exam", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Exam findById(Long examId) {
        Exam exam = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            String selectById = "SELECT * FROM  exams WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setLong(1, examId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                Date date = resultSet.getDate("date");
                String descpription = resultSet.getString("description");
                Long speciality = resultSet.getLong("speciality");
                Integer time = resultSet.getInt("time");
                exam = new Exam(title, date, descpription, speciality, time);
            }

        } catch (SQLException e) {
            throw new ProcessException("Can`t find a exam", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return exam;
    }
}
