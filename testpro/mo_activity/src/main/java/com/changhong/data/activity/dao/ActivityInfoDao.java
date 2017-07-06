package com.changhong.data.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.activity.entity.ActivityInfo;

public interface ActivityInfoDao extends BaseJpaDao<ActivityInfo> {

	public ActivityInfo findById(String id);

	@Transactional
	@Modifying
	@Query("delete from ActivityInfo where id=?1")
	public void deleteById(String id);

	public List<ActivityInfo> findByNameContaining(String name);

	public List<ActivityInfo> findByIdIn(List<String> ids);

	public List<ActivityInfo> findByStatus(Integer status);

	@Query("from ActivityInfo where lng between ?1 and ?2 and lat between ?3 and ?4")
	public List<ActivityInfo> findByLngAndLat(double minLng, double maxlng, double minlat, double maxlat);
	
}
