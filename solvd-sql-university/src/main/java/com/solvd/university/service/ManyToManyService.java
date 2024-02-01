package com.solvd.university.service;

public interface ManyToManyService<T, R> {
    void create(T t, R r);
}
