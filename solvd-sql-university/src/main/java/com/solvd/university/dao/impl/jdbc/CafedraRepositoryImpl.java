package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.CafedraRepository;
import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.model.Cafedra;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class CafedraRepositoryImpl implements CafedraRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Cafedra cafedra) {

        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO cafedries(title, description, faculty_id) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cafedra.getTitle());
            preparedStatement.setString(2, cafedra.getDescription());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                cafedra.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a cafedra", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Cafedra cafedra) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            String deleteByID = "DELETE FROM cafedries WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteByID);
            preparedStatement.setLong(1, cafedra.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessException("Can`t delete a cafedra", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
