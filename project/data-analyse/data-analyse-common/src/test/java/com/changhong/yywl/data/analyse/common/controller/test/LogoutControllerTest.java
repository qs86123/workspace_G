package com.changhong.yywl.data.analyse.common.controller.test;

import com.changhong.yywl.data.analyse.common.interceptor.RightInterceptor;
import com.changhong.yywl.data.analyse.common.util.JsonCheckUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Administrator on 16-9-18.
 */
public class LogoutControllerTest extends WebRunner{
    private static final String NORMAL_JSON_STRING="{\"code\":200,\"msg\":\"注销成功\",\"data\":null}";
    @Test
    public void logoutTest() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder= MockMvcRequestBuilders.get("/logout").sessionAttr("right",true);
        ResultActions resultActions=mockMvc.perform(mockHttpServletRequestBuilder);
        String res=resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//        System.out.println(res);
        boolean b= JsonCheckUtils.checkJsonFormat(NORMAL_JSON_STRING, res);
        Assert.assertTrue(b);
    }


}
