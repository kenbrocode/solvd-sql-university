package solvd.laba.services;

import solvd.laba.domain.Department;
import solvd.laba.domain.Speciality;
import solvd.laba.persistence.IDepartmentDAO;

import java.util.List;

public class DepartmentService implements IDepartmentService {

    private IDepartmentDAO departmentDAO;

    public DepartmentService(IDepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }


    @Override
    public void createDepartment(Department department) {

    }

    @Override
    public List<Speciality> getAllSpecialities() {
        return departmentDAO.getAll();
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDAO.update(department);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentDAO.delete(id);
    }

    // Additional methods related to departments could be implemented here
}
