package com.wt.demo.responsibilitychain.two;

public class ProjectManager extends Handler {

	public ProjectManager() {
		// 初始化的时候指定下一个处理者
		super(new Boss());
	}

	public String handleResult(Request request) {
		if (request.getRequestType().equals("projectManager"))
			return "project manager handled this";
		else
			return super.getNextHandler().handleResult(request);
	}

}
