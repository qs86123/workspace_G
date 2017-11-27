package com.chaonghong.data.test;

import java.lang.reflect.Proxy;

public class InvocationHandlerMain
{
    public static void main(String[] args)
    {
        StudentService studentService =
            (StudentService) Proxy.newProxyInstance(StudentService.class.getClassLoader(),
                new Class[] {StudentService.class },
                new StudentProxyHandler(new StudentServiceImpl()));

        studentService.getStudent();
    }
}
