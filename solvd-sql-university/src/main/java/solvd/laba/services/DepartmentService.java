package solvd.laba.services;

import solvd.laba.domain.Department;
import solvd.laba.domain.Specialty;
import solvd.laba.persistence.IDepartmentDAO;
import solvd.laba.persistence.impl.DepartmentDAO;

import java.util.List;

public class DepartmentService implements IDepartmentService {

    private final IDepartmentDAO departmentDAO;

    public DepartmentService() {
        this.departmentDAO = new DepartmentDAO();
    }

    @Override
    public void createDepartment(Department department) {
        departmentDAO.create(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentDAO.getById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDAO.getAll();
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDAO.update(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentDAO.delete(id);
    }
}