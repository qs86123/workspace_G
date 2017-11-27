package com.chaonghong.data.test;

public class StudentServiceImpl implements StudentService
{

    @Override
    public String getStudent()
    {
        System.out.println("getStudent");
        return "DO METHOD";
    }

    @Override
    public void deleteStudent(String name)
    {
        System.out.println(name);
    }

}
