package com.changhong.data.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.meeting.dao.MeetingRoomInfoDao;
import com.changhong.data.meeting.entity.MeetingRoomInfo;

@Service
public class MeetingRoomInfoService {

	@Autowired
	public MeetingRoomInfoDao mrDao;

	public String save(MeetingRoomInfo entity) {
		MeetingRoomInfo mr = mrDao.save(entity);
		return mr.getId();
	}

	public List<MeetingRoomInfo> get() {
		List<MeetingRoomInfo> rooms = mrDao.findAll();
		return rooms;
	}

	public MeetingRoomInfo getById(String id) {
		return mrDao.findById(id);
	}

	public void deleteById(String roomId) {
		mrDao.deleteById(roomId);
		
	}

}
