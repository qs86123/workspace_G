package com.wt.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:09 2017/7/21
 * @Email:tao8.wang@changhong.com
 */
//@Component
//@PropertySource(value = "classpath:kafka-client.properties")
public class KafkaClientConfig {

    @Autowired
    private static KafkaClientConfig kafkaClientConfig;

    @Value("${zkConn}")
    private String zkConn;
    @Value("${groupId}")
    private String groupId;
    @Value("${topicName}")
    private String topicName;

    public String getZkConn() {
        return zkConn;
    }

    public void setZkConn(String zkConn) {
        this.zkConn = zkConn;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
