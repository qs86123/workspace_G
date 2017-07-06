package com.changong.data.jxw.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DataPackageColumnInfo;
import com.changhong.data.jxw.db.entity.DataPackageInfo;
import com.changhong.data.jxw.db.entity.DataPackageTableInfo;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.mapper.DataPackageColumnMapper;
import com.changhong.data.jxw.db.mapper.DataPackageMapper;
import com.changhong.data.jxw.db.mapper.DataPackageTableMapper;
import com.changhong.data.jxw.db.mapper.DatasourceMapper;
import com.changhong.data.jxw.db.service.DataPackageService;
import com.changong.data.jxw.test.common.WebRunner;

/**
 * 
 * 用户数据打包接口测试类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午2:31:21
 */
public class DataPackageControllerTest extends WebRunner {

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
	private DataPackageMapper dpMapper;

	@Autowired
	private DataPackageTableMapper dptMapper;

	@Autowired
	private DataPackageColumnMapper dpcMapper;

	@Autowired
	private DatasourceMapper dsMapper;

	@Autowired
	private DataPackageService dpService;

	// 测试前准备的数据记录的id，以便测试完之后删除
	private String userId = "wt";
	private String datasourceId = "ddqwertyuiop";
	private String dpid = "dpid";
	private String dptid = "dptid";
	private String dpcid = "dpcid";
	// 这个id用来保存 add接口添加的那个id，便与测试完成之后删除
	private String dpid2 = "";

	@Before
	public void before() {
		System.out.println("setup~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		prapreData();
	}

	private String baseUrl = "/api/v1/package";

	/**
	 * 根据用户id查询用户的数据包
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPackages() throws Exception {
		String url = baseUrl;
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", this.userId);
		MvcResult result = get(url, params, null);
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
	}

	/**
	 * 根据数据包id，查询单个数据包的详细信息
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPackageInfo() throws Exception {
		String url = baseUrl + "/" + this.dpid;
		MvcResult result = get(url, null, null);
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
	}

	// 删除数据包
	@Test
	public void deletePackage() throws Exception {
		String url = baseUrl + "/" + this.dpid;
		MvcResult result = doHttp(url, null, null, null, "delete");
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
	}

	// 添加数据包
	@Test
	public void add() throws Exception {
		String url = baseUrl;
		JSONObject json = new JSONObject();
		json.put("userId", this.userId);
		json.put("title", "title");
		JSONArray datiArray = new JSONArray();
		JSONObject dati = new JSONObject();
		dati.put("datasourceId", this.datasourceId);
		JSONArray tableInfos = new JSONArray();
		JSONObject table = new JSONObject();
		table.put("tableSource", "datasource");
		table.put("targetTable", "targetTable");
		table.put("targetName", "table描述信息");
		JSONArray columns = new JSONArray();
		JSONObject column = new JSONObject();
		column.put("columnName", "column描述");
		column.put("columnSource", "url");
		column.put("columnType", "varchar(128)");
		columns.add(column);
		table.put("columns", columns);
		tableInfos.add(table);
		dati.put("tableInfo", tableInfos);
		datiArray.add(dati);
		json.put("dbAndTableInfo", datiArray);

		MvcResult result = doHttp(url, null, null, json.toJSONString(), "put");
		JSONObject resultJson = JSONObject.parseObject(result.getResponse().getContentAsString());
		if (resultJson.getIntValue("error") == 0) {
			// 如果error=0表示添加成功，将新增的数据包的获取到，测试完后删除该条测试记录
			this.dpid2 = resultJson.getString("data");
		}
		Assert.assertTrue(this.status(result.getResponse().getStatus()));
	}

	@After
	public void after() {
		dsMapper.delete(this.userId, this.datasourceId);
		dpService.deletePackage(this.dpid);
		dpService.deletePackage(this.dpid2);
	}

	// 准备数据
	private void prapreData() {
		DatasourceInfo dsInfo = new DatasourceInfo();
		dsInfo.setId(datasourceId);
		dsInfo.setUid(userId);
		dsInfo.setDbType("mysql");
		dsInfo.setPort(Integer.parseInt(this.port));
		dsInfo.setUrl(this.ip);
		dsInfo.setValidate("validate");
		dsInfo.setUsername(this.username);
		dsInfo.setPwd(this.password);
		dsInfo.setSchemaName(this.dbName);
		dsMapper.insert(dsInfo);
		DataPackageInfo dp = new DataPackageInfo();
		dp.setId(this.dpid);
		dp.setUid(this.userId);
		dp.setTitle("dpTitle");
		dpMapper.insert(dp);
		DataPackageTableInfo dpt = new DataPackageTableInfo();
		dpt.setId(this.dptid);
		dpt.setDpid(this.dpid);
		dpt.setTableName("tableName");
		dpt.setTableSource("tableSource");
		dpt.setTargetTable("targetTable");
		dpt.setSumaryTable("sumaryTable");
		dptMapper.insert(dpt);
		DataPackageColumnInfo dpc = new DataPackageColumnInfo();
		dpc.setId(this.dpcid);
		dpc.setDptid(this.dptid);
		dpc.setColumnSource("columnSource");
		dpc.setColumnName("columnName");
		dpc.setColumnType("varchar(100)");
		dpcMapper.insert(dpc);
	}

}
