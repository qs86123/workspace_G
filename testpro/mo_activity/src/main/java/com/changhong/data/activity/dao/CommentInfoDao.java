package com.changhong.data.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.activity.entity.CommentInfo;

public interface CommentInfoDao extends BaseJpaDao<CommentInfo> {

	public List<CommentInfo> findByActivityId(String activityId);

	@Transactional
	@Modifying
	@Query("delete from CommentInfo where activityId = ?1")
	public void deleteByActivityId(String activityId);

}
