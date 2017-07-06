package com.changhong.data.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.activity.dao.JoinActivityDao;
import com.changhong.data.activity.entity.JoinActivityInfo;

@Service
public class JoinActivityService {

	@Autowired
	private JoinActivityDao jaDao;

	public String save(JoinActivityInfo entity) {
		JoinActivityInfo jai = jaDao.save(entity);
		return jai.getId();
	}

	public void deleteByActivityIdAndCardNum(String activityId, String cardNum) {
		jaDao.deleteByActivityIdAndCardNum(activityId, cardNum);
	}

	public void deleteByActivityId(String activityId) {
		jaDao.deleteByActivityId(activityId);
	}

	public List<JoinActivityInfo> findByActivityId(String activityId) {
		List<JoinActivityInfo> jais = jaDao.findByActivityId(activityId);
		return jais;
	}

	public JoinActivityInfo findByActivityIdAndCardNum(String activityId, String openId) {
		JoinActivityInfo jai = jaDao.findByActivityIdAndCardNum(activityId, openId);
		return jai;
	}
	
}
