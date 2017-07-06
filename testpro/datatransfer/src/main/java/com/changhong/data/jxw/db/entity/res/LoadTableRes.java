package com.changhong.data.jxw.db.entity.res;

import java.util.ArrayList;
import java.util.List;

import com.changhong.data.jxw.db.entity.TableFieldInfo;

public class LoadTableRes {
	private String id;
	private String ldpid;
	// 用户数源的表名
	private String tableSource;
	// 表描述
	private String tableName;
	// 目标表表名
	private String targetTable;

	private List<TableFieldInfo> tableFieldInfos = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLdpid() {
		return ldpid;
	}

	public void setLdpid(String ldpid) {
		this.ldpid = ldpid;
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

	public List<TableFieldInfo> getTableFieldInfos() {
		return tableFieldInfos;
	}

	public void setTableFieldInfos(List<TableFieldInfo> tableFieldInfos) {
		this.tableFieldInfos = tableFieldInfos;
	}

}
