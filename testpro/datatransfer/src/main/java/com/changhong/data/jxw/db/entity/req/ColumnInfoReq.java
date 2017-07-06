package com.changhong.data.jxw.db.entity.req;

/**
 * 
 * 用来保存字段信息的实体,在tableInfoReq中有使用
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:25:36
 */
public class ColumnInfoReq {

	private String columnSource;
	private String columnName;
	private String columnType;

	public String getColumnSource() {
		return columnSource;
	}

	public void setColumnSource(String columnSource) {
		this.columnSource = columnSource;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

}
