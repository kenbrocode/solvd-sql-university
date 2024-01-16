package solvd.laba.services;

import solvd.laba.domain.Specialty;
import solvd.laba.domain.Subject;

import java.util.List;

public interface ISpecialtyService {
    void createSpecialty(Specialty specialty, Long departmentId);

    Specialty getSpecialtyById(Long id);

    List<Specialty> getAllSpecialties();

    void updateSpecialty(Specialty specialty);

    void deleteSpecialty(Long id);

    void addSubjectToSpecialty(Specialty specialty, Subject subject);
}