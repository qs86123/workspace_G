package com.wt.aop;

/**
 * @description
 * @author: wangtao
 * @date:9:35 2018/8/4
 * @email:386427665@qq.com
 */
public abstract class AbsHandler implements Handler {
    /**
     * 被代理对象
     */
    protected Handler target;

    public Handler getTarget() {
        return target;
    }

    public void setTarget(Handler target) {
        this.target = target;
    }
}
