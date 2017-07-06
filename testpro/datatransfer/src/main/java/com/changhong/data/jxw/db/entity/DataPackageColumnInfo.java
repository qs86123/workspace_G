package com.changhong.data.jxw.db.entity;

/**
 * 
 * 实体类，与数据库的data_package_column对应
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:44:07
 */
public class DataPackageColumnInfo {

	private String id;
	// data_package_table的id
	private String dptid;
	private String columnSource;
	// 列名
	private String columnName;
	// 列类型
	private String columnType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDptid() {
		return dptid;
	}

	public void setDptid(String dptid) {
		this.dptid = dptid;
	}

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
