package com.changhong.data.jxw.db.entity.req;

import java.util.List;

/**
 * 
 * 用户打包数据的时候用来保存表信息，在DbAndTableInfo中有使用
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:35:58
 */
public class TableInfoReq {
	// 表名
	private String tableSource;
	// 表中文描述信息
	private String tableName;
	// 目标表的名字
	private String targetTable;
	// 表字段信息
	private List<ColumnInfoReq> columns;

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

	public List<ColumnInfoReq> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnInfoReq> columns) {
		this.columns = columns;
	}

}
