package com.wt.demo.responsibilitychain;

public class Request {

	private String requestType;
	private String content;

	public Request(String requestType, String content) {
		this.requestType = requestType;
		this.content = content;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
