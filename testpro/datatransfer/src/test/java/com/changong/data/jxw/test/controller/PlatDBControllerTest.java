//package com.changong.data.jxw.test.controller;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.test.web.servlet.MvcResult;
//
//import com.changong.data.jxw.test.common.WebRunner;
//
//public class PlatDBControllerTest extends WebRunner {
//
//	@Before
//	public void before() {
//		System.out.println("setup~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//	}
//
//	private String baseUrl = "/plat/";
//
//	@Test
//	public void getTables() throws Exception {
//		String url = baseUrl + "/getTables";
//		MvcResult result = post(url, null, null, null);
//		Assert.assertTrue(this.status(result.getResponse().getStatus()));
//	}
//
//	@Test
//	public void getAllTableInfo() throws Exception {
//		String url = baseUrl + "/getAllTableInfo";
//		MvcResult result = post(url, null, null, null);
//		Assert.assertTrue(this.status(result.getResponse().getStatus()));
//	}
//
//}
