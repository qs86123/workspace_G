package com.changhong.data.meeting.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.meeting.entity.MeetingRoomInfo;

public interface MeetingRoomInfoDao extends BaseJpaDao<MeetingRoomInfo> {

	MeetingRoomInfo findById(String id);

	@Transactional
	@Modifying
	@Query("delete from MeetingRoomInfo where id = ?1")
	void deleteById(String roomId);

}
