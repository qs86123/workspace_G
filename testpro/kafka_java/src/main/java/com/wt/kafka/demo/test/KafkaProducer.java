package com.wt.kafka.demo.test;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import scala.util.Random;

/**
 * #####注意：使用时记得修改pom文件对应的kafka的jar包版本
 * kafka0.8.2.2版本的生产者
 * @author The_kid
 *
 */
public class KafkaProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("zk.connect", "192.168.2.14:2181,192.168.2.14:2182,192.168.2.14:2183");
		props.put("metadata.broker.list", "192.168.2.14:9092,192.168.2.14:9093,192.168.2.14:9094");
		// 配置key，value的序列化类
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		// 配置key的序列化类
		// props.put("key.serializer.class", "kafka.serializer.StringEncoder");
		// 配置value的序列化类
		// props.put("value.serializer.class",
		// "kafka.serializer.StringEncoder");

		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config);

		for (int i = 1; i <= 10; i++) {
			try {
				Thread.sleep(500);
				producer.send(new KeyedMessage<String, String>(KafkaCustomer.topic,"key_"+new Random().nextInt(1000),
						"The " + i + " times I've come to buy things.中文"));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("消息生产结束");
	}
}
