package com.wt.quartzdemo.jobs;


import org.quartz.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:33 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class QuartzCalendarJob implements Job {

    private int myCount = 0;
    private static int myStaticCount = 0;
    public static final String JOB_DATA_MAP_KEY = "key1";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getJobDetail().getKey() + ":--->this is QuartzCalendarJob");
    }
}
