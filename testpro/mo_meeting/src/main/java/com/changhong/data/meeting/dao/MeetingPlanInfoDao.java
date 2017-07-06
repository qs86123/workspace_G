package com.changhong.data.meeting.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.meeting.entity.MeetingPlanInfo;

public interface MeetingPlanInfoDao extends BaseJpaDao<MeetingPlanInfo> {

	public MeetingPlanInfo findById(String id);

	@Transactional
	@Modifying
	@Query("delete from MeetingPlanInfo where id = ?1")
	public void deleteById(String id);

	public List<MeetingPlanInfo> findByRoomId(String roomId);

	@Transactional
	@Modifying
	@Query("delete from MeetingPlanInfo where roomId = ?1")
	public void deleteByRoomId(String roomId);

	
}
