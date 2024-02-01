package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.PaymentRepository;
import com.solvd.university.model.Payment;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class PaymentRepositoryImpl implements PaymentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Payment payment) {

        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO payment(bank_title, price_id, student_id, data) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, payment.getBankTitle());
            preparedStatement.setLong(2, payment.getPriceId());
            preparedStatement.setLong(3, payment.getStudentId());
            preparedStatement.setDate(4, payment.getData());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                payment.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a payment", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Payment payment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            String deleteByID = "DELETE FROM payment WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteByID);
            preparedStatement.setLong(1, payment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessException("Can`t delete a payment", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
