package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.HealthRecordRepository;
import com.solvd.university.model.HealthRecord;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class HealthRecordRepositoryImpl implements HealthRecordRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(HealthRecord healthRecord) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO health_records(medical_report) values (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, healthRecord.getMedicalReport());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                healthRecord.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a healthRecord", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(HealthRecord healthRecord) {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "UPDATE health_records SET medical_report =? WHERE health_record_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, healthRecord.getMedicalReport());
            preparedStatement.setLong(2, healthRecord.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessException("Can`t update a healthRecord", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public HealthRecord findById(Long id) {
        HealthRecord healthRecord = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            String selectById = "SELECT * FROM  exams WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String medicalReport = resultSet.getString("medical_report");
                healthRecord = new HealthRecord(medicalReport);

            }

        } catch (SQLException e) {
            throw new ProcessException("Can`t find a health Record", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return healthRecord;
    }

}
