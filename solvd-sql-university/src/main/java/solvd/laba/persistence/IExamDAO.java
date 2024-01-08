package solvd.laba.persistence;

import org.apache.ibatis.annotations.Param;
import solvd.laba.domain.Exam;

import java.util.List;

public interface IExamDAO extends CommonDAO<Exam> {
        void create(@Param("exam") Exam exam, @Param("courseIds") List<Integer> courseIds, @Param("subjectIds") List<Integer> subjectIds);

        void update(Exam exam);

        void delete(int id);
        }
