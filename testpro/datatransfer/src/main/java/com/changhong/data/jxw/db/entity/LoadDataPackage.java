package com.changhong.data.jxw.db.entity;

import java.util.Date;

/**
 * 
 * 实体类，与数据库的load_data_package对应，保存的是用户数据库同步到平台数据库中的表信息,与之关联的还有一个load_table表
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:44:07
 */
public class LoadDataPackage {

	private String id;
	private String dsid;
	private String name;
	private Date addTime;
	private String description;
	private int isDel = 0;

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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

}
