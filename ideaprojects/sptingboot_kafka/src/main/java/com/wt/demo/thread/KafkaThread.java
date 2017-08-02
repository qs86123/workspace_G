package com.wt.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:05 2017/7/25
 * @Email:tao8.wang@changhong.com
 */
public class KafkaThread extends Thread {

    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    private List<String> listStr=new ArrayList<String>();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：this is kafkathread" + listStr.size());
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addList(String str) {
        if (listStr == null) {
            listStr = new ArrayList<String>();
            System.out.println("listStr==null");
        }
        listStr.add(str);
        if (listStr.size() > 10)
            executor.submit(new MyThread());
        System.out.println(listStr.size());
    }

}
