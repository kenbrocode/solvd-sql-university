package com.solvd.university.dao;

import org.apache.ibatis.annotations.Param;

public interface SubjectsProfessorsRepository {
    void create(@Param("subjectId") Long subjectId, @Param("professorId") Long professorid);
}
