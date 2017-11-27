package com.chaonghong.data.test;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.NoOp;

public class CgLibProxyFactory implements MethodInterceptor
{
    private Enhancer enhancer = new Enhancer();

    public <T> T getCgLibProxy(Class<T> calss)
    {
        enhancer.setSuperclass(calss);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, NoOp.INSTANCE, this });
        enhancer.setCallbackFilter(new CgLibProxyFilter());
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method method, Object[] arg2, MethodProxy arg3) throws Throwable
    {
        System.out.println("begin method....");
        Object invokeSuper = arg3.invokeSuper(arg0, arg2);
        System.out.println("after method....");

        return null;
    }
}
