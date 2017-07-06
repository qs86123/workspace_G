package com.changhong.data.jxw.ktr.controller;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONObject;
import com.changong.data.jxw.test.common.WebRunner;

public class DataLoadJobControllerTest extends WebRunner
{
    private String baseUrl = "/rest/ktr/";
    private JSONObject json;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        JSONObject json = new JSONObject();
        json.put("id", "12229fe-8fbe-48a1-9ef7-35dce5d7");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Test
    public void testGetDataLoadJob() throws Exception
    {
        String url = baseUrl + "/dataloadjob";
        MvcResult result = doHttp(url, null, null, json.toJSONString(), "get");
        Assert.assertTrue(this.status(result.getResponse().getStatus()));

    }

    @Test
    public void testDelDataLoadJob() throws Exception
    {
        String url = baseUrl + "/dataloadjob";
        MvcResult result = doHttp(url, null, null, json.toJSONString(), "delete");
        Assert.assertTrue(this.status(result.getResponse().getStatus()));
    }

    @Test
    public void testAddDataLoadJob() throws Exception
    {
        String url = baseUrl + "/dataloadjob";
        MvcResult result = doHttp(url, null, null, json.toJSONString(), "post");
        Assert.assertTrue(this.status(result.getResponse().getStatus()));
    }

    @Test
    public void testStartLoadJob() throws Exception
    {
        String url = baseUrl + "/dataloadjob";
        MvcResult result = doHttp(url, null, null, json.toJSONString(), "post");
        Assert.assertTrue(this.status(result.getResponse().getStatus()));
    }

    @Test
    public void testStopLoadJob() throws Exception
    {
        String url = baseUrl + "/dataloadjob/stop";
        MvcResult result = doHttp(url, null, null, json.toJSONString(), "post");
        Assert.assertTrue(this.status(result.getResponse().getStatus()));
    }

}
