package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.VaccineRepository;
import com.solvd.university.model.Vaccine;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class VaccineRepositoryImpl implements VaccineRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Vaccine vaccine) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO vaccines(title, description, data, health_record_id) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vaccine.getTitle());
            preparedStatement.setString(2, vaccine.getDescription());
            preparedStatement.setDate(3, vaccine.getData());
            preparedStatement.setLong(4, vaccine.getHealthRecordId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                vaccine.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a vaccine", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Vaccine vaccine) {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "UPDATE vaccines SET title = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, vaccine.getTitle());
            preparedStatement.setLong(2, vaccine.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessException("Can`t update a vaccine", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
