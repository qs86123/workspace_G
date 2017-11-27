package com.wt.mybatis.interceptor.demo;

import com.wt.mybatis.interceptor.demo.interfaces.Interceptor;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author: wangtao
 * @Date:8:54 2017/11/27
 * @Email:tao8.wang@changhong.com
 */
public class Interceptor1 implements Interceptor {

    private String msg;

    public Interceptor1(String msg) {
        this.msg = msg;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args) {
        System.out.println(this.msg);
        try {
            return method.invoke(proxy, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object plugin(Object var1) {
        return MyPlugIn.wrap(var1, this);
    }
}
