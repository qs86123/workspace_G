package com.wt.devtools;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @SpringBootApplication 位于根包，启动扫描所有
 * @author The_kid
 *
 */
@SpringBootApplication
public class Example {

	public static void main(String[] args) throws Exception {
//		SpringApplication app=new SpringApplication();
		SpringApplication.run(Example.class, args);
	}
}