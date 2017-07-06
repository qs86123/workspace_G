package com.changhong.yywl.data.analyse.common.jdbc.req;

import com.changhong.yywl.data.analyse.common.RestApiRequest;

public class RestApiReqforTrade extends RestApiRequest{

	private String goodsId;
	private String userType;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	
}
