package com.chaonghong.data.controller;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:14 2017/11/21
 * @Email:tao8.wang@changhong.com
 */
public class MyInterfaceMethod {

    public void doth(Method method, Object[] args) {
        System.out.println("methodName:" + method.getName());
        for (Object o : args)
            if (o instanceof String)
                System.out.println(">>>>>>>>:" + o);
    }
}
