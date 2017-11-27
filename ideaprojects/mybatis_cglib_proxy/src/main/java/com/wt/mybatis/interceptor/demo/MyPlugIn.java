package com.wt.mybatis.interceptor.demo;

import com.wt.mybatis.interceptor.demo.interfaces.Interceptor;
import org.apache.ibatis.reflection.ExceptionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author: wangtao
 * @Date:8:31 2017/11/27
 * @Email:tao8.wang@changhong.com
 */
public class MyPlugIn implements InvocationHandler {

    private Object target;
    private Interceptor interceptor;

    public MyPlugIn(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }


    public static Object wrap(Object target, Interceptor interceptor) {
        Class type = target.getClass();
        return interceptor != null ? Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), new MyPlugIn(target, interceptor)) : target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.getName().equals("print") ? this.interceptor.intercept(this.target, method, args) : method.invoke(this.target, args);
        } catch (Exception var5) {
            throw ExceptionUtil.unwrapThrowable(var5);
        }
    }

}
