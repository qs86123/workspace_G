package com.wt.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.wt.common.WebRunner;

public class TestController extends WebRunner{

	@Before
	public void before(){
		System.out.println("setup~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	@Test
	public void test(){
		try{
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getUser")
	                .accept(MediaType.APPLICATION_JSON)
	                ).andDo(MockMvcResultHandlers.print())
					.andReturn();
			String content=result.getResponse().getContentAsString();
			System.out.println(content);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void test2(){
		try{
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/get")
					.accept(MediaType.APPLICATION_JSON)
					).andDo(MockMvcResultHandlers.print())
					.andReturn();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
