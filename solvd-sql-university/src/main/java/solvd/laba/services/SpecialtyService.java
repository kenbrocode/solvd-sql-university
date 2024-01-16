package solvd.laba.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.config.Config;
import solvd.laba.domain.Specialty;
import solvd.laba.domain.Subject;
import solvd.laba.persistence.ISpecialtyDAO;
import solvd.laba.persistence.RepositoryFactory;


import java.lang.invoke.MethodHandles;
import java.util.List;

public class SpecialtyService implements ISpecialtyService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ISpecialtyDAO specialtyDAO;
    private final ISubjectService subjectService;

    public SpecialtyService() {
        specialtyDAO = RepositoryFactory.createSpecialtyRepository(Config.IMPL.getValue());
        subjectService = new SubjectService();
    }

    @Override
    public void createSpecialty(Specialty specialty, Long departmentId) {
        specialtyDAO.create(specialty, departmentId);
    }

    @Override
    public Specialty getSpecialtyById(Long id) {
        return specialtyDAO.getById(id);
    }

    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyDAO.getAll();
    }

    @Override
    public void updateSpecialty(Specialty specialty) {
        specialtyDAO.update(specialty);
    }

    @Override
    public void deleteSpecialty(Long id) {
        specialtyDAO.delete(id);
    }

    @Override
    public void addSubjectToSpecialty(Specialty specialty, Subject subject) {
        subjectService.createSubject(subject, specialty.getId());
    }
}