package com.wt.quartzdemo.jobs;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:37 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
public class DumbJob implements Job {

    private String jobSays;
    private float myFloatValue;
    private ArrayList state;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        JobDataMap mergedDataMap = context.getMergedJobDataMap();
        System.out.println("dataMap==mergedDataMap?---->" + dataMap.equals(mergedDataMap));
        if (state == null) {
            System.out.println("state==null");
            state = new ArrayList();
        }
        state.add(new Date());
        System.out.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }

    public String getJobSays() {
        return jobSays;
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public float getMyFloatValue() {
        return myFloatValue;
    }

    public void setMyFloatValue(float myFloatValue) {
        this.myFloatValue = myFloatValue;
    }

    public ArrayList getState() {
        return state;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }
}
