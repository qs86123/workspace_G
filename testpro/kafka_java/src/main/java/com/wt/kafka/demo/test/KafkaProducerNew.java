package com.wt.kafka.demo.test;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import scala.util.Random;

/**
 * #####注意：使用时记得修改pom文件对应的kafka的jar包版本
 * kafka0.10.1.0版本的生产者
 * @author The_kid
 *
 */
public class KafkaProducerNew {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("zk.connect", "192.168.2.14:2181,192.168.2.14:2182,192.168.2.14:2183");
		props.put("bootstrap.servers", "192.168.2.14:9092,192.168.2.14:9093,192.168.2.14:9094");
		// 配置key的序列化类,这里配置的序列化类不能用StringEncoder了
		// 这个序列化类需要是org.apache.kafka.common.serialization.Serializer<T>的子类
		// 自己写一个类A，继承至StringEncoder，这里就填A，就会报错说A类不是继承自org.apache.kafka.common.serialization.Serializer的类，
		// 然后去找org.apache.kafka.common.serialization.Serializer的子类，发现有个StringSerializer
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 配置value的序列化类
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ProducerRecord<String, String> producerrecord = new ProducerRecord<String, String>(KafkaCustomer.topic,
					"key" + new Random().nextInt(100), "haha" + i);
			Callback callback = new Callback() {

				@Override
				public void onCompletion(RecordMetadata paramRecordMetadata, Exception paramException) {
					System.out.println(paramRecordMetadata.topic());
					System.out.println(paramException.getMessage());
				}
			};
			producer.send(producerrecord, callback);
		}
		producer.close();
	}
}
