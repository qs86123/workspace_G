package com.wt.quartzdemo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Random;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:33 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getJobDetail().getKey() + ":---->this is simple job");
    }
}
