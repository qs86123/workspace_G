package com.wt.devtools.entity;

public class Statement {

	private String name;
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Statement [name=" + name + ", content=" + content + "]";
	}

}
