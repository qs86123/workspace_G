package com.changhong.yywl.data.analyse.common.controller.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.changhong.yywl.data.analyse.common.util.JsonCheckUtils;

/**
 * Created by Administrator on 16-9-8.
 */
public class AverageSpendControllerTest extends WebRunner {
	private static final String REQUEST_JSON = "{\"startTime\":\"2016-01-01\",\"endTime\":\"2016-01-01\",\"category\":\"daily\"}";
	private static final String NORMAL_JSON_STRING = "{\"code\":200,\"msg\":null,\"data\":{\"timeList\":[\"2016-01-01\"],\"itemData\":[{\"itemName\":\"1\",\"itemCount\":[25.33]},{\"itemName\":\"2\",\"itemCount\":[13.72]},{\"itemName\":\"3\",\"itemCount\":[23.28]},{\"itemName\":\"0\",\"itemCount\":[131.00]},{\"itemName\":\"all\",\"itemCount\":[193.33]}]}}";

	/**
	 * 平均消费金额测试，post
	 * @throws Exception
	 */
	@Test
	public void averageSpendTest() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/averageSpend");
		mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
		mockHttpServletRequestBuilder.content(REQUEST_JSON);
		ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
		String res = resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(res);
		boolean b = JsonCheckUtils.checkJsonFormat(NORMAL_JSON_STRING, res);
		Assert.assertTrue(b);
	}

}
