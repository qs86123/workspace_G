package com.wt.demo.a_lesson16;

public class Cat implements Comparable<Cat> {

	private int age;
	private String name;

	public Cat(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cat [age=" + age + ", name=" + name + "]";
	}

	public int compareTo(Cat o) {
		return o.age - this.age;
	}

}
