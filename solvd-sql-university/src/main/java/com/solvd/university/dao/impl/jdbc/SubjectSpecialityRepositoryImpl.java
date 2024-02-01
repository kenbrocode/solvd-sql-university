package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.SubjectsSpecialitiesRepository;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SubjectSpecialityRepositoryImpl implements SubjectsSpecialitiesRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long subjectId, Long specialityId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO specialities_subjects(speciality_id, subject_id) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, specialityId);
            preparedStatement.setLong(2, subjectId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessException("Can`t create a specialities_subjects", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}

