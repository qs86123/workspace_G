package com.wt.mybatis.interceptor.demo;

import com.wt.mybatis.interceptor.demo.interfaces.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:8:43 2017/11/27
 * @Email:tao8.wang@changhong.com
 */
public class MyPluginChain {
    private List<Interceptor> interceptors;

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public void addInterceptor(Interceptor interceptor) {
        if (this.interceptors == null) {
            this.interceptors = new ArrayList<>();
        }
        this.interceptors.add(interceptor);
    }

    public Object plugInAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }
}
