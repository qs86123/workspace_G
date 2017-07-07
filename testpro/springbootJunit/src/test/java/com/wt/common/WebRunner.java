package com.wt.common;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wt.demo.ExampleApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ExampleApplication.class })
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
//		this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}
	
}
