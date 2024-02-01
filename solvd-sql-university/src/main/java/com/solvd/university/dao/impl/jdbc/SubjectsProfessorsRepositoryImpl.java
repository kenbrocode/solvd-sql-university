package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.SubjectsProfessorsRepository;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SubjectsProfessorsRepositoryImpl implements SubjectsProfessorsRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long subjectId, Long professorId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO proffessors_subjects(proffessor_id, subject_id) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, professorId);
            preparedStatement.setLong(2, subjectId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessException("Can`t create a professors_subjects", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}

