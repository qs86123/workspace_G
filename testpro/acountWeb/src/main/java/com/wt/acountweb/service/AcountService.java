package com.wt.acountweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.acountweb.dao.AcountDao;
import com.wt.acountweb.entity.AcountInfo;

@Service
public class AcountService {

	@Autowired
	private AcountDao acountDao;

	public void saveAll(List<AcountInfo> entities) {
		acountDao.save(entities);
	}

	public void deleteByStatus(String status) {
		acountDao.deleteByStatus(status);
	}

	public List<AcountInfo> findByStatus(String status) {
		List<AcountInfo> ais = acountDao.findByStatus(status);
		return ais;
	}
}
