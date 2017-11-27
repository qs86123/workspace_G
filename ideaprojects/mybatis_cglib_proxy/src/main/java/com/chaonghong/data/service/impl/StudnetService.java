package com.chaonghong.data.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chaonghong.data.entity.Student;
import com.chaonghong.data.entity.Subject;
import com.chaonghong.data.mapper.StudentMapper;

@Service
public class StudnetService
{
    @Autowired
    StudentMapper studentMapper;

    public void getStudents()
    {
        List<Student> list = studentMapper.getAllStudents();
        for (Student student : list)
        {
            System.out.println(student.getStudentName());
        }
    }

    @Transactional // 这个注解不回滚查询操作，其他操作回滚
    public void getStudentById()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 7);
        Student list = studentMapper.getStudentById(map);
        System.out.println(list.getStudentName());
        list.setStudentName("赵六");

        Student student = new Student();
        student.setStudentName("李麻子");
        studentMapper.addStudent(student);

        int a = 1 / 0;
        studentMapper.updateStudent(list);

    }

    public void getStudentWithSubject()
    {
        Student list = studentMapper.getStudentByIds(2);

        for (Subject sub : list.getSubjects())
        {
            System.out.println(sub.getSubjectName());
        }
    }

}
