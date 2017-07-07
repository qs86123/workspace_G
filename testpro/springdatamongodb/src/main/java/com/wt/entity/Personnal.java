package com.wt.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="personnal")
public class Personnal {

	// mongo的加注解的id不同于es的id，es的加注解的id属于外层_id,与index同级，但是在mongo中这个id即是_id，也是person的属性id
	// @Id
	private String id;

	private String name;

	private String age;

//	public Personnal(){
//		
//	}
	
	public Personnal(String id, String name, String age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Personnal(String name, String age) {
		super();
		this.name = name;
		this.age = age;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
