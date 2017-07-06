package com.changhong.data.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.activity.dao.CommentInfoDao;
import com.changhong.data.activity.entity.CommentInfo;

@Service
public class CommentInfoService {

	@Autowired
	private CommentInfoDao ciDao;

	public String save(CommentInfo entity) {
		CommentInfo ci = ciDao.save(entity);
		return ci.getId();
	}

	public void deleteByActivityId(String activityId) {
		ciDao.deleteByActivityId(activityId);
	}

	public List<CommentInfo> findByActivityId(String activityId) {
		List<CommentInfo> cis=ciDao.findByActivityId(activityId);
		return cis;
	}

}
