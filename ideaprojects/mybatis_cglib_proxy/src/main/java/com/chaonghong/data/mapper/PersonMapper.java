package com.chaonghong.data.mapper;

import com.chaonghong.data.entity.Person;
import com.chaonghong.data.entity.Student;
import com.chaonghong.data.util.StudentDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonMapper {
    //    @Select("select * from person limit 3")
    // @Results({@Result(id = true, property = "id", column = "id"),
    // @Result(property = "studentName", column = "student_name") })
    public List<Person> getAllStudents();

    public void insert(Person person);

}
