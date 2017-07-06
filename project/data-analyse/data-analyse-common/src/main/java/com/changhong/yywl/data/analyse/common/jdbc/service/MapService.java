package com.changhong.yywl.data.analyse.common.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.yywl.data.analyse.common.jdbc.entity.MapEntity;
import com.changhong.yywl.data.analyse.common.jdbc.mapper.MapMapper;

@Service
public class MapService {

	@Autowired
	private MapMapper mapMapper;

	public List<MapEntity> findDataofProv() {
		return mapMapper.selectDataofProv();
	}

	public List<MapEntity> findDataofCity() {
		return mapMapper.selectDataofCity();
	}

}
