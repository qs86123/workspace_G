package com.changhong.data.meeting.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.meeting.entity.MeetingSeatInfo;

public interface MeetingSeatInfoDao extends BaseJpaDao<MeetingSeatInfo> {

	List<MeetingSeatInfo> findByMeetingId(String meetingId);

	MeetingSeatInfo findByMeetingIdAndUserId(String meetingId, String userId);

	@Transactional
	@Modifying
	@Query("delete from MeetingSeatInfo where meetingId=?1")
	void deleteByMeetingId(String meetingId);

	MeetingSeatInfo findByMeetingIdAndReplaceId(String meetingId, String replaceId);

}
