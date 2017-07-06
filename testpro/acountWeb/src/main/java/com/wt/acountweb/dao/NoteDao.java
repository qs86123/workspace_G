package com.wt.acountweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wt.acountweb.entity.NoteInfo;

public interface NoteDao extends BaseJpaDao<NoteInfo> {

	@Transactional
	@Modifying
	@Query("delete from NoteInfo where status = ?1")
	void deleteByStatus(String status);

	List<NoteInfo> findByStatus(String status);

}
