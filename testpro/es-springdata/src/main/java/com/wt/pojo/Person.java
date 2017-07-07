package com.wt.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "twitter", type = "tweet")
public class Person {

	@Id
	private String id;
	// 提供了set方法的时候在保存数据时id才会保存进去，这个id是_id,是与index同级的id，不是person的属性id
	private String user;
	private String sex;
	private String age;

	public Person() {

	}

	public Person(String id, String user, String sex, String age) {
		super();
		this.id = id;
		this.user = user;
		this.sex = sex;
		this.age = age;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [user=" + user + ", sex=" + sex + ", age=" + age + "]";
	}

}
