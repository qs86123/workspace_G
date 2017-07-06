package com.changhong.data.meeting.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.meeting.entity.UserQRSign;

public interface UserQRSignDao extends BaseJpaDao<UserQRSign> {

	UserQRSign findByMeetingIdAndOpenid(String meetingId, String openid);

	List<UserQRSign> findByMeetingId(String MeetingId);

	@Transactional
	@Modifying
	@Query("delete from UserQRSign where meetingId = ?1")
	void deleteByMeetingId(String meetingId);
}
