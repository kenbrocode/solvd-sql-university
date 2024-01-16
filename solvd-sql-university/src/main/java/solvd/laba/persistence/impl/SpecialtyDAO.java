package solvd.laba.persistence.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.domain.Specialty;
import solvd.laba.domain.Subject;
import solvd.laba.persistence.ConnectionPool;
import solvd.laba.persistence.ISpecialtyDAO;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecialtyDAO implements ISpecialtyDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO specialties (name, department_id) VALUES (?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM specialties WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM specialties";
    private static final String UPDATE_QUERY = "UPDATE specialties SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM specialties WHERE id = ?";
    private static final String GET_BY_DEPARTMENT_QUERY = "SELECT * FROM specialties WHERE department_id = ?";

    public static List<Specialty> mapRow(ResultSet resultSet, List<Specialty> specialties) throws SQLException {
        if (specialties == null) {
            specialties = new ArrayList<>();
        }

        Long specialtyId = resultSet.getLong("id");

        if (specialtyId != 0) {
            Specialty specialty = findById(specialtyId, specialties);

            specialty.setId(specialtyId);
            specialty.setName(resultSet.getString("name"));

            specialties.add(specialty);
        }

        return specialties;
    }

    private static Specialty findById(Long id, List<Specialty> specialties) {
        return specialties.stream()
                .filter(specialty -> Objects.equals(specialty.getId(), id))
                .findFirst()
                .orElseGet(() -> {
                    Specialty newSpecialty = new Specialty();
                    newSpecialty.setId(id);
                    specialties.add(newSpecialty);
                    return newSpecialty;
                });
    }

    @Override
    public void create(Specialty specialty, Long departmentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setLong(2, departmentId);

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    specialty.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating specialty failed, no ID obtained.");
                }
            }

            LOGGER.info("Specialty created: {}", specialty);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Specialty getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Specialty> specialties = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                specialties = mapRow(result, specialties);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return specialties.get(0);
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialties = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                specialties = mapRow(result, specialties);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return specialties;
    }

    @Override
    public void update(Specialty specialty) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setLong(2, specialty.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Specialty updated: {}", specialty);

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

            LOGGER.info("Specialty deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Specialty> getByDepartment(Long departmentId) {
        List<Specialty> specialties = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DEPARTMENT_QUERY)) {
            preparedStatement.setLong(1, departmentId);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                specialties = mapRow(result, specialties);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return specialties;
    }
}