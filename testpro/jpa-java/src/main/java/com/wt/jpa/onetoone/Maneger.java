package com.wt.jpa.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "jpa_maneger")
@Entity
public class Maneger {

	@GeneratedValue
	@Id
	private Integer id;
	@Column(name = "mgr_name")
	private String mgrName;

	//设置mappedBy属性，由对方的mgr属性来维护，自己不维护外键
	@OneToOne(mappedBy = "mgr")
	private Department dpt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	public Department getDpt() {
		return dpt;
	}

	public void setDpt(Department dpt) {
		this.dpt = dpt;
	}

}
