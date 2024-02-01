package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.FacultyRepository;
import com.solvd.university.model.Faculty;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultyRepositoryImpl implements FacultyRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Faculty faculty) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO faculties(title, description, dekan, university_id) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, faculty.getTitle());
            preparedStatement.setString(2, faculty.getDescription());
            preparedStatement.setString(3, faculty.getDekan());
            preparedStatement.setLong(4, faculty.getUniversityId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                faculty.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a faculty", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Optional<Faculty>> findAll() {
        List<Optional<Faculty>> faculties = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        String findAll = "SELECT * FROM faculties";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String dekan = resultSet.getString("dekan");
                Long univerityid = resultSet.getLong("university_id");

                Optional<Faculty> faculty = Optional.of(new Faculty(title, description, dekan, univerityid));
                faculties.add(faculty);
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t find all faculties", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return faculties;
    }
}

