package com.wt.test.webconf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ByteBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring/springmvc.xml", "classpath:spring/applicationContext-*.xml" })
public class WebRunner {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		System.out.println("WebRunner @before setup");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		// 初始化具体某个controller
//		this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}
	
	public String getResponseContent(String url) throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	public int getResponseStatus(String url) throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON)
				).andDo(MockMvcResultHandlers.print()).andReturn();
		int status = result.getResponse().getStatus();
		return status;
	}
	
	public String getResponseContent(String url,Map<String,String> params) throws Exception{
		MockHttpServletRequestBuilder msr=MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
		
		MultiValueMap<String, String> p=new LinkedMultiValueMap<String, String>();
		for(String key:params.keySet())
			p.add(key, params.get(key));
		msr.params(p);
		MvcResult result=mockMvc.perform(msr).andDo(MockMvcResultHandlers.print()).andReturn();
		System.out.println("url:"+result.getRequest().getRequestURL()+":"+result.getRequest().getRequestURI());
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	public String getResponseContent2(String url,Map<String,String> params) throws Exception{
		MockHttpServletRequestBuilder msr=MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
		for(String key:params.keySet())
			msr.param(key, params.get(key));
		MvcResult result=mockMvc.perform(msr).andDo(MockMvcResultHandlers.print()).andReturn();
		System.out.println("url:"+result.getRequest().getRequestURL()+":"+result.getRequest().getRequestURI());
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	public String getResponseContent3(String url,Map<String,String> params) throws Exception{
		MockHttpServletRequestBuilder msr=MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
		
		HttpHeaders p=new HttpHeaders();
		for(String key:params.keySet())
			p.add(key, params.get(key));
		msr.params(p);
		MvcResult result=mockMvc.perform(msr).andDo(MockMvcResultHandlers.print()).andReturn();
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////
	
	public String postResponseContent(String url) throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	public int postResponseStatus(String url) throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
				.accept(MediaType.APPLICATION_JSON)
				).andDo(MockMvcResultHandlers.print()).andReturn();
		int status = result.getResponse().getStatus();
		return status;
	}
	
	public String postResponseContent(String url,Map<String,String> params) throws Exception{
		MockHttpServletRequestBuilder msr=MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON);
		for(String key:params.keySet())
			msr.param(key, params.get(key));
		MvcResult result=mockMvc.perform(msr).andDo(MockMvcResultHandlers.print()).andReturn();
		System.out.println("url:"+result.getRequest().getRequestURL()+":"+result.getRequest().getRequestURI());
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	public String postResponseContent (String url,String json) throws Exception{
		MockHttpServletRequestBuilder msr=MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
		.characterEncoding("UTF-8")
		.content(json);
		MvcResult result=mockMvc.perform(msr).andDo(MockMvcResultHandlers.print()).andReturn();
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	//返回页面，//请求转发
	public String getForwardedUrl(String url) throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
				.andReturn();
		//当spring返回的是一个页面的时候，return的字符串如果指向一张页面，则返回这张页面的相对路径,例如/WEB-INF/jsp/success.jsp，
		//如果返回的字符串是一个请求转发的页面,则返回重定向的路径，例如controller中 return "forward:/toforeard",则返回/toforward
		String content=result.getResponse().getForwardedUrl();
		return content;
	}
	
	//重定向页面
	public String getRedirectedUrl(String url) throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
				.andReturn();
		//返回重定向的路径，例如controller中 return "redirect:/toforeard",则返回/toforward
		String content=result.getResponse().getRedirectedUrl();
		return content;
	}
	
	//模拟表单上传文件
	public String postUpload(String url) throws Exception{
		MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.post("/upload");
		
		String BOUNDARY = "---------" + System.currentTimeMillis();
		builder.contentType("multipart/form-data; boundary=" + BOUNDARY);
		StringBuilder sb = new StringBuilder();

		sb.append("--"+BOUNDARY+"\r\n");
		// 这里是content
        sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + "新建文本文档.txt" + "\"\r\n"); 
        sb.append("Content-Type: " + "text/plain;charset=utf-8" + "\r\n"); 
        sb.append("\r\n");
		sb.append("哈哈哈和按劳动法卡士大夫加回复撒"+"\r\n");
		//所有内容结束
	    sb.append("--" + BOUNDARY + "--\r\n"); 
	    
		builder.content(sb.toString().getBytes("utf-8"));
		ResultActions rActions = mockMvc.perform(builder);
		rActions.andDo(MockMvcResultHandlers.print());
		MvcResult result = rActions.andReturn();
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	//模拟表单上传文件+其他参数,这里面的其他参数用request.getParameter(name)获得
	public String upload2(String url) throws Exception{
		MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.post("/upload2");
		
		String BOUNDARY = "----------" + System.currentTimeMillis();
		builder.contentType("multipart/form-data; boundary=" + BOUNDARY);
		StringBuilder sb = new StringBuilder();
		
		sb.append("--"+BOUNDARY+"\r\n");
		sb.append("Content-Disposition: form-data; name=\"abcd\""+"\r\n");
		sb.append("\r\n");
		sb.append("abcd得值"+"\r\n");
		
        sb.append("--"+BOUNDARY+"\r\n");
        // 这里是content
        sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + "新建文本文档.txt" + "\"\r\n"); // 这里是参数名，参数名和值之间要用两次
        sb.append("Content-Type: " + "text/plain;charset=utf-8" + "\r\n"); 
        sb.append("\r\n");
		sb.append("哈哈哈和按劳动法卡士大夫加回复撒"+"\r\n");
	    sb.append("--" + BOUNDARY + "--\r\n"); 
		builder.content(sb.toString().getBytes("utf-8"));
		ResultActions rActions = mockMvc.perform(builder);
		rActions.andDo(MockMvcResultHandlers.print());
		MvcResult result = rActions.andReturn();
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	//模拟表单上传文件+其他参数+绑定参数
	public String upload3(String url) throws Exception{
		MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.post("/upload3");
		
		String BOUNDARY = "----------" + System.currentTimeMillis();
		builder.contentType("multipart/form-data; boundary=" + BOUNDARY);
		StringBuilder sb = new StringBuilder();
		
		sb.append("--"+BOUNDARY+"\r\n");
		sb.append("Content-Disposition: form-data; name=\"abcd\""+"\r\n");
		sb.append("\r\n");
		sb.append("abcd得值"+"\r\n");
		
		sb.append("--"+BOUNDARY+"\r\n");
		sb.append("Content-Disposition: form-data; name=\"name\""+"\r\n");
		sb.append("\r\n");
		sb.append("wtttt"+"\r\n");
		
		sb.append("--"+BOUNDARY+"\r\n");
		sb.append("Content-Disposition: form-data; name=\"sex\""+"\r\n");
		sb.append("\r\n");
		sb.append("nan"+"\r\n");
		
		sb.append("--"+BOUNDARY+"\r\n");
		sb.append("Content-Disposition: form-data; name=\"age\""+"\r\n");
		sb.append("\r\n");
		sb.append("23"+"\r\n");
		
        sb.append("--"+BOUNDARY+"\r\n");
        // 这里是content
        sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + "新建文本文档.txt" + "\"\r\n"); // 这里是参数名，参数名和值之间要用两次
        sb.append("Content-Type: " + "text/plain;charset=utf-8" + "\r\n"); 
        sb.append("\r\n");
		sb.append("哈哈哈和按劳动法卡士大夫加回复撒"+"\r\n");
	    sb.append("--" + BOUNDARY + "--\r\n"); 
		builder.content(sb.toString().getBytes("utf-8"));
		ResultActions rActions = mockMvc.perform(builder);
		rActions.andDo(MockMvcResultHandlers.print());
		MvcResult result = rActions.andReturn();
		String content=result.getResponse().getContentAsString();
		return content;
	}
	
	public String postToInputStream(String url, String data) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url);
		builder.accept(MediaType.APPLICATION_JSON);
		builder.content(data);

		ResultActions rActions = mockMvc.perform(builder);
		rActions.andDo(MockMvcResultHandlers.print());
		MvcResult result = rActions.andReturn();
		String content = result.getResponse().getContentAsString();
		return content;
	}
	
}
