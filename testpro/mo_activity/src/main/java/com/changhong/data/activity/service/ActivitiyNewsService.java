package com.changhong.data.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.activity.dao.ActivityNewsDao;
import com.changhong.data.activity.entity.ActivityNews;

@Service
public class ActivitiyNewsService {

	@Autowired
	private ActivityNewsDao anDao;

	public void saveAll(List<ActivityNews> entities) {
		anDao.save(entities);
	}

	public void deleteByActivityId(String activityId) {
		anDao.deleteByActivityId(activityId);
	}

	public List<ActivityNews> findByActivityId(String activityId) {
		List<ActivityNews> ans = anDao.findByActivityId(activityId);
		return ans;
	}

}
