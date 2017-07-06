package com.wt.service;

import java.util.List;

import com.wt.entity.User;

public interface TestService {
	
	String sayHello(String name);

	public List<User> getUsers();
	
}
