package com.changhong.yywl.data.analyse.common.controller.test;

import com.changhong.yyhl.data.encrypt.SHAUtils;
import com.changhong.yywl.data.analyse.common.util.JsonCheckUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator on 16-9-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring-app.xml","classpath:config/mvc/spring-mvc-front-filter.xml"})
public class LoginTest {
    private static final String NORMAL_JSON_STRING="{\"code\":200,\"msg\":\"注销成功\",\"data\":null}";
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void before()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loginTest() throws Exception {
        String param=SHAUtils.requestParam("abcd");
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder= MockMvcRequestBuilders.get("/logout"+param);
        ResultActions resultActions=mockMvc.perform(mockHttpServletRequestBuilder);
        String res=resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//        System.out.println(res);
        boolean b= JsonCheckUtils.checkJsonFormat(NORMAL_JSON_STRING, res);
        Assert.assertTrue(b);
    }

}
