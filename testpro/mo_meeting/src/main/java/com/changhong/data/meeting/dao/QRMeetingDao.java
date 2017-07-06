package com.changhong.data.meeting.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.meeting.entity.QRMeeting;

public interface QRMeetingDao extends BaseJpaDao<QRMeeting> {

	@Transactional
	@Modifying
	@Query("delete from QRMeeting where meetingId = ?1")
	void deleteByMeetingId(String meetingId);

	QRMeeting findByQrId(String qrId);

	QRMeeting findByMeetingId(String meetingId);

}
