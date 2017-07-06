package com.wt.springboot.demo.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyCommendLineRunner implements CommandLineRunner{
	@Override
	public void run(String... args) throws Exception {
		System.out.println("启动时加载数据");
	}
}
