package com.wt.demo.postfix;

import org.junit.Test;

public class APPTest {
	public static void main(String[] args) {

	}

	/**
	 * 将中缀表达式转换成后缀表达式
	 */
	@Test
	public void intoPost() {
		String input = "A+B*C/D";
		InToPost theTrans = new InToPost(input);
		String output = theTrans.dotrans();
		System.out.println("Postfix is " + output + "\n");
	}

	/**
	 * 计算后缀表达式的值
	 */
	@Test
	public void parsePost() {
		String input = "3+5*6/2+9-((4+6)/2*(2+1))";// 356*2/+9+46+2/21+*-
		// 先将中缀表达式转换成后缀表达式
		InToPost theTrans = new InToPost(input);
		String postfix = theTrans.dotrans();
		// 计算后缀表达式的值
		ParsePost parsePost = new ParsePost(postfix);
		int output = parsePost.doParse();
		System.out.println(postfix);
		System.out.println(input + "=" + output + "\n");
	}

}