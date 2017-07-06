package com.wt.demo.bridge;

public class Lady extends AbsPerson {

	public Lady() {
		this.type = "女人";
	}

	@Override
	public String drive(AbsCar car, AbsRoad road) {
		return this.type + " 开着 " + car.runOnRoad(road);
	}

}
