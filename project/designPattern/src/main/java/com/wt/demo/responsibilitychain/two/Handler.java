package com.wt.demo.responsibilitychain.two;

public abstract class Handler {

	private Handler nextHandler;

	public abstract String handleResult(Request request);

	public Handler() {
	}

	public Handler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public Handler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

}
