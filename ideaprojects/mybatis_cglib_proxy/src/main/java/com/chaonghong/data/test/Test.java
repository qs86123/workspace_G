package com.chaonghong.data.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test
{
    public static void main(String[] args)
    {
        Object o = getProxy();
        System.out.println(123);
        TestInterface test = (TestInterface) o;
        test.doTestWithResult("gaga");

    }

    public static Object getProxy()
    {
        TestInterface tt = new TestClass();
        Object o = null;
        o = Proxy.newProxyInstance(TestInterface.class.getClassLoader(),
            new Class[] {TestInterface.class },
            new TestProxy(tt));
        return o;
    }
}

interface TestInterface
{
    void doTest();

    String doTestWithResult(String str);
}

class TestClass implements TestInterface
{

    @Override
    public void doTest()
    {
        System.out.println("doTest");
    }

    @Override
    public String doTestWithResult(String str)
    {
        System.out.println(str);
        return str;
    }

}

class TestProxy implements InvocationHandler
{
    private Object object;

    public TestProxy()
    {
    }

    public TestProxy(Object obj)
    {
        this.object = obj;
    }

    public Object invoke(Object obj, Method method, Object[] aobj) throws Throwable
    {
        String methodName = method.getName();
        String methodResult = (String) method.invoke(object, "hello");

        String methodResult2 = (String) method.invoke(obj, "helloworld");
        System.out.println(22222);
        return null;
    }

}
