package com.changhong.data.jxw.db.entity.req;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 用户数打包上传数据请求实体
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月15日下午1:31:33
 */
public class DataPackageReq {

	private String userId;
	private String title;
	//提交多个数据源的数据，用来保存每个数据源和表的集合
	private List<DbAndTableInfo> dbAndTableInfo = new ArrayList<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<DbAndTableInfo> getDbAndTableInfo() {
		return dbAndTableInfo;
	}

	public void setDbAndTableInfo(List<DbAndTableInfo> dbAndTableInfo) {
		this.dbAndTableInfo = dbAndTableInfo;
	}

}
