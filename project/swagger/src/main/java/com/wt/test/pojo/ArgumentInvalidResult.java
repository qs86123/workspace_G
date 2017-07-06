package com.wt.test.pojo;

public class ArgumentInvalidResult {
	
	private String field;
	private Object rejectedValue;
	private String defaultMessage;

	public String getField() {
		return field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

}
