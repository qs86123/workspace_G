package com.changhong.yywl.data.analyse.common.controller.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.changhong.yywl.data.analyse.common.util.JsonCheckUtils;

public class PageBrowseTrendControllerTest extends WebRunner {

	private static final String REQUEST_JSON = "{\"startTime\":\"2016-01-01\",\"endTime\":\"2016-01-01\",\"category\":\"daily\",\"pageName\":\"0\"}";
	private static final String NORMAL_JSON_STRING = "{\"code\":200,\"msg\":null,\"data\":{\"timeList\":[\"2016-01-01\"],\"itemData\":[{\"itemName\":\"1\",\"itemCount\":[25.33]},{\"itemName\":\"2\",\"itemCount\":[13.72]},{\"itemName\":\"3\",\"itemCount\":[23.28]},{\"itemName\":\"0\",\"itemCount\":[131.00]},{\"itemName\":\"all\",\"itemCount\":[193.33]}]}}";
	private static final String PAGELIST_NORMAL_JOSN_STRING="{\"code\":200,\"msg\":null,\"data\":[{\"key\":\"0\",\"sectionName\":\"main\"}]}";
	/**
	 * 页面访问趋势测试，post
	 * 
	 * @throws Exception
	 */
	@Test
	public void pageBrowseTrendTest() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/pageBrowseTrend");
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
	 * 获得页面列表测试，get
	 * 
	 * @throws Exception
	 */
	@Test
	public void pageListTest() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/pageList");
		ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
		String res = resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(res);
		boolean b = JsonCheckUtils.checkJsonFormat(PAGELIST_NORMAL_JOSN_STRING, res);
		Assert.assertTrue(b);
	}
}
