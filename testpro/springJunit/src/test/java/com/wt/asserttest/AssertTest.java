package com.wt.asserttest;

import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

	@Test
	public void test(){
		Assert.assertTrue(true);
		Assert.assertEquals("abcd/abcd", "abcd/abcd");
		Assert.assertNotNull("");
		Assert.assertNull(null);
		String a=new String("a");
		String b=new String("a");
		Assert.assertNotSame(a, b);
	}
	
}
