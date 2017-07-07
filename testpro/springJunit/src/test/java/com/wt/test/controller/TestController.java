package com.wt.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSONObject;
import com.wt.test.webconf.WebRunner;

public class TestController extends WebRunner {

	@Before
	public void beforeMethod() {
		System.out.println("testController @Before beforeMethod()");
	}

	@Test
	public void testOne() throws Exception{
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.get("/user/user")
							.accept(MediaType.APPLICATION_JSON).param("foo", "bar"))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			int status = result.getResponse().getStatus();
			Assert.assertTrue(status == 200 ? true : false);
	}

	@Test
	public void testOne2() throws Exception{
			MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.get("/aaa");
			builder.accept(MediaType.APPLICATION_JSON);
			builder.param("foo", "bar");
			ResultActions rActions = mockMvc.perform(builder);
			rActions.andDo(MockMvcResultHandlers.print());
			MvcResult result = rActions.andReturn();
			int status = result.getResponse().getStatus();
			Assert.assertTrue(status == 200 ? true : false);
	}
	
	/**
	 * getParams?a=...&c=...
	 * 
	 * @throws Exception
	 */
	@Test
	public void getParams() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "b");
		map.put("c", "d");
		String content = getResponseContent("/getParams", map);
		System.out.println("content:" + content);
	}
	
	@Test
	public void upload(){
		try {
			String content=postUpload("/upload");
			System.out.println("resultContentStr:"+content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void upload2() throws Exception{
		String content=upload2("/upload2");
		System.out.println("result:"+content);
	}
	
	@Test
	public void upload3() throws Exception{
		String content=upload3("/upload3");
		System.out.println("result:"+content);
	}
	
	/////////////////////////////////////////////////////////

	/**
	 * postParams?a=...&c=...
	 * 
	 * @throws Exception
	 */
	@Test
	public void postParams() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "bv");
		map.put("c", "dv");
		String content = postResponseContent("/postParams", map);
		System.out.println("content:" + content);
	}

	/**
	 * postJson
	 * 
	 * @throws Exception
	 */
	@Test
	public void postJson() throws Exception {
		JSONObject json=new JSONObject();
		json.put("name", "wt");
		json.put("sex", "男");
		json.put("age", 23);
		String content = postResponseContent("/postJson", json.toJSONString());
		System.out.println("content:" + content);
	}
	
	@Test
	public void getForwardedUrl() throws Exception{
		String content=getForwardedUrl("/getForward");
		System.out.println("view:"+content);
	}
	
	@Test
	public void getForwardedUrl2() throws Exception{
		String content=getForwardedUrl("/getForward2");
		System.out.println("view:"+content);
	}
	
	@Test
	public void getForwardedUrl3() throws Exception{
		String content=getForwardedUrl("/getForward3");
		System.out.println("view:"+content);
	}
	
	@Test
	public void getRedirectedUrl() throws Exception{
		String content=getRedirectedUrl("/getRedirect");
		System.out.println("view:"+content);
	}
	
	@Test
	public void getRedirectedUrl2() throws Exception{
		String content=getRedirectedUrl("/upload");
		System.out.println("view:"+content);
	}
	
	@Test
	public void postToInputStream() throws Exception{
		String content=postToInputStream("/postToInputStream","就是adsada");
		System.out.println(content);
	}
	
	
}
