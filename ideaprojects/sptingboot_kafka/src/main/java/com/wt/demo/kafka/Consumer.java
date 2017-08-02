package com.wt.demo.kafka;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:08 2017/7/20
 * @Email:tao8.wang@changhong.com
 */

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer extends Thread {

    private final ConsumerConnector consumer;

    private Properties props;

    private String topicName;

    private Integer consumerthreadcount;

    /**
     * 构造函数初始化consumer
     */
    public Consumer() {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
    }

    /**
     * 加载配置
     *
     * @return
     */
    private ConsumerConfig createConsumerConfig() {
        // 读取配置文件
        InputStream is = Consumer.class.getClassLoader().getResourceAsStream("kafka-client.properties");
        props = new Properties();
        try {
            props.load(is);
            this.topicName = props.getProperty("topicName");
            this.consumerthreadcount = Integer.parseInt(props.getProperty("customer.threads.count", "1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 读取属性值
//        Properties props = new Properties();
//        props.put("zookeeper.connect", "192.168.2.14:2181,192.168.2.14:2182,192.168.2.14:2183");
//        props.put("group.id", "myGroup");
//        props.put("auto.offset.reset", "smallest");
//        props.put("zookeeper.session.timeout.ms", "15000");
//        props.put("zookeeper.sync.time.ms", "2000");
//        props.put("auto.commit.interval.ms", "10000");
        return new ConsumerConfig(props);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        //第二个参数，决定消费线程的最大个数
        //线程会在每个stream的it.hasNext中阻塞，也就是说，这里开了多少个线程，就会有多少条线程一直开启，等待消息来临
        //所以这里直接设置线程为1，也就是只开一个线程去监控并读取kafka消息，处理消息的时候再开多线程
        topicCountMap.put(topicName, consumerthreadcount);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        //list的大小和topicCountMap的第二个参数大小相同,每一个分区最多只能一条线程消费，所以线程数还跟分区有关
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topicName);
        //处理消息的线程管理，最多10条线程，线程池的线程会驻留，相当于挂起
        ExecutorService executor = Executors.newFixedThreadPool(consumerthreadcount);
        for (final KafkaStream stream : streams) {
            executor.submit(new KakkaConsumerRunnable(stream));
        }
        System.out.println("");
    }

}

