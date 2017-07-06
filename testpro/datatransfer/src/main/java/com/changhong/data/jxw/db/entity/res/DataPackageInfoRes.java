package com.changhong.data.jxw.db.entity.res;

import java.util.List;

public class DataPackageInfoRes {
	private String id;
	// 数据源id
	private String uid;
	private String title;
	List<DataPackageTableRes> tableList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DataPackageTableRes> getTableList() {
		return tableList;
	}

	public void setTableList(List<DataPackageTableRes> tableList) {
		this.tableList = tableList;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
