package com.wt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * 启动类
 * @author wangtao
 * 2016年10月19日
 */
@SpringBootApplication
@PropertySource({"classpath:pro.properties"})
public class ExampleApplication {

	public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
