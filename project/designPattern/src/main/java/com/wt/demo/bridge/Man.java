package com.wt.demo.bridge;

public class Man extends AbsPerson {

	public Man() {
		this.type="男人";
	}

	@Override
	public String drive(AbsCar car,AbsRoad road) {
		return this.type + " 开着 " + car.runOnRoad(road);
	}

}
