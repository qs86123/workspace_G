package com.wt.test.webconf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring/springmvc.xml", "classpath:spring/applicationContext-*.xml" })
public class WebRunnerCommonMethod {

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

	/**
	 * 
	 * @param url 请求网址
	 * @param params 请求参数
	 * @param inputStreamData 写入输入流的数据
	 * @return
	 * @throws Exception
	 */
	public MvcResult get(String url, Map<String, String> params, String inputStreamData) throws Exception {
		return doPostOrGet(url, params, inputStreamData, null, false);
	}

	/**
	 * 
	 * @param url 请求网址
	 * @param params 请求参数
	 * @param inputStreamData 写入输入流的数据，有该值则不能再上传文件
	 * @param json post请求专有的json数据，请求体的body
	 * @return
	 * @throws Exception
	 */
	public MvcResult post(String url, Map<String, String> params, String inputStreamData, String json)
			throws Exception {
		return doPostOrGet(url, params, inputStreamData, json, true);
	}
	
	/**
	 * 
	 * @param url 请求网址
	 * @param params 请求参数
	 * @param inputStreamData 写入输入流的数据，有该值则不能再上传文件
	 * @param json post请求专有的json数据，请求体的body
	 * @param isPost 布尔值，用来判断是否是post方法
	 * @return
	 * @throws Exception
	 */
	public MvcResult doPostOrGet(String url, Map<String, String> params, String inputStreamData, String json, boolean isPost)
			throws Exception {
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

	public String fileUpload(String url, String fieldName, String originalName, 
			InputStream is, Map<String, String> params) throws Exception{
		MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.fileUpload(url)
				.file(new MockMultipartFile(fieldName, originalName, null, is));
		if (params != null) {
			for (String key : params.keySet())
				builder.param(key, params.get(key));
		}
		MvcResult result=mockMvc.perform(builder).andDo(MockMvcResultHandlers.print()).andReturn();
		String msg=result.getResponse().getContentAsString();
		return msg;
	}
	
	
	
	
	
	
	
	//////////////////////////////////拼接请求头实现文件上传////////////////////////////////////////
	/**
	 * 
	 * @param url 请求网址
	 * @param params 请求参数，通过request.getParameter(name)获得
	 * @param file 文件
	 * @param fieldName 域名，跟@RequestParam(value)的value对应
	 * @return
	 * @throws Exception
	 */
	public String postUpload(String url, Map<String, String> params, String fieldName, File file) throws Exception {
		InputStream is = new FileInputStream(file);
		return postUpload(url, params, is, fieldName, file.getName());
	}

	/**
	 * 
	 * @param url 请求网址
	 * @param params 请求参数，通过request.getParameter(name)获得
	 * @param filePath 文件路径
	 * @param fieldName 域名，跟@RequestParam(value)的value对应
	 * @return
	 * @throws Exception
	 */
	public String postUpload(String url, Map<String, String> params, String fieldName, String filePath)
			throws Exception {
		File file = new File(filePath);
		InputStream is = new FileInputStream(file);
		return postUpload(url, params, is, fieldName, file.getName());
	}

	/**
	 * 
	 * @param url 请求网址
	 * @param params 请求参数，通过request.getParameter(name)获得
	 * @param is 文件输入流
	 * @param fieldName 域名，跟@RequestParam(value)的value对应
	 * @param fileName 文件名
	 * @return
	 * @throws Exception
	 */
	public String postUpload(String url, Map<String, String> params, InputStream is, String fieldName, String fileName)
			throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url).accept(MediaType.ALL);

		if (params != null) {
			for (String key : params.keySet())
				builder.param(key, params.get(key));
		}

		String BOUNDARY = "---------" + System.currentTimeMillis();
		builder.contentType("multipart/form-data; boundary=" + BOUNDARY);
		StringBuilder sb = new StringBuilder();

		sb.append("--" + BOUNDARY + "\r\n");
		// 这里是content
		sb.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"\r\n");
		sb.append("Content-Type: " + "text/plain;charset=utf-8" + "\r\n");
		sb.append("\r\n");

		byte[] b = new byte[1024];
		int len = -1;
		while ((len = is.read(b)) != -1) {
			sb.append(new String(b, 0, len, "utf-8"));
		}
		sb.append("\r\n");// 内容结束后换行

		// 所有内容结束
		sb.append("--" + BOUNDARY + "--\r\n");

		builder.content(sb.toString().getBytes("utf-8"));
		ResultActions rActions = mockMvc.perform(builder);
		rActions.andDo(MockMvcResultHandlers.print());
		MvcResult result = rActions.andReturn();
		String content = result.getResponse().getContentAsString();
		return content;
	}

}
