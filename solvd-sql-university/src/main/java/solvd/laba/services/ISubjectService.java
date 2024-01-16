package solvd.laba.services;

import solvd.laba.domain.Exam;
import solvd.laba.domain.Subject;

import java.util.List;

public interface ISubjectService {
    void createSubject(Subject subject, long id);

    Subject getSubjectById(Long id);

    List<Subject> getAllSubjects();

    void updateSubject(Subject subject);

    void deleteSubject(Long id);

    List<Class> getClassesBySubject(Long subjectId);

    List<Exam> getExamsBySubject(Long subjectId);
}