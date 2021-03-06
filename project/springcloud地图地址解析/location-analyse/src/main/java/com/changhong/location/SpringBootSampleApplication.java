package com.changhong.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动类
 * @author wangyang@broadengate.com
 * 2016年10月19日
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringBootSampleApplication {

	public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleApplication.class, args);
    }
}
