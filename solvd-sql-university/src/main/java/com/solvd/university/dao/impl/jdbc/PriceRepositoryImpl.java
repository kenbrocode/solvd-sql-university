package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.PriceRepository;
import com.solvd.university.model.Price;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;

public class PriceRepositoryImpl implements PriceRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Price price) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO prices(cost, speciality_id) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, price.getCost());
            preparedStatement.setLong(2, price.getSpecialityId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                price.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a price", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Price findById(Long priceId) {
        Price price = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            String selectById = "SELECT * FROM  prices WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setLong(1, priceId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer cost = resultSet.getInt("cost");
                Long specialityId = resultSet.getLong("speciality_id");
                price = new Price(cost, specialityId);
            }

        } catch (SQLException e) {
            throw new ProcessException("Can`t find a price", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return price;
    }
}
