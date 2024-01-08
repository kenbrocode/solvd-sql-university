package solvd.laba.persistence;

import org.apache.ibatis.annotations.Param;
import solvd.laba.domain.Grade;

import java.util.List;

public interface IGradeDAO extends CommonDAO<Grade> {
    void create(@Param("grade") Grade grade, @Param("studentIds") List<Integer> studentIds, @Param("examIds") List<Integer> examIds);

    void update(Grade grade);

    void delete(int id);
}