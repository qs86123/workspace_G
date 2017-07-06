package com.wt.demo.postfix;

import com.wt.demo.stack.ParsePostStack;

/**
 * 求后缀表达式的值
 * 
 * @author wangtao
 * @date 2017年4月1日上午8:30:35
 */
public class ParsePost {

	private ParsePostStack theStack;
	private String input;

	public ParsePost(String s) {
		input = s;
	}

	public int doParse() {
		theStack = new ParsePostStack(20);
		char ch;
		int j;
		int num1, num2, interAns;
		for (j = 0; j < input.length(); j++) {
			ch = input.charAt(j);
			theStack.displayStack("" + ch + " ");
			if (ch >= '0' && ch <= '9')
				theStack.push((int) (ch - '0'));
			else {
				num2 = theStack.pop();
				num1 = theStack.pop();
				switch (ch) {
				case '+':
					interAns = num1 + num2;
					break;
				case '-':
					interAns = num1 - num2;
					break;
				case '*':
					interAns = num1 * num2;
					break;
				case '/':
					interAns = num1 / num2;
					break;
				default:
					interAns = 0;
				}
				theStack.push(interAns);
			}
		}
		interAns = theStack.pop();
		return interAns;
	}

}
