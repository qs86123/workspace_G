package com.changhong.yywl.data.analyse.common.jdbc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.yywl.data.analyse.common.jdbc.entity.CRResultMap;
import com.changhong.yywl.data.analyse.common.jdbc.entity.GoodsEntity;
import com.changhong.yywl.data.analyse.common.jdbc.mapper.ConversionsRadesMapper;

@Service
public class ConversionsRadesService {

	@Autowired
	private ConversionsRadesMapper conversionsRadesMapper;

	//获得所有用户类型分别购买数量和浏览次数等信息
	public List<CRResultMap> findBuyerCountCR() {
		return conversionsRadesMapper.selectBuyerCountCR();
	}

	
	public List<CRResultMap> findTradeCR(String category, String startTime, String endTime, String goodsId,String userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("goodsId", goodsId);
		map.put("userType", userType);
		return conversionsRadesMapper.selectTradeCR(map);
	}

//	public List<GoodsEntity> findGoodsList(){
//		return conversionsRadesMapper.selectGoodsList();
//	}
	
	public List<GoodsEntity> findGoodsList(String category,String startTime,String endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return conversionsRadesMapper.selectGoodsList(map);
	}

}
