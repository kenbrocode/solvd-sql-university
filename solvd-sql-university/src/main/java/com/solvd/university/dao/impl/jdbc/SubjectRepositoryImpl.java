package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.SubjectRepository;
import com.solvd.university.model.Subject;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class SubjectRepositoryImpl implements SubjectRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Subject subject) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO subjects(title,decription) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, subject.getTitle());
            preparedStatement.setString(2, subject.getDescription());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                subject.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a subject", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Subject findById(Long id) {
        Subject subject = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            String selectById = "SELECT * FROM  subjects WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                subject = new Subject(title, description);
            }

        } catch (SQLException e) {
            throw new ProcessException("Can`t find a subject", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return subject;
    }
}
