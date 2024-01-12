package solvd.laba.persistence;

import solvd.laba.domain.Department;

import java.util.List;

public interface IDepartmentDAO extends CommonDAO<Department> {

    List<Department> getAll();

    void update(Department department);

    void delete(int id);
}