package com.changong.data.jxw.test.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DataPackageColumnInfo;
import com.changhong.data.jxw.db.entity.DataPackageInfo;
import com.changhong.data.jxw.db.entity.DataPackageTableInfo;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.mapper.DataPackageColumnMapper;
import com.changhong.data.jxw.db.mapper.DataPackageMapper;
import com.changhong.data.jxw.db.mapper.DataPackageTableMapper;
import com.changhong.data.jxw.db.mapper.DatasourceMapper;
import com.changong.data.jxw.test.common.WebRunner;

/**
 * 
 * 示例数据获取和生产数据获取接口测试类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午2:30:31
 */
public class SourceAndSumaryDataControllerTest extends WebRunner {

	@Before
	public void before() {
		System.out.println("setup~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		prapreData();
	}

	private String baseUrl = "/api/v1/data/";

	@Autowired
	private DataPackageMapper dpMapper;

	@Autowired
	private DataPackageTableMapper dptMapper;

	@Autowired
	private DataPackageColumnMapper dpcMapper;

	@Autowired
	private DatasourceMapper dsMapper;

	private String userId = "wt";
	private String datasourceId = "ddqwertyuiop";
	private String dpid = "dpid";
	private String dptid = "dptid";
	private String dpcid = "dpcid";

	@Test
	public void sumaryTable() throws Exception {
		String url = baseUrl + "/sumary/" + this.dpid;
		MvcResult result = get(url, null, null);
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
		// 测试数据源为null
		String url2 = baseUrl + "/sumary/errordsid";
		get(url2, null, null);
	}

	@Test
	public void getSourceData() throws Exception {
		String url = baseUrl + "/source";
		JSONObject json = new JSONObject();
		json.put("dbType", "mysql");
		json.put("tableSource", "datasource");
		json.put("columns", Arrays.asList("url", "port"));
		MvcResult result = post(url, null, null, json.toJSONString());
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
		// 测试列名为空
		json.put("columns", new ArrayList<>());
		post(url, null, null, json.toJSONString());
		// 测试tableSource为null
		json.put("tableSource", null);
		post(url, null, null, json.toJSONString());
	}

	private void prapreData() {
		DatasourceInfo dsInfo = new DatasourceInfo();
		dsInfo.setId(datasourceId);
		dsInfo.setUid(userId);
		dsInfo.setDbType("mysql");
		dsInfo.setPort(3306);
		dsInfo.setUrl("127.0.0.1");
		dsInfo.setValidate("validate");
		dsInfo.setUsername("root");
		dsInfo.setPwd("mysql");
		dsMapper.insert(dsInfo);
		DataPackageInfo dp = new DataPackageInfo();
		dp.setId(this.dpid);
		dp.setUid(this.userId);
		dp.setTitle("dpTitle");
		dp.setIsDel(0);
		dpMapper.insert(dp);
		DataPackageTableInfo dpt = new DataPackageTableInfo();
		dpt.setId(this.dptid);
		dpt.setDpid(this.dpid);
		dpt.setTableName("datasource");
		dpt.setTableSource("datasource");
		dpt.setTargetTable("datasource");
		dpt.setSumaryTable("datasource");
		dptMapper.insert(dpt);
		DataPackageColumnInfo dpc = new DataPackageColumnInfo();
		dpc.setId(this.dpcid);
		dpc.setDptid(this.dptid);
		dpc.setColumnSource("url");
		dpc.setColumnName("url");
		dpc.setColumnType("varchar(100)");
		dpcMapper.insert(dpc);
	}

	@After
	public void after() {
		dsMapper.delete(this.userId, this.datasourceId);
		// 根据dptid删除dataPackage
		dpcMapper.deleteByDataPackageId(dpid);
		// 根据dpid删除dataPackageTable
		dptMapper.deleteByDataPackageId(dpid);
		// 根据dpid删除dataPackage
		dpMapper.deleteById(dpid);
	}
}
