package com.changhong.pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.pro.entity.User;
import com.changhong.pro.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper um;
	
	public User findUserByName(String name){
		return um.getUserByName();
	}
	
	public void delete(){
		um.deleteOne();
		int i=1/0;
		um.deleteTwo();
	}
}
