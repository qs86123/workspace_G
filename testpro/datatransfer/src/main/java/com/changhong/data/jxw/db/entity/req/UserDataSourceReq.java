package com.changhong.data.jxw.db.entity.req;

/**
 * 删除用户数据源的时候使用的实体，在datasourceController的删除接口的请求体中有使用
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:38:02
 */
public class UserDataSourceReq {

	// 用户id
	private String userId;
	// 数据源id
	private String datasourceId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

}
