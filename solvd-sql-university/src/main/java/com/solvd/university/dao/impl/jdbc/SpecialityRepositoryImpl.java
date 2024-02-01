package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.SpecialityRepository;
import com.solvd.university.model.Speciality;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class SpecialityRepositoryImpl implements SpecialityRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Speciality speciality) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO specialities(title, description, cafedra_id) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, speciality.getTitle());
            preparedStatement.setString(2, speciality.getDescription());
            preparedStatement.setLong(3, speciality.getCafedraId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                speciality.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a speciality", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Speciality speciality) {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "UPDATE specialities SET title =? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, speciality.getTitle() + "UPDATE");
            preparedStatement.setLong(2, speciality.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessException("Can`t update a speciality", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
