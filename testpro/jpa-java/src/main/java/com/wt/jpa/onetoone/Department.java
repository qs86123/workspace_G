package com.wt.jpa.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "jpa_department")
@Entity
public class Department {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "dept_name")
	private String deptName;

	// 由于是一对一映射，所以需要加uniqe=true,不加好像也行
	@JoinColumn(name = "mgr_id", unique = true)
	@OneToOne(fetch=FetchType.LAZY)
	private Maneger mgr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Maneger getMgr() {
		return mgr;
	}

	public void setMgr(Maneger mgr) {
		this.mgr = mgr;
	}

}
