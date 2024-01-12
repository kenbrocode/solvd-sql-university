package solvd.laba.services;

import solvd.laba.domain.Department;
import solvd.laba.domain.Speciality;

import java.util.List;


public interface IDepartmentService {
    void createDepartment(Department department);

    List<Speciality> getAllSpecialities();

    void updateDepartment(Department department);

    void deleteDepartment(int id);
}