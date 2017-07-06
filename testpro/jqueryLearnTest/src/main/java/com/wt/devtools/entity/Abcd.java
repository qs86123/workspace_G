package com.wt.devtools.entity;

public class Abcd<E> {

	private Person p;
	private String name;
	private E e;

	public Abcd() {
	}

	public Person getP() {
		return p;
	}

	public void setP(Person p) {
		this.p = p;
	}

	public Abcd(String name, E e) {
		this.name = name;
		this.e = e;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public E getE() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}

}
