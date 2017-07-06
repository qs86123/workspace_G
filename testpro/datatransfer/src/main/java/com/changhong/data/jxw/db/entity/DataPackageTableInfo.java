package com.changhong.data.jxw.db.entity;

/**
 * 
 * 实体类，与数据库的data_package_table对应
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:44:07
 */
public class DataPackageTableInfo {
	private String id;
	private String dpid;
	private String tableSource;
	// 源表
	private String tableName;
	// 目标表
	private String targetTable;
	// 示例数据表
	private String sumaryTable;

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

}
