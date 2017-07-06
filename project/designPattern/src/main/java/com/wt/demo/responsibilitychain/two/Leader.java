package com.wt.demo.responsibilitychain.two;

public class Leader extends Handler {

	public Leader() {
		// 初始化的时候指定下一个处理者
		super(new ProjectManager());
	}

	public String handleResult(Request request) {
		if (request.getRequestType().equals("leader"))
			return "leader handler";
		else
			return super.getNextHandler().handleResult(request);
	}

}
