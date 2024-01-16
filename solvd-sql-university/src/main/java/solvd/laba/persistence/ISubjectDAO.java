package solvd.laba.persistence;

import solvd.laba.domain.Exam;
import solvd.laba.domain.Subject;

import java.util.List;

public interface ISubjectDAO extends CommonDAO<Subject> {
    List<Subject> getAll();

    Subject getById(Long id);

    void create(Subject subject);

    void update(Subject subject);

    void delete(Long id);

    List<Class> getClassesBySubject(Long subjectId);

    List<Exam> getExamsBySubject(Long subjectId);
}
