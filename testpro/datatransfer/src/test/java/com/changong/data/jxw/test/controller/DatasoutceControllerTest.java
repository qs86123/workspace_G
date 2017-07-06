package com.changong.data.jxw.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.mapper.DatasourceMapper;
import com.changong.data.jxw.test.common.WebRunner;

/**
 * 
 * 用户数据源增删改查接口测试类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午2:31:03
 */
public class DatasoutceControllerTest extends WebRunner {

	@Autowired
	private DatasourceMapper dsMapper;

	private String datasourceId = "qwer";
	private String userId = "asdf";
	private String datasourceId2 = "";

	@Before
	public void before() {
		System.out.println("setup~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// 测试前准备数据
		DatasourceInfo dsInfo = new DatasourceInfo();
		dsInfo.setId(datasourceId);
		dsInfo.setUid(userId);
		dsInfo.setDbType("mysql");
		dsInfo.setPort(3306);
		dsInfo.setUrl("127.0.0.1");
		dsInfo.setSchemaName("kt");
		dsInfo.setValidate("validate");
		dsInfo.setUsername("aaa");
		dsInfo.setPwd("aaa");
		dsMapper.insert(dsInfo);
	}

	private String baseUrl = "/api/v1/datasource";

	/**
	 * 添加用户数据源
	 * 
	 * @throws Exception
	 */
	@Test
	public void add() throws Exception {
		JSONObject json = new JSONObject();
		json.put("uid", this.userId);
		json.put("url", "127.0.0.1");
		json.put("port", "3306");
		json.put("username", "root");
		json.put("pwd", "mysql");
		json.put("dbType", "mysql");
		json.put("schemaName", "kt");
		MvcResult result = doHttp(baseUrl, null, null, json.toJSONString(), "put");
		JSONObject jsonRst = JSONObject.parseObject(result.getResponse().getContentAsString());
		if (jsonRst.getIntValue("error") == 0) {
			// 如果返回的值error=0则表示添加成功，将id取出来，以便后边删除测试数据
			this.datasourceId2 = jsonRst.getJSONObject("data").getString("id");
		}
		// 测试catch块
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
		json.put("username", "error");
		json.put("pwd", "error");
		doHttp(baseUrl, null, null, json.toJSONString(), "put");
	}

	/**
	 * 更新用户数据源
	 * 
	 * @throws Exception
	 */
	@Test
	public void update() throws Exception {
		JSONObject json = new JSONObject();
		json.put("id", this.datasourceId);
		json.put("uid", this.userId);
		json.put("url", "127.0.0.1");
		json.put("port", "3306");
		json.put("username", "root");
		json.put("pwd", "mysql");
		json.put("dbType", "mysql");
		json.put("schemaName", "kt");
		MvcResult result = doHttp(baseUrl, null, null, json.toJSONString(), "post");
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
		// 测试catch块
		json.put("username", "error");
		json.put("pwd", "error");
		doHttp(baseUrl, null, null, json.toJSONString(), "post");
	}

	/**
	 * 根据用户id获取用户的所有数据源
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAll() throws Exception {
		JSONObject json = new JSONObject();
		Map<String, String> params = new HashMap<>();
		params.put("userId", this.userId);
		MvcResult result = doHttp(baseUrl, params, null, json.toJSONString(), "get");
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
	}

	/**
	 * 根据数据源id获取某一条数据源的信息
	 * 
	 * @throws Exception
	 */
	@Test
	public void getOne() throws Exception {
		JSONObject json = new JSONObject();
		Map<String, String> params = new HashMap<>();
		params.put("userId", this.userId);
		params.put("datasourceId", this.datasourceId);
		MvcResult result = doHttp(baseUrl, params, null, json.toJSONString(), "get");
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
	}

	/**
	 * 删除某条用户数据源
	 * 
	 * @throws Exception
	 */
	@Test
	public void delete() throws Exception {
		JSONObject json = new JSONObject();
		Map<String, String> params = new HashMap<>();
		params.put("datasourceId", this.datasourceId);
		MvcResult result = doHttp(baseUrl, params, null, json.toJSONString(), "delete");
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
	}

	/**
	 * 测试完成后删除测试数据
	 */
	@After
	public void after() {
		// 删除事先准备的数据源
		dsMapper.delete(this.userId, this.datasourceId);
		// 删除测试方法add添加的数据源
		dsMapper.delete(this.userId, this.datasourceId2);
	}
}
