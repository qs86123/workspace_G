package com.wt.demo.bridge.two;

public class PhoneOne extends IPhone {

	@Override
	public String phoneRunSoft(ISoft soft) {
		return "phone One run soft " + soft.softName();
	}

}
