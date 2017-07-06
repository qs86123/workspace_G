package com.wt.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @SpringBootApplication 位于根包，启动扫描所有
 * @author The_kid
 *
 */
@SpringBootApplication
//@ServletComponentScan
public class Example {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Example.class, args);
	}

}