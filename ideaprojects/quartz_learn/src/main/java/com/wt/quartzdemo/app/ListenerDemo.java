package com.wt.quartzdemo.app;

import com.wt.quartzdemo.jobs.SimpleJob;
import com.wt.quartzdemo.listener.MyJobListener;
import com.wt.quartzdemo.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:18 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class ListenerDemo {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("myJob", "jobGroup").build();
        Trigger trigger = getTrigger("myTrigger", "triggerGroup");
        JobDetail job2 = JobBuilder.newJob(SimpleJob.class).withIdentity("myJob2", "jobGroup2").build();
        Trigger trigger2 = getTrigger("myTrigger2", "triggerGroup2");
        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);

        //当没有指定xxxMatcher的时候，所有job都监听
//        scheduler.getListenerManager().addJobListener(new MyJobListener());
        //所有job监听也可以这样写
//        scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());

        scheduler.getListenerManager().addJobListener(new MyJobListener(), KeyMatcher.keyEquals(new JobKey("myJob", "jobGroup")));
        //触发器监听和job监听类似
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener(), KeyMatcher.keyEquals(new TriggerKey("myTrigger2", "triggerGroup2")));
    }

    private static SimpleTrigger getTrigger(String name, String groupName) {
        return TriggerBuilder.newTrigger()
                .withIdentity(name, groupName)
                .startNow().
                        withSchedule(
                                SimpleScheduleBuilder
                                        .simpleSchedule()
                                        .withIntervalInSeconds(10)
                                        .repeatForever())
                .build();
    }

    private static JobDetail getJob(String name, String groupName) {
        return JobBuilder.newJob(SimpleJob.class).withIdentity(name, groupName).build();
    }
}
