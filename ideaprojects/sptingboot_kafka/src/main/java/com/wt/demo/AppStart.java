package com.wt.demo;

import com.wt.demo.thread.KafkaThread;
import com.wt.demo.thread.MyThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:06 2017/7/20
 * @Email:tao8.wang@changhong.com
 */
@SpringBootApplication
@EnableKafka
public class AppStart {
    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(AppStart.class, args);
        for (int i = 0; i < 4000000; i++)
            KafkaThread.executor.submit(new KafkaThread());
//        MyThread mt = new MyThread();
//
//        mt.start();
//
//        try {
//
//            Thread.currentThread().sleep(5000);
//
//        } catch (InterruptedException e) {
//
//            e.printStackTrace();
//
//        }
//
//        System.out.println("canyou get here?");
//
//        try {
//
//            Thread.currentThread().sleep(3000);
//            System.out.println("canyou get here?ddddddddd");
//        } catch (InterruptedException e) {
//
//            e.printStackTrace();
//
//        }
//
//        mt.unPark();
//
//        Thread.currentThread().sleep(100000);
    }

}
