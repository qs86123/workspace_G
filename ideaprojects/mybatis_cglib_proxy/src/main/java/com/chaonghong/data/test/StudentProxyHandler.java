package com.chaonghong.data.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StudentProxyHandler implements InvocationHandler
{
    StudentService studentService;

    StudentProxyHandler(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println("begin method....");

        Object invoke = method.invoke(studentService, args);
        System.out.println(invoke.toString());

        System.out.println("after method....");
        return null;
    }

}
