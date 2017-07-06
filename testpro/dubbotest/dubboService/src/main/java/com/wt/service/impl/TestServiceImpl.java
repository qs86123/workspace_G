package com.wt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wt.entity.User;
import com.wt.service.TestService;

public class TestServiceImpl implements TestService {

	public String sayHello(String name) {

		return "hello" + name;
	}

	public List<User> getUsers() {
		List<User> list = new ArrayList<User>();
		User u1 = new User();
		u1.setName("jack");
		u1.setAge(20);
		u1.setSex("男");

		User u2 = new User();
		u2.setName("tom");
		u2.setAge(21);
		u2.setSex("女");

		User u3 = new User();
		u3.setName("rose");
		u3.setAge(19);
		u3.setSex("女");

		list.add(u1);
		list.add(u2);
		list.add(u3);
		return list;
	}

}
