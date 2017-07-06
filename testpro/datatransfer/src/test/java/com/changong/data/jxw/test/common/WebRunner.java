package com.changong.data.jxw.test.common;

import java.io.InputStream;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.changhong.data.jxw.AppStart;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { AppStart.class })
@WebAppConfiguration
public class WebRunner {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		System.out.println("WebRunner @before setup");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		// 初始化具体某个controller
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// UserController()).build();
	}

	public boolean status(int status) {
		if (status == 200)
			return true;
		return false;
	}

	/**
	 * 
	 * @param url
	 *            请求网址
	 * @param params
	 *            请求参数
	 * @param inputStreamData
	 *            写入输入流的数据
	 * @return
	 * @throws Exception
	 */
	public MvcResult get(String url, Map<String, String> params, String inputStreamData) throws Exception {
		return doPostOrGet(url, params, inputStreamData, null, false);
	}

	/**
	 * 
	 * @param url
	 *            请求网址
	 * @param params
	 *            请求参数
	 * @param inputStreamData
	 *            写入输入流的数据，有该值则不能再上传文件
	 * @param json
	 *            post请求专有的json数据，请求体的body
	 * @return
	 * @throws Exception
	 */
	public MvcResult post(String url, Map<String, String> params, String inputStreamData, String json)
			throws Exception {
		return doPostOrGet(url, params, inputStreamData, json, true);
	}

	/**
	 * 
	 * @param url
	 *            请求网址
	 * @param params
	 *            请求参数
	 * @param inputStreamData
	 *            写入输入流的数据，有该值则不能再上传文件
	 * @param json
	 *            post请求专有的json数据，请求体的body
	 * @param isPost
	 *            布尔值，用来判断是否是post方法
	 * @return
	 * @throws Exception
	 */
	public MvcResult doPostOrGet(String url, Map<String, String> params, String inputStreamData, String json,
			boolean isPost) throws Exception {
		MockHttpServletRequestBuilder builder = null;
		if (isPost)
			builder = MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON);
		else
			builder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
		if (inputStreamData != null) {
			builder.content(inputStreamData);
		}
		if (params != null) {
			for (String key : params.keySet())
				builder.param(key, params.get(key));
		}
		if (json != null) {
			builder.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json);
		}
		MvcResult result = mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andReturn();
		return result;
	}

	public String fileUpload(String url, String fieldName, String originalName, InputStream is,
			Map<String, String> params) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload(url)
				.file(new MockMultipartFile(fieldName, originalName, null, is));
		if (params != null) {
			for (String key : params.keySet())
				builder.param(key, params.get(key));
		}
		MvcResult result = mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andReturn();
		String msg = result.getResponse().getContentAsString();
		return msg;
	}

	public MvcResult doHttp(String url, Map<String, String> params, String inputStreamData, String json, String method)
			throws Exception {
		MockHttpServletRequestBuilder builder = this.createBuilder(method, url);
		if (params != null) {
			for (String key : params.keySet())
				builder.param(key, params.get(key));
		}
		if (json != null) {
			builder.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(json);
		}
		MvcResult result = mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andReturn();
		return result;
	}

	private MockHttpServletRequestBuilder createBuilder(String method, String url) {
		MockHttpServletRequestBuilder builder = null;
		if (method.equals("put"))
			builder = MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON);
		else if (method.equals("get"))
			builder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
		else if (method.equals("post"))
			builder = MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON);
		else if (method.equals("delete"))
			builder = MockMvcRequestBuilders.delete(url).accept(MediaType.APPLICATION_JSON);
		return builder;
	}

}
