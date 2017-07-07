package com.wt.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.demo.entity.User;
import com.wt.demo.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper um;
	
	public User findUserByName(String name){
		return um.getUserByName();
	}
	
	public void delete(){
		um.deleteOne();
//		int i=1/0;
		um.deleteTwo();
	}
}
