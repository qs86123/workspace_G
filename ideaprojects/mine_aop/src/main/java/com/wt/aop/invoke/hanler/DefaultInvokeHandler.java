package com.wt.aop.invoke.hanler;

import com.wt.aop.AfterHandler;
import com.wt.aop.BeforeHandler;
import com.wt.aop.Handler;
import com.wt.aop.HandlerType;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @description
 * @author: wangtao
 * @date:10:02 2018/8/4
 * @email:386427665@qq.com
 */
public class DefaultInvokeHandler implements InvocationHandler {

    private Handler target;
    private Object self;

    public DefaultInvokeHandler(Handler target,Object self) {
        this.target = target;
        this.self=self;
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        HandlerConfig handlerConfig = HandlerConfig.getIncetence();
        List<ProxyConfig> proxyConfigs = handlerConfig.getProxyConfigs(method);
        target = new Handler() {
            public Object invoke() {
                try {
                    return method.invoke(self, args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        for (ProxyConfig config : proxyConfigs) {
            if (config.getHandlerType().equals(HandlerType.BEFORE)) {
                target = new BeforeHandler(target, config.getProxy(), config.getMethod());
            }
            if (config.getHandlerType().equals(HandlerType.AFTER)) {
                target = new AfterHandler(target, config.getProxy(), config.getMethod());
            }
        }
        return target.invoke();
    }

}
