package solvd.laba.persistence.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.domain.Department;
import solvd.laba.domain.Specialty;
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
    private static final String GET_BY_ID_QUERY =
            "SELECT " +
                    "   d.id AS department_id, d.name AS department_name, " +
                    "   sp.id AS speciality_id, sp.name AS speciality_name " +
                    "FROM departments d " +
                    "LEFT JOIN specialities sp ON d.id = sp.department_id " +
                    "WHERE d.id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM departments";
    private static final String GET_SPECIALITIES_BY_DEPARTMENT_QUERY = "SELECT * FROM specialities WHERE department_id = ?";
    private static final String DELETE_DEPARTMENT_QUERY = "DELETE FROM departments WHERE id = ?";
    private static final String UPDATE_DEPARTMENT_QUERY = "UPDATE departments SET name = ? WHERE id = ?";

    public static List<Department> mapRow(ResultSet resultSet, List<Department> departments) throws SQLException {
        List<Specialty> specialities = new ArrayList<>();
        if (departments == null) {
            departments = new ArrayList<>();
        }

        Long id = resultSet.getLong("department_id");

        if (id != 0) {
            Department department = findById(id, departments);
            department.setName(resultSet.getString("department_name"));

            specialities = SpecialtyDAO.mapRow(resultSet, specialities);
            department.setSpecialities(specialities);

            departments.add(department);
        }

        return departments;
    }

    private static Department findById(Long id, List<Department> departments) {
        return departments.stream()
                .filter(department -> department.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    Department newDepartment = new Department();
                    newDepartment.setId(id);
                    departments.add(newDepartment);
                    return newDepartment;
                });
    }

    @Override
    public void create(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, department.getName());

            preparedStatement.executeUpdate();

            LOGGER.info("Department created: {}", department);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Department getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Department> departments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {

                mapRow(result, departments);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return departments.get(0);
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Department department = new Department();
                department.setId(result.getLong("id"));
                department.setName(result.getString("name"));
                department.setSpecialities(getSpecialitiesByDepartment(department.getId()));

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

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENT_QUERY)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Department updated: {}", department);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENT_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Department deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Specialty> getSpecialitiesByDepartment(Long departmentId) {
        List<Specialty> specialities = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SPECIALITIES_BY_DEPARTMENT_QUERY)) {
            preparedStatement.setLong(1, departmentId);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Specialty specialty = new Specialty();
                specialty.setId(result.getLong("id"));
                specialty.setName(result.getString("name"));
                // Add other Specialty fields as needed

                specialities.add(specialty);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return specialities;
    }
}