package com.changhong.data.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.meeting.dao.MeetingSeatInfoDao;
import com.changhong.data.meeting.entity.MeetingSeatInfo;

@Service
public class MeetingSeatInfoService {

	@Autowired
	private MeetingSeatInfoDao meetingInfoDao;

	public String save(MeetingSeatInfo entity) {
		MeetingSeatInfo mi = meetingInfoDao.save(entity);
		return mi.getId();
	}

	public List<MeetingSeatInfo> getByMeetingId(String meetingId) {
		List<MeetingSeatInfo> lists = meetingInfoDao.findByMeetingId(meetingId);
		return lists;
	}

	public List<MeetingSeatInfo> saveAll(List<MeetingSeatInfo> list) {
		List<MeetingSeatInfo> l = meetingInfoDao.save(list);
		return l;
	}

	public MeetingSeatInfo getByMeetingIdAndUserId(String meetingId, String userId) {
		MeetingSeatInfo msi = meetingInfoDao.findByMeetingIdAndUserId(meetingId, userId);
		return msi;
	}

	public MeetingSeatInfo getByMeetingIdAndReplaceId(String meetingId, String replaceId) {
		MeetingSeatInfo msi = meetingInfoDao.findByMeetingIdAndReplaceId(meetingId, replaceId);
		return msi;
	}
	
	public void deleteByMeetingId(String meetingId) {
		meetingInfoDao.deleteByMeetingId(meetingId);
	}
}
