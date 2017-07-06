package com.wt.demo.stack;

/**
 * 栈,解析前缀表达式的时候用
 * 
 * @author wangtao
 * @date 2017年4月1日上午8:31:37
 */
public class ParsePostStack {

	private int maxSize;
	private int[] stackArray;
	private int top;

	public ParsePostStack(int s) {
		maxSize = s;
		stackArray = new int[maxSize];
		top = -1;
	}

	public void push(int c) {
		stackArray[++top] = c;
	}

	public int pop() {
		return stackArray[top--];
	}

	public int peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int size() {
		return top + 1;
	}

	public int peekN(int n) {
		return stackArray[n];
	}

	public void displayStack(String s) {
		System.out.println(s);
		System.out.println("Statck {bottom-->top}:");
		for (int i = 0; i < size(); i++) {
			System.out.println(peekN(i));
			System.out.println("  ");
		}
		System.out.println("**");
	}
}
