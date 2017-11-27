package com.chaonghong.data.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:18 2017/11/21
 * @Email:tao8.wang@changhong.com
 */
public class ProxyFactory {

    public static <T> T getProxy(Class<T> clzz, InvocationHandler invokeh) {
        Object o = Proxy.newProxyInstance(clzz.getClassLoader(), new Class[]{clzz}, invokeh);
        return (T) o;
    }

}
