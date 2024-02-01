package com.solvd.university.service;

import java.util.List;
import java.util.Optional;

public interface Service <T> {
    void create(T t);

    default T findById(Long id){return null;}

    default List<Optional<T>> findAll() {
        return null;
    }

    default void update(T t) {
        return;
    }

    default void delete(T t) {
        return;
    }
}