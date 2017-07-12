package com.wt.quartzdemo.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.quartz.listeners.TriggerListenerSupport;

/**
 * @Description
 * @Author: wangtao
 * @Date:17:08 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class MyTriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return "trigerListenername";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("triggerFired");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("triggerMisfired");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        System.out.println("triggerComplete");
    }
}
