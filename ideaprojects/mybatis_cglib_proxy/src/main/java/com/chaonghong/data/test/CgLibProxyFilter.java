package com.chaonghong.data.test;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.CallbackFilter;

public class CgLibProxyFilter implements CallbackFilter {

    /**
     * 改方法返回0/1...标识且不允许返回比 enhancer.setCallbacks参数中的Callback数组的索引大
     * <p>
     * 返回的猪与enhancer.setCallbacks参数中的Callback数组中的拦截器想对应
     */
    @Override
    public int accept(Method method) {
        if (method.getName().equalsIgnoreCase("getStudent")) {
            return 0;
        } else if (method.getName().equalsIgnoreCase("deleteStudent")) {
            return 1;
        }
        return 2;

    }

}
