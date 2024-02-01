package com.solvd.university.dao;

import org.apache.ibatis.annotations.Param;

public interface StudentsExamsRepository {

    void create(@Param("studentId") Long studentId, @Param("examId") Long examId);
}
