package com.chaonghong.data.test;

public class CgLibMainTest
{
    public static void main(String[] args)
    {
        StudentDoService studentDoService = new StudentDoService();
        CgLibProxyFactory cgLibProxyFactory = new CgLibProxyFactory();
        StudentDoService cgLibProxy = cgLibProxyFactory.getCgLibProxy(studentDoService.getClass());

        cgLibProxy.deleteStudent("小明");
        cgLibProxy.getStudent();
        cgLibProxy.updateStudent("何小龙");
    }
}
