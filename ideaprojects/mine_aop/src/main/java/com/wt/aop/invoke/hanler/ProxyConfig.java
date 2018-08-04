package com.wt.aop.invoke.hanler;

import com.wt.aop.HandlerType;

/**
 * @description
 * @author: wangtao
 * @date:10:10 2018/8/4
 * @email:386427665@qq.com
 */
public class ProxyConfig {

    private Object proxy;
    private String method;
    private HandlerType handlerType;

    public ProxyConfig(Object proxy, String method, HandlerType handlerType) {
        this.proxy = proxy;
        this.method = method;
        this.handlerType = handlerType;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    public String getMethod() {
        return method;
    }

    public HandlerType getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(HandlerType handlerType) {
        this.handlerType = handlerType;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
