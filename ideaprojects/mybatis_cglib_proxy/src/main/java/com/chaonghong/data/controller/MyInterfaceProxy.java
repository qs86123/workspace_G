package com.chaonghong.data.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:12 2017/11/21
 * @Email:tao8.wang@changhong.com
 */
public class MyInterfaceProxy implements InvocationHandler, Serializable {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        new MyInterfaceMethod().doth(method, args);
        return null;
    }
}
