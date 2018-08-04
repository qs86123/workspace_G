package com.wt.aop.invoke.hanler;

import com.wt.aop.HandlerType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: wangtao
 * @date:10:05 2018/8/4
 * @email:386427665@qq.com
 */
public class HandlerConfig {

    private static HandlerConfig handlerConfig;

    protected Map<Method, List<ProxyConfig>> proxyMap;

    private HandlerConfig() {
        proxyMap = new HashMap<Method, List<ProxyConfig>>();
    }

    public static HandlerConfig getIncetence() {
        if (handlerConfig == null) {
            handlerConfig = new HandlerConfig();
        }
        return handlerConfig;
    }

    public void addHandler(Method method, ProxyConfig proxyConfig) {
        List<ProxyConfig> list = this.proxyMap.get(method);
        if (list == null) {
            list = new ArrayList<ProxyConfig>();
            this.proxyMap.put(method, list);
        }
        list.add(proxyConfig);
    }

    public List<ProxyConfig> getProxyConfigs(Method method) {
        List<ProxyConfig> proxyConfigs = this.proxyMap.get(method);
        return proxyConfigs == null ? new ArrayList<ProxyConfig>() : proxyConfigs;
    }
}
