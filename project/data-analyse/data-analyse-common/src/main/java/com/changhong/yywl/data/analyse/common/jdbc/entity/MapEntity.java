package com.changhong.yywl.data.analyse.common.jdbc.entity;

public class MapEntity {

	private String name;
	private Integer value;
	private String worker = "个人";
	private Integer value1;
	private String parent = "员工";
	private Integer value2;
	private String person = "员工亲友";
	private Integer value3;

	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}

	public String getWorker() {
		return worker;
	}

	public Integer getValue1() {
		return value1;
	}

	public String getParent() {
		return parent;
	}

	public Integer getValue2() {
		return value2;
	}

	public String getPerson() {
		return person;
	}

	public Integer getValue3() {
		return value3;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public void setValue1(Integer value1) {
		this.value1 = value1;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public void setValue2(Integer value2) {
		this.value2 = value2;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public void setValue3(Integer value3) {
		this.value3 = value3;
	}

}
