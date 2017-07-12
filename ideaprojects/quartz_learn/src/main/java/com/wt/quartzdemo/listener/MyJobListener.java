package com.wt.quartzdemo.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:40 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class MyJobListener implements JobListener{
    @Override
    public String getName() {
        return "aaa";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("jobToBeExecuted");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("jobWasExecuted");
    }
}
