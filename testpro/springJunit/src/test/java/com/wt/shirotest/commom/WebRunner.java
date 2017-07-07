/**
 *
 */
package com.wt.shirotest.commom;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * @author
 * @description 用于支持WEB类型的测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:/test-baseTest.xml", "classpath*:/spring-mvc.xml","classpath*:/shiroConfigTest.xml" })
public class WebRunner extends AbstractTransactionalJUnit4SpringContextTests {

	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

//	@Autowired
//	protected MockServletContext servletContext; // cached

	@Autowired
	protected MockHttpSession session;

	@Autowired
	protected MockHttpServletRequest request;

//	@Autowired
//	protected MockHttpServletResponse response;

//	@Autowired
//	protected ServletWebRequest webRequest;

	@Before
	public void buildMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.session = new MockHttpSession();
	}

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected ObjectMapper om = new ObjectMapper();



	protected void out(Object obj) {
		try {
			String s;
			if (obj instanceof String) {
				s = (String) obj;
			} else {
				s = om.writeValueAsString(obj);
			}
			logger.info(s);
		} catch (JsonProcessingException e) {
			RuntimeException re = new RuntimeException(e);
			throw re;
		}
	}



	/**
	 * get test method
	 * @param url
	 * @return
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	public String get(String url) throws Exception {
		ResultActions ra = this.mockMvc.perform(
				MockMvcRequestBuilders.get(url).accept(MediaType.parseMediaType("application/json;charset=UTF-8")));
//		ra.andExpect(MockMvcResultMatchers.status().isOk());
		MvcResult mr = ra.andReturn();
		String result = mr.getResponse().getContentAsString();
//		String result = ra.andExpect(MockMvcResultMatchers.status().isOk());
		out(result);
		return result;
	}


	public String getForward(String url) throws Exception {
		ResultActions ra = this.mockMvc.perform(
				MockMvcRequestBuilders.get(url).accept(MediaType.parseMediaType("application/json;charset=UTF-8")));
		MvcResult mr = ra.andReturn();
		String forwardedUrl = mr.getResponse().getForwardedUrl();//跳转地址
		out(forwardedUrl);
		return forwardedUrl;
	}

	public int getStatus(String url) throws Exception {
		ResultActions ra = this.mockMvc.perform(
				MockMvcRequestBuilders.get(url).accept(MediaType.parseMediaType("application/json;charset=UTF-8")));
		MvcResult mr = ra.andReturn();
		int status = mr.getResponse().getStatus();
		out(status);
		return status;
	}

	/**
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String post(String url) throws Exception {
		ResultActions ra = this.mockMvc.perform(
				MockMvcRequestBuilders.post(url).accept(MediaType.parseMediaType("application/json;charset=UTF-8")));
//		ra.andExpect(MockMvcResultMatchers.status().isOk());
		MvcResult mr = ra.andReturn();
//		int status = mr.getResponse().getStatus();//状态码
//		String s = mr.getResponse().getForwardedUrl();//跳转地址
		String result = mr.getResponse().getContentAsString();
//		String result2 = ra.andExpect(MockMvcResultMatchers.status().isOk());
		out(result);
		return result;
	}


	public int postStatus(String url) throws Exception {
		ResultActions ra = this.mockMvc.perform(
				MockMvcRequestBuilders.post(url).accept(MediaType.parseMediaType("application/json;charset=UTF-8")));
		MvcResult mr = ra.andReturn();
		int status = mr.getResponse().getStatus();//状态码
		out(status);
		return status;
	}

	public String postForward(String url) throws Exception {
		ResultActions ra = this.mockMvc.perform(
				MockMvcRequestBuilders.post(url).accept(MediaType.parseMediaType("application/json;charset=UTF-8")));
		MvcResult mr = ra.andReturn();
		String forwardedUrl = mr.getResponse().getForwardedUrl();//跳转地址
		out(forwardedUrl);
		return forwardedUrl;
	}



	/**
	 * post test method
	 * @param url
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String post(String url, String jsonString) throws Exception {
		ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString.getBytes()));

		MvcResult mr = ra.andReturn();
		String result = mr.getResponse().getContentAsString();
		out(result);
		return result;
	}

	public String post(String url, Map<String,String> params) throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url);
		for(String key : params.keySet()){
			builder.param(key, params.get(key));
		}
		ResultActions ra = this.mockMvc.perform(builder);
//		ra.andExpect(MockMvcResultMatchers.status().isOk());
		MvcResult mr = ra.andReturn();
		String result = mr.getResponse().getContentAsString();
//		String result = mr.getResponse().getForwardedUrl();
		out(result);
		return result;
	}

	public String postForward(String url, Map<String,String> params) throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url);
		for(String key : params.keySet()){
			builder.param(key, params.get(key));
		}
		ResultActions ra = this.mockMvc.perform(builder);
		MvcResult mr = ra.andReturn();
		String result = mr.getResponse().getForwardedUrl();
		out(result);
		return result;
	}

	public int postStatus(String url, Map<String,String> params) throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url);
		for(String key : params.keySet()){
			builder.param(key, params.get(key));
		}
		ResultActions ra = this.mockMvc.perform(builder);
		MvcResult mr = ra.andReturn();
		int result = mr.getResponse().getStatus();
		out(result);
		return result;
	}

	public String postFile(MockMultipartFile file, String url) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url);
        builder.content(file.getBytes());
        builder.accept(MediaType.ALL);
//        for(String key : map.keySet()){
//            builder.param(key, map.get(key));
//        }
        ResultActions ra = this.mockMvc.perform(builder);
        MvcResult mr = ra.andReturn();
        String result = mr.getResponse().getContentAsString();
        out(result);
		return result;
	}

}
