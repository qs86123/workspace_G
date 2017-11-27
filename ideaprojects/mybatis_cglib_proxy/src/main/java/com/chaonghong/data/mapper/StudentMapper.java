package com.chaonghong.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.chaonghong.data.entity.Student;
import com.chaonghong.data.util.StudentDynaSqlProvider;

@Mapper
public interface StudentMapper
{
    @Select("select * from person limit 3")
    // @Results({@Result(id = true, property = "id", column = "id"),
    // @Result(property = "studentName", column = "student_name") })
    public List<Student> getAllStudents();

    @SelectProvider(method = "getStudentById", type = StudentDynaSqlProvider.class)
    @Results({@Result(id = true, property = "id", column = "id"),
        @Result(property = "studentName", column = "student_name") })
    public Student getStudentById(Map<String, Object> map);

    @Select("SELECT * FROM student  where id = #{id}")
    @Results({@Result(id = true, column = "id", property = "id"),
        @Result(property = "studentName", column = "student_name"),
        @Result(property = "subjects", column = "id", many = @Many(select = "com.chaonghong.data.mapper.SubjectMapper.findSubjectById", fetchType = FetchType.LAZY)) })
    public Student getStudentByIds(int id);

    @Insert("INSERT into student values(null,#{student.studentName})")
    public void addStudent(@Param("student") Student student);

    @Update("UPDATE student SET student.student_name = #{student.studentName} WHERE id =  #{student.id})")
    public void updateStudent(@Param("student") Student student);
}
