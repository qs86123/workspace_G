package com.wt.demo.bridge;

public class BigCar extends AbsCar {

	@Override
	public String runOnRoad(AbsRoad road) {
		return "bigcar 在" + road.roadType() + "上行驶";
	}

}
