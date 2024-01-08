package solvd.laba.persistence;

import solvd.laba.domain.Subject;

import java.util.List;

public interface ISubjectDAO extends CommonDAO<Subject> {
    void create(Subject subject);

    List<Subject> getAll();

    void update(Subject subject);

    void delete(int id);
}
