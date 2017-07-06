package com.changhong.data.jxw.db.entity.req;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来保存数据源和对应需要打包的表的信息， Description: Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:28:55
 */
public class DbAndTableInfo {
	private String datasourceId;
	// 保存当前数据库下需要打包的表的信息
	private List<TableInfoReq> tableInfo = new ArrayList<>();

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public List<TableInfoReq> getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(List<TableInfoReq> tableInfo) {
		this.tableInfo = tableInfo;
	}

}
