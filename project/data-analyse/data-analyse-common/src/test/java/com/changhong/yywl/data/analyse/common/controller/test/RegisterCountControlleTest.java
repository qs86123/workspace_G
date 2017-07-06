package com.changhong.yywl.data.analyse.common.controller.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.changhong.yywl.data.analyse.common.util.JsonCheckUtils;

public class RegisterCountControlleTest extends WebRunner {

	private static final String REQUEST_JSON = "{\"startTime\":\"2016-01-01\",\"endTime\":\"2016-01-01\",\"category\":\"daily\"}";
	private static final String NORMAL_JSON_STRING = "{\"code\":200,\"msg\":null,\"data\":{\"timeList\":[\"2016-01-01\"],\"itemData\":[{\"itemName\":\"1\",\"itemCount\":[25.33]},{\"itemName\":\"2\",\"itemCount\":[13.72]},{\"itemName\":\"3\",\"itemCount\":[23.28]},{\"itemName\":\"0\",\"itemCount\":[131.00]},{\"itemName\":\"all\",\"itemCount\":[193.33]}]}}";
	private static final String ALLUSERCOUNT_NORMAL_JSON_STRING = "{\"code\":200,\"msg\":null,\"data\":[{\"userType\":\"0\",\"userCount\":473},{\"userType\":\"all\",\"userCount\":1808}]}";

	/**
	 * 用户注册量测试，post
	 * 
	 * @throws Exception
	 */
	@Test
	public void registerCountTest() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/registerCount");
		mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
		mockHttpServletRequestBuilder.content(REQUEST_JSON);
		ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
		String res = resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(res);
		boolean b = JsonCheckUtils.checkJsonFormat(NORMAL_JSON_STRING, res);
		Assert.assertTrue(b);
	}

	/**
	 * 获得总用户数测试，get
	 * 
	 * @throws Exception
	 */
	@Test
	public void allUserCountTest() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/allUserCount");
		ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
		String res = resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(res);
		boolean b = JsonCheckUtils.checkJsonFormat(ALLUSERCOUNT_NORMAL_JSON_STRING, res);
		Assert.assertTrue(b);
	}

}
