package com.changhong.data.jxw.db.entity;

import java.util.List;

/**
 * 表表结构实体，返回用户表结构信息的时候有用到
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:52:21
 */
public class TableInfo {

	// 表名
	private String tableSource;
	// 表中文描述信息
	private String tableName;
	private List<TableFieldInfo> tableFieldInfos;

	public TableInfo() {

	}

	public TableInfo(String tableSource, String tableName, List<TableFieldInfo> tableFieldInfos) {
		this.tableSource = tableSource;
		this.tableName = tableName;
		this.tableFieldInfos = tableFieldInfos;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<TableFieldInfo> getTableFieldInfos() {
		return tableFieldInfos;
	}

	public void setTableFieldInfos(List<TableFieldInfo> tableFieldInfos) {
		this.tableFieldInfos = tableFieldInfos;
	}

	public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
	}

	@Override
	public String toString() {
		return "TableInfo [tableName=" + tableName + ", tableFieldInfos=" + tableFieldInfos + "]";
	}

}
