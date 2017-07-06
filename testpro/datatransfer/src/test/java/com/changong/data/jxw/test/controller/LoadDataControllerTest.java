package com.changong.data.jxw.test.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.mapper.DataPackageMapper;
import com.changhong.data.jxw.db.mapper.DatasourceMapper;
import com.changhong.data.jxw.db.mapper.LoadDataPackageMapper;
import com.changhong.data.jxw.db.mapper.LoadTableMapper;
import com.changong.data.jxw.test.common.WebRunner;

/**
 * 
 * 用户添加数据源后，选择表与平台同步的接口测试类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午2:29:13
 */
public class LoadDataControllerTest extends WebRunner {

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

	@Autowired
	private LoadDataPackageMapper ldpMapper;

	@Autowired
	private LoadTableMapper ltMapper;

	@Autowired
	private DataPackageMapper dpMapper;

	// 设置数据源保存的id
	private String datasourceId = "qwer";
	// 设置用户id
	private String userId = "asdf";
	// 设置load_data_package的id
	private String ldpid = "ldpid";

	private String baseUrl = "/api/v1/loadData";

	@Before
	public void before() {
		System.out.println("setup~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		prepareData();
	}

	// 测试同步用户数据源，此操作会在平台建立与用户数据库相同的表，由于测试用的平台的数据库，所以肯定有一张表表名交datasource
	// 为了不出错，就为此张表建立目标表
	@Test
	public void loadTable() throws Exception {
		String url = baseUrl + "/loadTable";
		JSONObject json = new JSONObject();
		json.put("name", "name");
		json.put("description", "desc");
		json.put("datasourceId", this.datasourceId);
		JSONArray ja = new JSONArray();
		JSONObject table1 = new JSONObject();
		// table_name为标的描述信息，tableSource才是表名
		table1.put("tableName", "table_desc");
		table1.put("tableSource", "datasource");
		ja.add(table1);
		json.put("tables", ja);
		MvcResult result = post(url, null, null, json.toJSONString());
		JSONObject jsonRst = JSONObject.parseObject(result.getResponse().getContentAsString());
		if (jsonRst.getIntValue("error") == 0) {
			// 如果返回的值error=0则表示添加成功，将id取出来，以便后边删除测试数据
			this.ldpid = jsonRst.getJSONObject("data").getString("id");
		}
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
		// 测试数据源为null时
		json.put("datasourceId", "error");
		post(url, null, null, json.toJSONString());
	}

	// 用户删除同与数据库同步的目标表
	@Test
	public void delTable() throws Exception {
		String url = baseUrl + "/" + this.ldpid;
		MvcResult result = doHttp(url, null, null, null, "delete");
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
	}

	@Test
	public void getTablesAsyncPlat() throws Exception {
		String url = baseUrl + "/" + this.userId;
		MvcResult result = doHttp(url, null, null, null, "get");
		int status = result.getResponse().getStatus();
		System.out.println(status);
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
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
		// 测试的时候执行了add操作，会在平台建立对应的目标表，先获取到目标表的名字
		List<String> targetTables = ltMapper.selectTargetTablesByLoadDataPackageId(this.ldpid);
		// 删除掉load_data_package表测试加入的记录
		ltMapper.deleteByLoadDataPackageId(this.ldpid);
		// 删除掉关联表load_table表测试加入的记录
		ldpMapper.deleteById(this.ldpid);
		// drop掉测试建的目标表
		for (String targetTable : targetTables) {
			dpMapper.deleteTable(targetTable);
		}
	}

}
