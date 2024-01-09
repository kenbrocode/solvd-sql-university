package solvd.laba.persistence;

public interface CommonDAO<T> {

    T getById(Integer id);

    void update(T object);
}