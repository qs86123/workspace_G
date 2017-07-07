package com.wt.repo;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;
/**
 * opsForValue,返回类型ValueOperations，普通k-v操作
 * opsForSet，返回类型SetOperations，set集合
 * opsForList，返回类型ListOperations，list集合
 * opsForHash，返回类型HashOperations，hashset集合
 * @author The_kid
 *
 */
@Repository
public class MyRepository {

	@Autowired
	private RedisTemplate<String, String> template;

	public void get(String id) {
		RedisSerializer<String> serializer = new StringRedisSerializer();
		template.setKeySerializer(serializer);
		template.setValueSerializer(serializer);
		String h = template.opsForValue().get(id);
		System.out.println(h);
	}

	public void save(String str) {
		RedisSerializer<String> serializer = new StringRedisSerializer();
		template.setKeySerializer(serializer);
		template.setValueSerializer(serializer);
		template.opsForValue().set("h", "hh");
	}

	public void save2(String key, String value) {
		RedisSerializer<String> serializer = new StringRedisSerializer();
		template.setKeySerializer(serializer);
		template.setValueSerializer(serializer);
		template.opsForSet().add(key, "value1", "value2", value);
		Set<String> set = template.opsForSet().members(key);
		for (String string : set) {
			System.out.println(string);
		}
	}

	public void save22(String key, String value) {
		RedisSerializer<String> serializer = new StringRedisSerializer();
		template.setKeySerializer(serializer);
		template.setValueSerializer(serializer);
		// 求差集
		Set<String> set = template.opsForSet().difference("setkey", "setkey2");
		for (String string : set) {
			System.out.println(string);
		}
	}

	public void saveWithLiveTime() {
		RedisSerializer<String> serializer = new StringRedisSerializer();
		template.setKeySerializer(serializer);
		template.setValueSerializer(serializer);
		template.opsForValue().set("timekey", "timevalue", 60, TimeUnit.SECONDS);
	}

}
