package solvd.laba.services;

import solvd.laba.domain.Department;
import solvd.laba.domain.Specialty;

import java.util.List;


public interface IDepartmentService {
    void createDepartment(Department department);

    Department getDepartmentById(Long id);

    List<Department> getAllDepartments();

    void updateDepartment(Department department);

    void deleteDepartment(Long id);
}