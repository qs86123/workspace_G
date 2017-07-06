package com.changhong.data.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.activity.entity.JoinActivityInfo;

public interface JoinActivityDao extends BaseJpaDao<JoinActivityInfo> {

	@Transactional
	@Modifying
	@Query("delete from JoinActivityInfo where activityId = ?1 and cardNum= ?2")
	void deleteByActivityIdAndCardNum(String activityId, String cardNum);

	@Transactional
	@Modifying
	@Query("delete from JoinActivityInfo where activityId = ?1")
	void deleteByActivityId(String activityId);

	List<JoinActivityInfo> findByActivityId(String activityId);
	
	JoinActivityInfo findByActivityIdAndCardNum(String activityId,String cardNum);

}
