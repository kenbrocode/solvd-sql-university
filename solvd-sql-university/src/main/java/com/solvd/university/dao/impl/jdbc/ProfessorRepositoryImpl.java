package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.ProfessorRepository;
import com.solvd.university.model.Professor;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorRepositoryImpl implements ProfessorRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Professor professor) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO proffessors(name, surname, cafedra_id) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getSurname());
            preparedStatement.setLong(3, professor.getCafedraId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                professor.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a proffessor", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Optional<Professor>> findAll() {
        List<Optional<Professor>> professors = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        String findAll = "SELECT * FROM proffessors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Long cafedraId = resultSet.getLong("cafedra_id");

                Optional<Professor> professor = Optional.of(new Professor(name, surname, cafedraId));
                professors.add(professor);
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t find all proffessors", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return professors;
    }

    @Override
    public void delete(Professor professor) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            String deleteByID = "DELETE FROM proffessors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteByID);
            preparedStatement.setLong(1, professor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessException("Can`t delete a proffessor", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
