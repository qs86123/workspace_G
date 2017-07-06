package com.changhong.data.jxw.db.entity.req;

/**
 * 
 * 用户数选择表与平台数据库同步的时候，存放表信息的实体，在LoadDataPackageReq中有使用
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:33:15
 */
public class LoadTableReq {

	// 表的名字
	private String tableSource;
	// 标的描述信息
	private String tableName;
	// 时间增量字段，需要用户手动填写
	private String incrementField;

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

	public String getIncrementField() {
		return incrementField;
	}

	public void setIncrementField(String incrementField) {
		this.incrementField = incrementField;
	}

}
