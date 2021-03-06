package com.changhong.data.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.meeting.dao.UserOpenidDao;
import com.changhong.data.meeting.entity.UserOpenid;

@Service
public class UserOpenidService {

	@Autowired
	private UserOpenidDao uoDao;

	public UserOpenid findByOpenid(String openid) {
		UserOpenid uo = uoDao.findByOpenid(openid);
		return uo;
	}

	public List<UserOpenid> findByCardNums(List<String> strs) {
		return uoDao.findByCardNumIn(strs);
	}

	public List<UserOpenid> findByOpenidIn(List<String> openids) {
		List<UserOpenid> uos=uoDao.findByOpenidIn(openids);
		return uos;
	}

}
