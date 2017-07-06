package com.changhong.yywl.data.analyse.common.jdbc.mapper;

import java.util.List;

import com.changhong.yywl.data.analyse.common.jdbc.entity.MapEntity;

public interface MapMapper {

	List<MapEntity> selectDataofProv();

	List<MapEntity> selectDataofCity();
	
}
