package com.wt.demo.responsibilitychain;

public class ProjectManager extends Handler {

	public String handleResult(Request request) {
		if (request.getRequestType().equals("projectManager"))
			return "leader cannot handle this , project manager handled this";
		else
			return new Boss().handleResult(request);
	}

}
