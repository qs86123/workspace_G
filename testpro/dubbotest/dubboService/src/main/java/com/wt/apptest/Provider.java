package com.wt.apptest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicatoinContext.xml" });
		context.start();
		String[] ars = {};
		com.alibaba.dubbo.container.Main.main(ars);
//		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
	}
}
