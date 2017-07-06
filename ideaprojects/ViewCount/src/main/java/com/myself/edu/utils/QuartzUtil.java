package com.myself.edu.utils;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.myself.edu.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.google.common.collect.Lists;

public enum QuartzUtil {
	INS;
	QuartzUtil() {
		try {
			// First we must get a reference to a scheduler
			SchedulerFactory sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
			// jobs can be scheduled before sched.start() has been called
		} catch (Exception e) {
			Logger.INS.error("{}", e);
		}
	}
	private final List<String> classCronList = Lists.newArrayList();
	private Scheduler sched;
	
	public QuartzUtil addClassCron(String classCron) {
		classCronList.add(classCron);
		return this;
	}
	
	public QuartzUtil addClassCrons(List<String> _classCronList) {
		classCronList.addAll(_classCronList);
		return this;
	}
	
	public QuartzUtil genJobs(Object obj){
		JobDetail job = null;
		CronTrigger trigger = null;
		Date ft = null;
		try {
			for(String classCron : classCronList){
				String className = classCron.replaceAll(":.*", "");
				String cronExpression = classCron.replaceAll(".*:", "");
				job = newJob(Class.forName(className).asSubclass(Job.class))
						.withIdentity("job" + Constants.QUARTZ_JOB_INDEX.getAndIncrement(), Constants.QUARTZ_GROUP_NAME).build();

				trigger = newTrigger()
						.withIdentity("trigger" + Constants.QUARTZ_TRIGGER_INDEX.getAndIncrement(), Constants.QUARTZ_GROUP_NAME)
								.withSchedule(cronSchedule(cronExpression)).build();
				
				if(obj != null){
					job.getJobDataMap().put(job.getKey().toString(), obj);
				}

				ft = sched.scheduleJob(job, trigger);
				Logger.INS.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
						+ trigger.getCronExpression());
				
			}
		} catch (Exception e) {
			Logger.INS.error("{}", e);
		}
		
		return this;
	}
	
	public QuartzUtil shutDown(boolean waitForJobsToComplete){
		try {
			if(sched != null){
				if(!sched.isShutdown()){
					sched.shutdown(waitForJobsToComplete);
				}
			} else {
				throw new NullPointerException();
			}
		} catch (SchedulerException e) {
			Logger.INS.error("{}", e);
		}
		return this;
	}

	public void startJobs(String classCronStr, Object obj) {
		if(StringUtils.isNotBlank(classCronStr)){
			addClassCrons(Arrays.asList(classCronStr.split("\\|"))).genJobs(obj).start();
		}
	}
	
	public QuartzUtil start(){
		try {
			if(sched != null){
				sched.start();
			} else {
				throw new NullPointerException();
			}
		} catch (SchedulerException e) {
			Logger.INS.error("{}", e);
		}
		return this;
	}
}
