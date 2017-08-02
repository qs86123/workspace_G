package com.wt.demo.kafka;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:05 2017/7/20
 * @Email:tao8.wang@changhong.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(Object o) {

        kafkaTemplate.send("accessRecord", new Random().nextInt(10), o.toString());
    }
}

