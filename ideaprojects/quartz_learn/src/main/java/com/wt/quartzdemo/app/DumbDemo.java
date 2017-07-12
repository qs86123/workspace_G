package com.wt.quartzdemo.app;

import com.wt.quartzdemo.jobs.DumbJob;
import com.wt.quartzdemo.jobs.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:18 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class DumbDemo {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        JobDetail job = JobBuilder.newJob(DumbJob.class).withIdentity("myJob", "jobGroup")
                .usingJobData("jobSays", "Hello World!")
                .usingJobData("myFloatValue", 3.141f)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "triggerGroup")
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
