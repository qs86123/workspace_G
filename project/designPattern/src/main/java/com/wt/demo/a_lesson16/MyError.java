package com.wt.demo.a_lesson16;

public class MyError extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public MyError() {
		super();
	}

	public MyError(String msg) {
		super(msg);
	}

	public MyError(String message, Throwable cause) {
		super(message, cause);
	}

	public MyError(Throwable cause) {
		super(cause);
	}

	protected MyError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
