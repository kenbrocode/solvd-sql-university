package solvd.laba.persistence;

import solvd.laba.domain.Speciality;

import java.sql.SQLException;
import java.util.List;

public interface ISpecialityDAO extends CommonDAO<Speciality> {
    void create(Speciality speciality) throws SQLException;

    List<Speciality> getAll() throws SQLException;

    void update(Speciality speciality);

    void delete(int id) throws SQLException;

    void addDepartmentsToSpeciality(Speciality speciality, List<Integer> departmentIds) throws SQLException;

    void deleteDepartmentsFromSpeciality(Speciality speciality, List<Integer> departmentIds) throws SQLException;

    List<Integer> getDepartmentsBySpeciality(Speciality speciality) throws SQLException;

    Speciality getSpecialityByDepartmentIds(List<Integer> departmentIds) throws SQLException;
}