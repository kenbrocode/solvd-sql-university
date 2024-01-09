package solvd.laba.services;

import solvd.laba.domain.Department;

import java.util.List;

public interface IDepartmentService {
    void createDepartment(Department department);

    List<Department> getAllDepartments();

    void updateDepartment(Department department);

    void deleteDepartment(int id);

    // Other business-specific methods related to departments could be here
}