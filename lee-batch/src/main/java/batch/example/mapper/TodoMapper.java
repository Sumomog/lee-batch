package batch.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import batch.example.domain.Todo;

@Mapper
public interface TodoMapper {

    @Insert("INSERT INTO todo (id, title, details, finished) VALUES (#{id}, #{title}, #{details}, #{finished})")
    @Options(useGeneratedKeys = true)
    void insert(Todo todo);

    @Select("SELECT id, title, details, finished FROM todo WHERE id = #{id}")
    Todo select(int id);

//    @Select("SELECT * FROM todo")
    List<Todo> selectAll();

}