package com.wt.pojo;

import java.io.Serializable;

public class Address implements Serializable {
	private String city;
	private String pro;

	public Address() {

	}

	public Address(String city, String pro) {
		super();
		this.city = city;
		this.pro = pro;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPro() {
		return pro;
	}

	public void setPro(String pro) {
		this.pro = pro;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", pro=" + pro + "]";
	}

}
