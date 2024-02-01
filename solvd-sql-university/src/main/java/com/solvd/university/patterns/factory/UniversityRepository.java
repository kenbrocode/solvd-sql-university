package com.solvd.university.patterns.factory;

import com.solvd.university.model.University;

public interface UniversityRepository {
    University create(University university);
}
