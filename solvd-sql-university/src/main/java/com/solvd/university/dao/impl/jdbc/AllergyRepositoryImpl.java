package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.AllergyRepository;
import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.model.Allergy;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class AllergyRepositoryImpl implements AllergyRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Allergy allergy) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO allergies(title, pills, date, health_record_id) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, allergy.getTitle());
            preparedStatement.setString(2, allergy.getPills());
            preparedStatement.setDate(3, allergy.getDate());
            preparedStatement.setLong(4, allergy.getHealthRecordId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                allergy.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a allergy", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Allergy allergy) {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "UPDATE allergies SET pills =? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, allergy.getPills());
            preparedStatement.setLong(2, allergy.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessException("Can`t update a allergy", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
