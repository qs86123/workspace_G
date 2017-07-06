package com.changhong.data.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.activity.entity.ActivityNews;

public interface ActivityNewsDao extends BaseJpaDao<ActivityNews> {

	@Transactional
	@Modifying
	@Query("delete from ActivityNews where activityId = ?1")
	void deleteByActivityId(String activityId);

	List<ActivityNews> findByActivityId(String activityId);

}
