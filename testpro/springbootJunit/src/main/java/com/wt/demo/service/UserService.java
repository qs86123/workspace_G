package com.wt.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.demo.dao.UserDao;
import com.wt.demo.entity.UserInfo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void saveAll(List<UserInfo> entities) {
		userDao.save(entities);
	}

	public UserInfo findByName(String name) {
		UserInfo ui = userDao.findByName(name);
		return ui;
	}
}
