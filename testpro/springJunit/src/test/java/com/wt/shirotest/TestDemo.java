package com.wt.shirotest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wt.shirotest.commom.AbstractShiroTest;

/**
 * shiro测试只是模板，不提供测试，测试示例看项目党建微信父工程code下的子工程wechatCMS-wxmgm
 * 
 * @author The_kid
 *
 */
public class TestDemo extends AbstractShiroTest {

	@Before
	public void initSubject() {
		createSubject();
	}

	@After
	public void tearDownSubject() {
		clearSubject();
	}

	@Test
	public void testMethod() {
		// ........
	}

}
