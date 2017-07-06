package com.changhong.data.jxw.db.entity;

/**
 * 
 * 实体类，与数据库的data_table对应，保存用户数据库同步到平台的表信息，包括目标表表名等信息
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:44:07
 */
public class LoadTable {

	private String id;
	private String ldpid;
	// 用户数源的表名
	private String tableSource;
	// 表描述
	private String tableName;
	// 目标表表名
	private String targetTable;
	// 时间增量字段名字
	private String incrementField;

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

	public String getIncrementField() {
		return incrementField;
	}

	public void setIncrementField(String incrementField) {
		this.incrementField = incrementField;
	}

}
