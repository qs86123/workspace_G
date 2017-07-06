package com.wt.springboot.demo.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class MyCommendLineRunner2 implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("启动时加载数据2");
	}
}
