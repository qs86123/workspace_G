package com.wt.quartzdemo.app;

import com.wt.quartzdemo.jobs.ManyInstenceJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:18 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class ManyInstenceDemo {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        JobDetail job1 = getJob("myjob1", "jobGroup1");
        job1.getJobDataMap().put(ManyInstenceJob.JOB_DATA_MAP_KEY, 10);
        scheduler.scheduleJob(job1, getTrigger("myTrigger1", "triggerGroup1"));
    }

    private static SimpleTrigger getTrigger(String name, String groupName) {
        return TriggerBuilder.newTrigger().withIdentity(name, groupName)
                .startNow().
                        withSchedule(
                                SimpleScheduleBuilder
                                        .simpleSchedule()
                                        .withIntervalInSeconds(5)
                                        .withRepeatCount(4))
                .build();
    }

    private static JobDetail getJob(String name, String groupName) {
        return JobBuilder.newJob(ManyInstenceJob.class).withIdentity(name, groupName).build();
    }
}
