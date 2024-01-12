package solvd.laba.persistence;

import solvd.laba.domain.Department;
import solvd.laba.domain.Speciality;

import java.util.List;

public interface IDepartmentDAO extends CommonDAO<Department> {

    List<Speciality> getAll();

    void update(Department department);

    void delete(int id);
}