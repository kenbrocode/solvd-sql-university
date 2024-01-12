package solvd.laba.persistence.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.domain.Department;
import solvd.laba.persistence.ConnectionPool;
import solvd.laba.persistence.IDepartmentDAO;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements IDepartmentDAO {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO departments (name) VALUES (?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM departments WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM departments";
    private static final String UPDATE_QUERY = "UPDATE departments SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM departments WHERE id = ?";

    static Department mapDepartment(ResultSet result) throws SQLException {
        Department department = new Department();
        department.setId(result.getInt("id"));
        department.setName(result.getString("name"));

        return department;
    }

    @Override
    public void create(Department department) {
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {

            preparedStatement.setString(1, department.getName());

            preparedStatement.executeUpdate();

            LOGGER.info("Department created: {}", department);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Department getById(Integer id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Department department = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                department = mapDepartment(result);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return department;
    }

    @Override
    public List<Department> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Department> departments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Department department = mapDepartment(result);
                departments.add(department);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return departments;
    }

    @Override
    public void update(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Department updated: {}", department);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Department deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}