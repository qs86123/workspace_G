package com.changhong.data.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.meeting.dao.UserQRSignDao;
import com.changhong.data.meeting.entity.UserQRSign;

@Service
public class UserQRSignService {

	@Autowired
	private UserQRSignDao uqsDao;

	public String save(UserQRSign entity) {
		UserQRSign uqs = uqsDao.save(entity);
		return uqs.getId();
	}

	public UserQRSign findByMeetingIdAndOpenid(String meetingId, String openid) {
		UserQRSign u = uqsDao.findByMeetingIdAndOpenid(meetingId, openid);
		return u;
	}

	public List<UserQRSign> findByMeetingId(String meetingId) {
		List<UserQRSign> uqs = uqsDao.findByMeetingId(meetingId);
		return uqs;
	}

	public void deleteByMeetingId(String meetingId) {
		uqsDao.deleteByMeetingId(meetingId);
	}
}
