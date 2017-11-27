package com.chaonghong.data.test;

public class StudentDoService
{
    public String getStudent()
    {
        System.out.println("getStudent");
        return "DO METHOD";
    }

    public void deleteStudent(String name)
    {
        System.out.println(name + " helloWorld");
    }

    public void updateStudent(String name)
    {
        System.out.println("我是大肥龙");
    }
}
