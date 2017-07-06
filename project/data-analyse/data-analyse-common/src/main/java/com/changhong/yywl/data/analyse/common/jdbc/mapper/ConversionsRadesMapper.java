package com.changhong.yywl.data.analyse.common.jdbc.mapper;

import java.util.List;
import java.util.Map;

import com.changhong.yywl.data.analyse.common.jdbc.entity.CRResultMap;
import com.changhong.yywl.data.analyse.common.jdbc.entity.GoodsEntity;

public interface ConversionsRadesMapper {

	List<CRResultMap> selectBuyerCountCR();

	List<CRResultMap> selectTradeCR(Map<String, Object> map);

//	List<GoodsEntity> selectGoodsList();

	List<GoodsEntity> selectGoodsList(Map<String, Object> map);

}
