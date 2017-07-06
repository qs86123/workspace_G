package com.wt.demo.responsibilitychain.two;

public class Boss extends Handler {

	public Boss() {
		// 最高层处理者，结果必须处理，无需指定下一个默认处理者，因为没有下一个默认处理者了
		// 如果boss需要将其指定给其他的处理这，用setNextHandler方法就可以指定给下一个处理者了，再重新修改handResult方法就行了
	}

	public String handleResult(Request request) {
		return "boss handle this";
	}

}
