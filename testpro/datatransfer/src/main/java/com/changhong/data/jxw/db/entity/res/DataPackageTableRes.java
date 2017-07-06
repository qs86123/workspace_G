package com.changhong.data.jxw.db.entity.res;

import java.util.List;

import com.changhong.data.jxw.db.entity.DataPackageColumnInfo;

public class DataPackageTableRes {
	private String id;
	private String dpid;
	private String tableSource;
	// 源表
	private String tableName;
	// 目标表
	private String targetTable;
	// 示例数据表
	private String sumaryTable;
	private List<DataPackageColumnInfo> dataPackageColumnInfoList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDpid() {
		return dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}

	public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTargetTable() {
		return targetTable;
	}

	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}

	public String getSumaryTable() {
		return sumaryTable;
	}

	public void setSumaryTable(String sumaryTable) {
		this.sumaryTable = sumaryTable;
	}

	public List<DataPackageColumnInfo> getDataPackageColumnInfoList() {
		return dataPackageColumnInfoList;
	}

	public void setDataPackageColumnInfoList(List<DataPackageColumnInfo> dataPackageColumnInfoList) {
		this.dataPackageColumnInfoList = dataPackageColumnInfoList;
	}

}
