package com.wt.quartzdemo.app;

import com.wt.quartzdemo.jobs.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:18 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class SimpleDemo {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("myJob", "jobGroup").build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .startNow().
                        withSchedule(
                                SimpleScheduleBuilder
                                        .simpleSchedule()
                                        .withIntervalInSeconds(10)
                                        .repeatForever())
                .build();
        scheduler.scheduleJob(job, trigger);
    }
}
