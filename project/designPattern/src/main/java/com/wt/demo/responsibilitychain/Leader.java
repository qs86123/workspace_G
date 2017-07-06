package com.wt.demo.responsibilitychain;

public class Leader extends Handler {

	public String handleResult(Request request) {
		if (request.getRequestType().equals("leader"))
			return "leader handler";
		else
			return new ProjectManager().handleResult(request);
	}

}
