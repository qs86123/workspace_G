package com.wt.demo.postfix;

import com.wt.demo.stack.StackX;

/**
 * 
 * 将中缀表达式转成后缀表达式
 * 
 * @author wangtao
 * @date 2017年4月1日上午8:30:50
 */
public class InToPost {

	private StackX theStack;
	private String input;
	private String output = "";

	public InToPost(String in) {
		input = in;
		int stackSize = in.length();
		theStack = new StackX(stackSize);
	}

	public String dotrans() {
		for (int j = 0; j < input.length(); j++) {
			char ch = input.charAt(j);
			theStack.displayStack("For " + ch + " ");
			switch (ch) {
			case '+':
			case '-':
				gotOper(ch, 1);
				break;
			case '*':
			case '/':
				gotOper(ch, 2);
				break;
			case '(':
				theStack.push(ch);
				break;
			case ')':
				gotParent(ch);
				break;
			default:
				output = output + ch;
				break;
			}
		}
		while (!theStack.isEmpty()) {
			theStack.displayStack("While ");
			output = output + theStack.pop();
		}
		theStack.displayStack("End ");
		return output;
	}

	private void gotOper(char opThis, int prec) {
		while (!theStack.isEmpty()) {
			char opTop = theStack.pop();
			if (opTop == '(') {
				theStack.push(opTop);
				break;
			} else {
				int prec2;
				if (opTop == '+' || opTop == '-')
					prec2 = 1;
				else
					prec2 = 2;
				if (prec2 < prec) {
					theStack.push(opTop);
					break;
				} else
					output = output + opTop;
			}
		}
		theStack.push(opThis);
	}

	private void gotParent(char ch) {
		while (!theStack.isEmpty()) {
			char chx = theStack.pop();
			if (chx == '(')
				break;
			else
				output = output + chx;
		}
	}
}
