package com.solvd.university.patterns.factory;

import com.solvd.university.dao.UniversityRepository;

public interface RepositoryFactory {
    UniversityRepository createUniversityRepository(String type);
}
