package com.changhong.data.jxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//开启定时任务功能
@EnableScheduling
// springboot启动类，取消mongo的自动配置
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class AppStart {
	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
	}
}
