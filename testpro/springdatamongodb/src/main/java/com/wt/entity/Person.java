package com.wt.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@TypeAlias("good")//指定保存时默认字段_class的值，不指定默认为包名
//@Document(collection="person")
public class Person {

	// mongo的加注解的id不同于es的id，es的加注解的id属于外层_id,与index同级，但是在mongo中这个id即是_id，也是person的属性id
	// @Id
	private String id;

	private String name;

	private String age;
	
	// 支持嵌套
//	private Personnal personnal;
//
//	public Personnal getPersonnal() {
//		return personnal;
//	}
//
//	public void setPersonnal(Personnal personnal) {
//		this.personnal = personnal;
//	}

	public Person(){
		
	}
	
	public Person(String id, String name, String age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Person(String name, String age) {
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
