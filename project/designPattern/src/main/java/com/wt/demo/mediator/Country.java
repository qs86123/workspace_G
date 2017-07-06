package com.wt.demo.mediator;

public abstract class Country {

	private String name;
	private Mediator mediator;

	public Country(String name, Mediator mediator) {
		this.name = name;
		this.mediator = mediator;
	}

	public abstract String msg(Country contryFrom, String msg);

	public Mediator getMediator() {
		return mediator;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
