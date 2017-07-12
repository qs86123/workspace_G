package com.wt.quartzdemo.jobs;


import org.quartz.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:33 2017/7/12
 * @Email:tao8.wang@changhong.com
 */
//由于每次执行job都是重新实例化的，所以参数不会保存，该注解就是为了将参数序列化保存，以便下次取到的参数是上次任务执行完之后改过的
//如果不加这个注解，JobDataMap保存的变量的值将不会被序列化，也就是说每次任务启动都是初始的值
@PersistJobDataAfterExecution
//该注解表示不允许当前任务并行执行，用来防止同一个job的多个实例同时改动序列化的jobDataMap的值的时候发生混乱
//这两个注解一般都同时存在
@DisallowConcurrentExecution
public class ManyInstenceJob implements Job {

    private int myCount = 0;
    private static int myStaticCount = 0;
    public static final String JOB_DATA_MAP_KEY = "key1";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        JobDataMap data = context.getJobDetail().getJobDataMap();
        int jobDataMapInt = data.getInt(JOB_DATA_MAP_KEY);
        //这里注释的内容用于理解DisallowConcurrentExecution这个注解，
        //目的就是让上一个job实例还未执行完的时候，下一个job的开始时间就已经到了，观察第二个job实例会不会有输出
        //结果，程序卡在上一个实例，直到上衣个实例执行完，才执行下一个实例
        if (jobDataMapInt == 11) {
          try {
              Thread.sleep(11000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        }
        System.out.println(jobName + "***private成员变量为:" + myCount + ",static成员变量为:" + myStaticCount + ",JobDataMap保存的变量为:" + jobDataMapInt);
        myCount++;
        jobDataMapInt++;
        data.put(JOB_DATA_MAP_KEY, jobDataMapInt);
        myStaticCount++;
    }
}
