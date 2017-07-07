package com.wt.pojo;

import java.io.Serializable;

public class Person implements Serializable {

	private String id;
	private String name;
	private String sex;
	private Address adderss;

	public Person() {

	}

	public Person(String id, String name, String sex) {
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	public Person(String id, String name, String sex, Address address) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.adderss = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Address getAdderss() {
		return adderss;
	}

	public void setAdderss(Address adderss) {
		this.adderss = adderss;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", sex=" + sex + ", adderss=" + adderss + "]";
	}

}
