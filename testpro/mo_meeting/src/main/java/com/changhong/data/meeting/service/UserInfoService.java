package com.changhong.data.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.meeting.dao.UserInfoDao;
import com.changhong.data.meeting.entity.UserInfo;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoDao uiDao;

	public String save(UserInfo entity) {
		UserInfo ui = uiDao.save(entity);
		return ui.getId();
	}

	public List<UserInfo> findByCardNumIn(List<String> userIds) {
		List<UserInfo> uis=uiDao.findByCardNumIn(userIds);
		return uis;
	}

	public UserInfo findByCardNum(String userId) {
		UserInfo ui=uiDao.findByCardNum(userId);
		return ui;
	}
	
	
	
}
