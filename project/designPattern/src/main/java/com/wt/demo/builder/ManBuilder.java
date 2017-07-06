package com.wt.demo.builder;

public class ManBuilder implements PersonBuilder {

	Person person = new Person();

	public void buildHead() {
		person.setHead("build Head");
	}

	public void buildBody() {
		person.setBody("build Body");
	}

	public void buildFoot() {
		person.setFoot("build Foot");

	}

	public Person buildPerson() {
		return person;
	}

}
