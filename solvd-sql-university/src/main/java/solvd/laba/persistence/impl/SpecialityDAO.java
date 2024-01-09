package solvd.laba.persistence.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.domain.Speciality;
import solvd.laba.persistence.ConnectionPool;
import solvd.laba.persistence.ISpecialityDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAO implements ISpecialityDAO {
    private static final Logger LOGGER = LogManager.getLogger(SpecialityDAO.class.getName());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO Specialities (name, departmentIds) VALUES (?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM Specialities WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM Specialities";
    private static final String UPDATE_QUERY = "UPDATE Specialities SET name = ?, departmentIds = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Specialities WHERE id = ?";

    static Speciality mapSpeciality(ResultSet result) throws SQLException {
        Speciality speciality = new Speciality();
        speciality.setId(result.getInt("id"));
        speciality.setName(result.getString("name"));

        // Map departmentIds from the database result
        String departmentIdsString = result.getString("departmentIds");
        String[] departmentIdsArray = departmentIdsString.split(",");
        List<Integer> departmentIdsList = new ArrayList<>();
        for (String id : departmentIdsArray) {
            departmentIdsList.add(Integer.parseInt(id.trim()));
        }
        speciality.setDepartmentIds(departmentIdsList);

        return speciality;
    }

    @Override
    public void create(Speciality speciality) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, speciality.getName());

            // Convert departmentIds List to a comma-separated string for storage
            String departmentIdsString = String.join(",", speciality.getDepartmentIds());
            preparedStatement.setString(2, departmentIdsString);

            preparedStatement.executeUpdate();
            LOGGER.info("Speciality created: {}", speciality);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Speciality> getAll() throws SQLException {
        List<Speciality> specialities = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                specialities.add(mapSpeciality(result));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return specialities;
    }

    @Override
    public void update(Speciality speciality) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, speciality.getName());
            String departmentIdsString = String.join(",", speciality.getDepartmentIds());
            preparedStatement.setString(2, departmentIdsString);
            preparedStatement.setInt(3, speciality.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Speciality updated: {}", speciality);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Speciality deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}