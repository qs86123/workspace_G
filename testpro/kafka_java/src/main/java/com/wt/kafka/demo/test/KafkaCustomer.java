package com.wt.kafka.demo.test;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

/**
 * #####注意：使用时记得修改pom文件对应的kafka的jar包版本
 * kafka0.8.2.2和kafka0.10.1.0都用这个消费者
 * @author The_kid
 *
 */
public class KafkaCustomer {
	public static final String topic="test"; 
	private static final Integer threads=1; 

	public static void main(String[] args) {
		Properties props=new Properties();
		props.put("zookeeper.connect", "192.168.2.14:2181,192.168.2.14:2182,192.168.2.14:2183");
		props.put("group.id", "test-consumer-group");
		props.put("auto.offset.reset", "smallest");
		props.put("zookeeper.session.timeout.ms", "15000");
		
		ConsumerConfig config=new ConsumerConfig(props);
		ConsumerConnector consumer=Consumer.createJavaConsumerConnector(config);
		Map<String, Integer> topicCountMap=new HashMap<>();
		topicCountMap.put(topic, threads);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams=consumerMap.get(topic);
		for (final KafkaStream<byte[], byte[]> kafkaStream : streams) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (MessageAndMetadata<byte[], byte[]> messageAndMetadata : kafkaStream) {
//						String key=new String(messageAndMetadata.key()==null?"null".getBytes():messageAndMetadata.key());
						String msg="";
						if(messageAndMetadata==null)
							msg="\n\t";
						else
							msg=new String(messageAndMetadata.message());
						System.out.println("key"+":"+msg);
					}
				}
			}).start();
		}
	
	}
	
}
