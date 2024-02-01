package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.UniversityRepository;
import com.solvd.university.model.Faculty;
import com.solvd.university.model.University;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniversityRepositoryImpl implements UniversityRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(University university) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO universities(title, rector) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, university.getTitle());
            preparedStatement.setString(2, university.getRector());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                university.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a university", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Optional<University>> findAll() {
        List<Optional<University>> universities = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        String findAll = "SELECT * FROM universities";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String rector = resultSet.getString("rector");
                Optional<University> university = Optional.of(new University(title, rector));
                universities.add(university);
            }

        } catch (SQLException e) {
            throw new ProcessException("Can`t find all universities", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return universities;
    }
}
