package com.changong.data.jxw.test.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.mapper.DatasourceMapper;
import com.changong.data.jxw.test.common.WebRunner;

/**
 * 
 * 用户数据源信息获取，数据获取接口测试类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午2:29:58
 */
public class ProviderDBControllerTest extends WebRunner {

	// 使用平台的数据库作为用户数据源进行测试
	@Value("${db.ip}")
	private String ip;
	@Value("${db.port}")
	private String port;
	@Value("${db.Name}")
	private String dbName;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	@Autowired
	private DatasourceMapper dsMapper;

	@Before
	public void before() {
		System.out.println("setup~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		prepareData();
	}

	private String baseUrl = "/api/v1/userData/";

	private String datasourceId = "qwer";
	private String userId = "wt";

	/**
	 * 获取用户苏数据库表名列表，暂时这个接口没用到
	 * 
	 * @throws Exception
	 */
	@Test
	public void getTables() throws Exception {
		String url = baseUrl + "tables/" + this.datasourceId;
		MvcResult result = post(url, null, null, null);
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
		// 测试数据源为null
		String url2 = baseUrl + "tables/error";
		post(url2, null, null, null);
	}

	/**
	 * 获取用户数据源某张表的数据
	 * 
	 * @throws Exception
	 */
	@Test
	public void getDataFromTable() throws Exception {
		String url = baseUrl + "dataFromTable";
		JSONObject json = new JSONObject();
		json.put("userId", this.userId);
		json.put("datasourceId", this.datasourceId);
		json.put("tableSource", "datasource");
		json.put("pageSize", "1");
		json.put("pageNum", "0");
		MvcResult result = post(url, null, null, json.toJSONString());
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
		// 测试数据源为null
		String url2 = baseUrl + "dataFromTable";
		json.put("datasourceId", "errordsid");
		post(url2, null, null, json.toJSONString());
	}

	/**
	 * 获取用户数据库说有表的结构信息
	 * 
	 * @throws Exception
	 */
	@Test
	public void allTableInfo() throws Exception {
		String url = baseUrl + "allTableInfo/" + this.datasourceId;
		MvcResult result = post(url, null, null, null);
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
		// 测试数据源为null
		String url2 = baseUrl + "allTableInfo/errordsid";
		post(url2, null, null, null);
	}

	// 为测试准备数据
	private void prepareData() {
		DatasourceInfo dsInfo = new DatasourceInfo();
		dsInfo.setId(this.datasourceId);
		dsInfo.setUid(this.userId);
		dsInfo.setDbType("mysql");
		dsInfo.setPort(Integer.parseInt(this.port));
		dsInfo.setUrl(this.ip);
		dsInfo.setSchemaName(this.dbName);
		dsInfo.setValidate("validate");
		dsInfo.setUsername(this.username);
		dsInfo.setPwd(this.password);
		dsMapper.insert(dsInfo);
	}

	@After
	public void after() {
		// 删除测试时添加的数据源
		dsMapper.delete(this.userId, this.datasourceId);
	}

}
