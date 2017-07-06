package com.wt.demo.builder;

public class Test {
	public static void main(String[] args) {
		PersonBuilder pb=new ManBuilder();
		PersonDirector pd=new PersonDirector();
		Person person = pd.constructPerson(pb);
		System.out.println(person);
	}
}
