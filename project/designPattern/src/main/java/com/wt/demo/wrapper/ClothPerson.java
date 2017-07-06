package com.wt.demo.wrapper;

public class ClothPerson implements Person {

	Person person;

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public void show() {
		person.show();
	}

}
