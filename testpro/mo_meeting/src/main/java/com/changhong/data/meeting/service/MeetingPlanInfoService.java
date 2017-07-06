package com.changhong.data.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.meeting.dao.MeetingPlanInfoDao;
import com.changhong.data.meeting.entity.MeetingPlanInfo;

@Service
public class MeetingPlanInfoService {

	@Autowired
	private MeetingPlanInfoDao mpiDao;

	public String save(MeetingPlanInfo entity) {
		MeetingPlanInfo mpi = mpiDao.save(entity);
		return mpi.getId();
	}

	public MeetingPlanInfo findById(String id) {
		MeetingPlanInfo mpi = mpiDao.findById(id);
		return mpi;
	}

	public List<MeetingPlanInfo> findAll() {
		List<MeetingPlanInfo> mpis = mpiDao.findAll();
		return mpis;
	}

	public void deleteById(String id) {
		mpiDao.deleteById(id);
	}

	public List<MeetingPlanInfo> findByRoomId(String roomId) {
		List<MeetingPlanInfo> mpis = mpiDao.findByRoomId(roomId);
		return mpis;
	}

	public void deleteByRoomId(String roomId) {
		mpiDao.deleteByRoomId(roomId);
	}
}
