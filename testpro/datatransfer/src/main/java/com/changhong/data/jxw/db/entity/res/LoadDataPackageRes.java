package com.changhong.data.jxw.db.entity.res;

import java.util.ArrayList;
import java.util.List;

public class LoadDataPackageRes {
	private String id;
	private String dsid;
	private String name;
	private String addTime;
	private String description;
	private List<LoadTableRes> tableList = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDsid() {
		return dsid;
	}

	public void setDsid(String dsid) {
		this.dsid = dsid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<LoadTableRes> getTableList() {
		return tableList;
	}

	public void setTableList(List<LoadTableRes> tableList) {
		this.tableList = tableList;
	}

}
