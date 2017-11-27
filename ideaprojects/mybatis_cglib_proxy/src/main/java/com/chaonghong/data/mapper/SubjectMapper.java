package com.chaonghong.data.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.chaonghong.data.entity.Subject;

@Mapper
public interface SubjectMapper
{
    @Select("select id,subject_name as subjectName from subject where id  in( SELECT subject_id FROM student stu LEFT JOIN student_subject ss ON stu.id=ss.student_id where stu.id = #{id}) ")
    public Set<Subject> findSubjectById(int id);
}
