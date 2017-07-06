package com.changhong.yywl.data.analyse.common.controller.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.changhong.yywl.data.analyse.common.util.JsonCheckUtils;

public class ConversionRadesControllerTest extends WebRunner {

	private static final String REQUEST_JSON = "{\"startTime\":\"2016-01-01\",\"endTime\":\"2016-01-01\",\"category\":\"daily\",\"goodsId\":\"3333333333\",\"userType\":\"1\"}";
	private static final String GOODSLIST_NORMAL_JSON_STRING = "{\"code\":200,\"msg\":\"true\",\"data\":[{\"goodsId\":\"1111111111\",\"goodsName\":\"93#汽油\"},{\"goodsId\":\"2222222222\",\"goodsName\":\"90#汽油\"}]}";
	private static final String BUSSINESS_NORMAL_JSON_STRING = "{\"code\":200,\"msg\":null,\"data\":{\"timeList\":[\"2016-01-01\"],\"itemData\":[{\"itemName\":\"1\",\"itemCount\":[25.33]},{\"itemName\":\"2\",\"itemCount\":[13.72]},{\"itemName\":\"3\",\"itemCount\":[23.28]},{\"itemName\":\"0\",\"itemCount\":[131.00]},{\"itemName\":\"all\",\"itemCount\":[193.33]}]}}";
	private static final String USERCR_NOMAL_JSON_STRING = "{\"code\":0,\"msg\":\"true\",\"data\":null,\"data1\":[{\"name\":\"购买人数\",\"value\":81},{\"name\":\"未购买人数\",\"value\":311}],\"data2\":[{\"name\":\"0端购买人数\",\"value\":24},{\"name\":\"0端未购买人数\",\"value\":101}],\"data3\":[{\"name\":\"1端购买人数\",\"value\":24},{\"name\":\"1端未购买人数\",\"value\":101}],\"data4\":[{\"name\":\"2端购买人数\",\"value\":24},{\"name\":\"0端未购买人数\",\"value\":101}],\"legend\":[\"购买人数\",\"未购买人数\",\"0端购买人数\",\"0端未购买人数\",\"1端购买人数\",\"1端未购买人数\",\"2端购买人数\"]}";

	/**
	 * 获得商品列表测试，get
	 * 
	 * @throws Exception
	 */
	@Test
	public void goodsListTest() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.get("/conversions/goodsList");
		ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
		String res = resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(res);
		boolean b = JsonCheckUtils.checkJsonFormat(GOODSLIST_NORMAL_JSON_STRING, res);
		Assert.assertTrue(b);
	}

	/**
	 * 交易转化率测试，post
	 * 
	 * @throws Exception
	 */
	@Test
	public void bussinessCRTest() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/conversions/bussinessCR");
		mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
		mockHttpServletRequestBuilder.content(REQUEST_JSON);
		ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
		String res = resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(res);
		boolean b = JsonCheckUtils.checkJsonFormat(BUSSINESS_NORMAL_JSON_STRING, res);
		Assert.assertTrue(b);
	}

	/**
	 * 用户转化率测试，get
	 * 
	 * @throws Exception
	 */
	@Test
	public void userCRTest() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.get("/conversions/userCR");
		ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
		String res = resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(res);
		boolean b = JsonCheckUtils.checkJsonFormat(USERCR_NOMAL_JSON_STRING, res);
		Assert.assertTrue(b);
	}
}
