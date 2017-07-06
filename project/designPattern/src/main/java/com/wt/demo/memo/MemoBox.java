package com.wt.demo.memo;

public class MemoBox<T> {

	private T obj;

	public MemoBox(T t) {
		this.obj = t;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

}
