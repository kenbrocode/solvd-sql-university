package solvd.laba.persistence.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.domain.Exam;
import solvd.laba.domain.Subject;
import solvd.laba.persistence.ConnectionPool;
import solvd.laba.persistence.ISubjectDAO;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements ISubjectDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String GET_ALL_QUERY = "SELECT * FROM subjects";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM subjects WHERE id = ?";
    private static final String CREATE_QUERY = "INSERT INTO subjects (name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE subjects SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM subjects WHERE id = ?";
    private static final String GET_CLASSES_BY_SUBJECT_QUERY = "SELECT * FROM classes WHERE subject_id = ?";
    private static final String GET_EXAMS_BY_SUBJECT_QUERY = "SELECT * FROM exams WHERE subject_id = ?";

    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                subjects.add(mapRow(result));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return subjects;
    }

    @Override
    public Subject getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return mapRow(result);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return null;
    }

    @Override
    public void create(Subject subject) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                subject.setId(generatedKeys.getLong(1));
                LOGGER.info("Subject created with id: {}", subject.getId());
            } else {
                LOGGER.warn("Failed to retrieve generated id for Subject");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Subject subject) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setLong(2, subject.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Subject updated: {}", subject);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Subject deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Class> getClassesBySubject(Long subjectId) {
        List<Class> classes = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CLASSES_BY_SUBJECT_QUERY)) {
            preparedStatement.setLong(1, subjectId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                // Implement the mapping of class entities and add them to the 'classes' list
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return classes;
    }

    @Override
    public List<Exam> getExamsBySubject(Long subjectId) {
        List<Exam> exams = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_EXAMS_BY_SUBJECT_QUERY)) {
            preparedStatement.setLong(1, subjectId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                // Implement the mapping of exam entities and add them to the 'exams' list
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return exams;
    }

    private Subject mapRow(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id"));
        subject.setName(resultSet.getString("name"));
        return subject;
    }
}