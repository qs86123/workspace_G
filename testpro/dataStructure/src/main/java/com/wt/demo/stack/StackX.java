package com.wt.demo.stack;

/**
 * 栈
 * 
 * @author wangtao
 * @date 2017年4月1日上午8:31:37
 */
public class StackX {

	private int maxSize;
	private char[] stackArray;
	private int top;

	public StackX(int s) {
		maxSize = s;
		stackArray = new char[maxSize];
		top = -1;
	}

	public void push(char c) {
		stackArray[++top] = c;
	}

	public char pop() {
		return stackArray[top--];
	}

	public char peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int size() {
		return top + 1;
	}

	public char peekN(int n) {
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
