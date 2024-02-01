package com.solvd.university.dao.impl.jdbc;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.StudentRepository;
import com.solvd.university.model.Student;
import com.solvd.university.model.exceptions.ProcessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Student student) {

        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO students(name, surname, phone_number, email, health_record_id) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getPhoneNumber());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setLong(5, student.getHealthRecordId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                student.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t create a student", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Student student) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            String deleteByID = "DELETE FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteByID);
            preparedStatement.setLong(1, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessException("Can`t delete a student", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Optional<Student>> findAll() {
        List<Optional<Student>> students = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        String findAll = "SELECT * FROM students";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                Long healthRecordId = resultSet.getLong("health_record_id");

                Optional<Student> student = Optional.of(new Student(name, surname, phoneNumber, email, healthRecordId));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new ProcessException("Can`t find all students", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return students;
    }


}
