package solvd.laba.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.domain.Exam;
import solvd.laba.domain.Subject;
import solvd.laba.persistence.ISubjectDAO;
import solvd.laba.persistence.impl.SubjectDAO;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class SubjectService implements ISubjectService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final ISubjectDAO subjectDAO;

    public SubjectService() {
        this.subjectDAO = new SubjectDAO();
    }

    @Override
    public void createSubject(Subject subject, long id) {
        subjectDAO.create(subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectDAO.getById(id);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAll();
    }

    @Override
    public void updateSubject(Subject subject) {
        subjectDAO.update(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectDAO.delete(id);
    }

    @Override
    public List<Class> getClassesBySubject(Long subjectId) {
        return subjectDAO.getClassesBySubject(subjectId);
    }

    @Override
    public List<Exam> getExamsBySubject(Long subjectId) {
        return subjectDAO.getExamsBySubject(subjectId);
    }
}
