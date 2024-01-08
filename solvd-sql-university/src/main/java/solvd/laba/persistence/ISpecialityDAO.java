package solvd.laba.persistence;

import solvd.laba.domain.Speciality;

import java.util.List;

public interface ISpecialityDAO extends CommonDAO<Speciality> {
    void create(Speciality speciality);

    List<Speciality> getAll();

    void update(Speciality speciality);

    void delete(int id);
}