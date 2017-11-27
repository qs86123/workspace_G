package com.wt.mybatis.interceptor.demo.interfaces;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author: wangtao
 * @Date:8:29 2017/11/27
 * @Email:tao8.wang@changhong.com
 */
public interface Interceptor {

    Object intercept(Object proxy, Method method, Object[] args);

    Object plugin(Object var1);
}
