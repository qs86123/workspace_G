package com.wt.demo.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:05 2017/7/25
 * @Email:tao8.wang@changhong.com
 */
public class MyThread extends Thread {

    private boolean isPark = false;

    private int i = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running...." + i);
            i++;
            if (i > 5)
                LockSupport.park(this);
        }
    }

    public void unPark() {
        i = 0;
        LockSupport.unpark(this);
    }


}
