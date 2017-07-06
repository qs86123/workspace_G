package com.wt.demo.digui;

public class StackTriangleApp {

	int theNumber;
	int theAnswer = 0;
	Stack theStatck;
	int flag;

	public static void main(String[] args) {
		StackTriangleApp sta = new StackTriangleApp();
		sta.reTriangle(100);
		int i = sta.getTheAnswer();
		System.out.println(i);
	}

	public void reTriangle(int n) {
		this.theNumber = n;
		theStatck = new Stack(n);
		flag = 1;
		while (!caculate()) {
			;
		}
	}

	public int getTheAnswer() {
		return theAnswer;
	}

	private boolean caculate() {
		switch (flag) {
		case 1:
			while (theNumber != 1)
				theStatck.push(theNumber--);
			theStatck.push(theNumber);
			flag = 2;
			break;
		case 2:
			while (!theStatck.isEmpty())
				theAnswer += theStatck.pop();
			return true;
		default:
			break;
		}
		return false;
	}

}

class Stack {

	private int maxSize;
	private int[] stackArray;
	private int top;

	public Stack(int s) {
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