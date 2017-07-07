package com.wt.test.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wt.test.webconf.WebRunnerCommonMethod;

public class CommonMethodTest extends WebRunnerCommonMethod {

	private String path = "/common";

	@Test
	public void get1() throws Exception {
		get(path + "/user/user", null, null);
	}

	@Test
	public void get2() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("a", "b");
		params.put("c", "d");
		get(path + "/getParams", params, null);
	}

	@Test
	public void get3() throws Exception {
		String data = "aaaaaaaaaaa";
		get(path + "/postToInputStream", null, data);
	}

	@Test
	public void get4() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("a", "b");
		params.put("c", "d");
		String data = "aaaaaaaaaaa";
		get(path + "/getParamsandInputStream", params, data);
	}

	@Test
	public void post1() throws Exception {
		post(path + "/user/user", null, null, null);
	}

	@Test
	public void post2() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("a", "b");
		params.put("c", "d");
		post(path + "/getParams", params, null, null);
	}

	@Test
	public void post3() throws Exception {
		String data = "aaaaaaaaaaa";
		post(path + "/postToInputStream", null, data, null);
	}

	@Test
	public void post4() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("a", "b");
		params.put("c", "d");
		String data = "aaaaaaaaaaa";
		post(path + "/getParamsandInputStream", params, data, null);
	}

	@Test
	public void postPojo_Param_InputStream() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("p", "pValue");
		params.put("abcd", "abcdValue");
		params.put("name", "name");
		params.put("age", "23");
		params.put("sex", "男");
		String data = "aaaaaaaaaaa";
		post(path + "/postPojo_Param_InputStream", params, data, null);
	}

	@Test
	public void postUpload() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("p", "pValue");
		params.put("abcd", "abcdValue");
		params.put("name", "name");
		params.put("age", "23");
		params.put("sex", "男");
		InputStream is = new ByteArrayInputStream("哈哈哈hahaha".getBytes());
		postUpload(path + "/postPojo_Param_File", params, is,"file","aaa");
	}

	@Test
	public void fileUpload() throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("p", "pValue");
		params.put("abcd", "abcdValue");
		params.put("name", "name");
		params.put("age", "23");
		params.put("sex", "男");
		InputStream is = new ByteArrayInputStream("哈哈哈hahaha".getBytes());
		fileUpload(path + "/postPojo_Param_File", "file", "kk.jpg", is, params);
	}
}
