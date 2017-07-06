package com.wt.demo.mediator;

public class CountryA extends Country {

	public CountryA(String name, Mediator mediator) {
		super(name, mediator);
	}

	public String declear(String msg, Country countryTo) {
		return super.getMediator().declear(msg, this, countryTo);
	}

	@Override
	public String msg(Country contryFrom, String msg) {
		return "A获得" + contryFrom.getName() + "方消息:" + msg;
	}

}
