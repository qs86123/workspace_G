package com.wt.devtools.entity;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Person {

	private String name;
	private String age;

	public String getName() {
		return name;
	}

	@JsonSetter("hh")
	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
