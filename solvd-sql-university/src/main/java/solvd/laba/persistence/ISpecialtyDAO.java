package solvd.laba.persistence;

import solvd.laba.domain.Specialty;
import solvd.laba.domain.Subject;

import java.util.List;
public interface ISpecialtyDAO extends CommonDAO<Specialty> {

    void create(Specialty specialty, Long departmentId);

    Specialty getById(Long id);

    List<Specialty> getAll();

    void update(Specialty specialty);

    void delete(Long id);

    List<Specialty> getByDepartment(Long departmentId);

}