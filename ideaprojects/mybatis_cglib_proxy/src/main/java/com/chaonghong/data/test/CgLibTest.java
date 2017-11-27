package com.chaonghong.data.test;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CgLibTest implements MethodInterceptor
{
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz)
    {
        // 设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        // 通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] aobj, MethodProxy methodproxy)
        throws Throwable
    {
        System.out.println("-------before " + methodproxy.getSuperName() + "-------");
        System.out.println(method.getName());
        Object o1 = methodproxy.invokeSuper(obj, aobj);
        System.out.println("=======after " + methodproxy.getSuperName() + "=======");
        return null;
    }

}

class SayHello
{
    public void say()
    {
        System.out.println("hello everyone");
    }

    public void say(String... strings)
    {
        System.out.println("hello world");
    }
}
