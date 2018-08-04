package com.wt.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description
 * @author: wangtao
 * @date:9:39 2018/8/4
 * @email:386427665@qq.com
 */
public class AfterHandler extends AbsHandler {

    /**
     * 代理类
     */
    protected Object afterObj;
    protected String method;

    public AfterHandler(Handler target, Object afterObj, String method) {
        this.target=target;
        this.afterObj=afterObj;
        this.method=method;
    }

    public Object getBeforeObj() {
        return afterObj;
    }

    public void setBeforeObj(Object afterObj) {
        this.afterObj = afterObj;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object invoke() {
        try {
            Object o = target.invoke();
            Method m = afterObj.getClass().getMethod(method);
            m.invoke(afterObj);
            return o;
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
