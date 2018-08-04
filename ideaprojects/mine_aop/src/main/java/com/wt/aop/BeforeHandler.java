package com.wt.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description
 * @author: wangtao
 * @date:9:39 2018/8/4
 * @email:386427665@qq.com
 */
public class BeforeHandler extends AbsHandler {

    /**
     * 代理类
     */
    protected Object beforeObj;
    protected String method;

    public BeforeHandler() {
    }

    public BeforeHandler(Handler target, Object beforeObj, String method) {
        this.target = target;
        this.beforeObj = beforeObj;
        this.method = method;
    }

    public Object invoke() {
        try {
            Method m = beforeObj.getClass().getMethod(method);
            m.invoke(beforeObj);
            return target.invoke();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
