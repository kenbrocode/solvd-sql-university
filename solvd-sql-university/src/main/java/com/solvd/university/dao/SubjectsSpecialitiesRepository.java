package com.solvd.university.dao;

import org.apache.ibatis.annotations.Param;

public interface SubjectsSpecialitiesRepository {
    void create(@Param("subjectId") Long subjectId, @Param("specialityId") Long specialityId);
}
