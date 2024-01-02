package db.parsers.mybatis;

import db.models.Subject;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ISubjectMapper {
    String INSERT = "INSERT INTO Subjects(name,Specialities_id) VALUES(#{name},#{specialityId});";
    String UPDATE = "UPDATE Subjects SET name #{name}, Specialities_id = #{specialityId} WHERE id = #{id};";
    String DELETE = "DELETE FROM Subjects WHERE id = #{id};";
    String GET_BY_ID = "SELECT * FROM Subjects WHERE id = #{id};";
    String GET_ALL = "SELECT * FROM Subjects ORDER BY id;";

    @Insert(INSERT)
    void insert(Subject subject);

    @Update(UPDATE)
    void update(Subject subject);

    @Delete(DELETE)
    void delete(int id);

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "specialityId", column = "specialities_id")
    })
    @Select(GET_BY_ID)
    Subject getById(int id);

    @Select(GET_ALL)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "specialityId", column = "specialities_id")
    })
    List<Subject> getAll();

}
