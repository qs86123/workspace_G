package com.wt.demo.visitor;

import java.util.ArrayList;
import java.util.List;

public class Manager {

	List<Person> persons = new ArrayList<>();

	public void addPerson(Person person) {
		this.persons.add(person);
	}

	public void display(Action visitor) {
		for (Person person : persons) {
			System.out.println(person.accept(visitor));
		}
	}

}
