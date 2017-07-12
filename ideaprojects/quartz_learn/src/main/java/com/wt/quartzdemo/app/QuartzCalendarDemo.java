package com.wt.quartzdemo.app;

import com.wt.quartzdemo.jobs.QuartzCalendarJob;
import com.wt.quartzdemo.jobs.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Description trigger优先级，日历排除
 * @Author: wangtao
 * @Date:9:18 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class QuartzCalendarDemo {
    public static void main(String[] args) throws SchedulerException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();

        JobDetail job1 = getJob("myjob1", "jobGroup1");
        JobDetail job2 = getJob2("myjob2", "jobGroup2");
        HolidayCalendar cal = new HolidayCalendar();
        //在2017-07-12这一天不执行
        cal.addExcludedDate(sdf.parse("2017-07-13"));

        scheduler.addCalendar("myHolidays", cal, false, true);
        //当多个个trigger的触发时间相同，但是线程池不够的时候，会根据优先级决定先触发哪些触发器，priority值越大优先级越高，正负均可
        Trigger t2 = newTrigger()
                .withIdentity("myTrigger2")
                .withPriority(1)
                //每天14点43执行
                .withSchedule(dailyAtHourAndMinute(14, 43))
                .modifiedByCalendar("myHolidays")
                .build();
        Trigger t = newTrigger()
                .withIdentity("myTrigger1")
                //每天14点43执行
                .withSchedule(dailyAtHourAndMinute(14, 43))
                .modifiedByCalendar("myHolidays")
                .build();
        scheduler.scheduleJob(job1, t2);
        scheduler.scheduleJob(job2, t);
    }

    private static JobDetail getJob(String name, String groupName) {
        return JobBuilder.newJob(QuartzCalendarJob.class).withIdentity(name, groupName).build();
    }

    private static JobDetail getJob2(String name, String groupName) {
        return JobBuilder.newJob(SimpleJob.class).withIdentity(name, groupName).build();
    }
}
