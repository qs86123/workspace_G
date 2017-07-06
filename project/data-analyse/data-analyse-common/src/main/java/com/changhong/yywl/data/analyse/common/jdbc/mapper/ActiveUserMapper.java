package com.changhong.yywl.data.analyse.common.jdbc.mapper;

import java.util.List;
import java.util.Map;

import com.changhong.yywl.data.analyse.common.jdbc.entity.ActiveUserResultMap;

public interface ActiveUserMapper {

	List<ActiveUserResultMap> selectUserCount(Map<String, Object> map);

	List<String> selectUserType();
	
}
