package com.wt.demo.bridge.two;

public class PhoneTwo extends IPhone {

	@Override
	public String phoneRunSoft(ISoft soft) {
		return "phone Two run soft " + soft.softName();
	}

}
