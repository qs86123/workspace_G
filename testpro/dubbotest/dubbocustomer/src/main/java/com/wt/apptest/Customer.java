package com.wt.apptest;

import java.io.IOException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wt.entity.User;
import com.wt.service.TestService;

public class Customer {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicatoinContext.xml" });
		context.start();

		TestService demoService = (TestService) context.getBean("testService"); //
		String hello = demoService.sayHello("tom"); // ִ
		System.out.println(hello); //

		//
		List<User> list = demoService.getUsers();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
		// System.out.println(demoService.hehe());
		System.in.read();
	}
}
