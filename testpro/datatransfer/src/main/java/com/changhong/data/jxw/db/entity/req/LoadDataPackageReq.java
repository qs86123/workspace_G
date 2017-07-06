package com.changhong.data.jxw.db.entity.req;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据用户提交的表名，创建目标表，用户选择表与平台数据库同步的时候请求的实体
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月20日下午1:59:53
 */
public class LoadDataPackageReq {

	private String datasourceId;
	private String name;
	private String description;
	// 表的集合
	private List<LoadTableReq> tables = new ArrayList<>();

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<LoadTableReq> getTables() {
		return tables;
	}

	public void setTables(List<LoadTableReq> tables) {
		this.tables = tables;
	}

}
