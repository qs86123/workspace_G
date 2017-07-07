package com.wt.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

//对于自定义的需要实现的Repository使用这个类实体类，不需要用注解@Id

@Document(indexName = "twitter", type = "tweet")
public class CustomerPerson {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String user;
	private String sex;
	private String age;

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

	@Override
	public String toString() {
		return "Person [user=" + user + ", sex=" + sex + ", age=" + age + "]";
	}

}
