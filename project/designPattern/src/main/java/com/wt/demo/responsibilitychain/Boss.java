package com.wt.demo.responsibilitychain;

public class Boss extends Handler {

	public String handleResult(Request request) {
		return "leader and projectManager cannot handle this , boss handle this";
	}

}
