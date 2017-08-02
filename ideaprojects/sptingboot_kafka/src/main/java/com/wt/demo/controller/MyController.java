package com.wt.demo.controller;

import com.wt.demo.kafka.Consumer;
import com.wt.demo.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:06 2017/7/20
 * @Email:tao8.wang@changhong.com
 */
@RestController
@RequestMapping("/test")
public class MyController {

    @Autowired
    private Producer producer;

//    @Autowired
//    private Consumer consumer;

    @RequestMapping("/w")
    public String testw() {
//        producer.sendMessage("addddddddddddddddd-------------!");
        return "ok";
    }

    @RequestMapping("/r")
    public String testr() {
        new Consumer().start();
        return "ok";
    }

}
