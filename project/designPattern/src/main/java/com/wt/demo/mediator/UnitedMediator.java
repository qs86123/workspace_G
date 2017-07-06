package com.wt.demo.mediator;

public class UnitedMediator extends Mediator {

	@Override
	public String declear(String msg, Country countryFrom, Country countryTo) {
		return countryTo.msg(countryFrom, msg);
	}

}
