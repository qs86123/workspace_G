package com.changhong.data.jxw.db.entity;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 抽取提供方数据到平台数据库时使用的实体类
 * 
 * @author wangtao
 * @date 2017年3月17日上午8:50:38
 */
public class SumaryDataEntity {

	private String tableSource;
	// 平台临时表的表名
	private String sumaryTable;
	// 数据
	private List<JSONObject> data;

	public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
	}

	public String getSumaryTable() {
		return sumaryTable;
	}

	public void setSumaryTable(String sumaryTable) {
		this.sumaryTable = sumaryTable;
	}

	public List<JSONObject> getData() {
		return data;
	}

	public void setData(List<JSONObject> data) {
		this.data = data;
	}

}
