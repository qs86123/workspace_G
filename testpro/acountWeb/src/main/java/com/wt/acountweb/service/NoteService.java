package com.wt.acountweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.acountweb.dao.NoteDao;
import com.wt.acountweb.entity.NoteInfo;

@Service
public class NoteService {

	@Autowired
	private NoteDao noteDao;

	public void saveAll(List<NoteInfo> entities) {
		noteDao.save(entities);
	}
	public void deleteByStatus(String status) {
		noteDao.deleteByStatus(status);
	}
	public List<NoteInfo> findByStatus(String status) {
		List<NoteInfo> nis=noteDao.findByStatus(status);
		return nis;
	}
}
