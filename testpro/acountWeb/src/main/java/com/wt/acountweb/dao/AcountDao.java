package com.wt.acountweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wt.acountweb.entity.AcountInfo;

public interface AcountDao extends BaseJpaDao<AcountInfo> {

	@Transactional
	@Modifying
	@Query("delete from AcountInfo where status = ?1")
	void deleteByStatus(String status);

	List<AcountInfo> findByStatus(String status);

}
