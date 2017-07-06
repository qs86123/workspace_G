package com.wt.demo.memo;

public class GamePlayerMemoManager<T> {
	private MemoBox<T> memoBox;

	public MemoBox<T> getMemoBox() {
		return memoBox;
	}

	public void setMemoBox(MemoBox<T> memoBox) {
		this.memoBox = memoBox;
	}

}
