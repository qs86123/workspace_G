package com.changhong.data.jxw.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.req.LoadDataPackageReq;
import com.changhong.data.jxw.db.entity.res.BaseRes;
import com.changhong.data.jxw.db.entity.res.LoadDataPackageRes;
import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.db.service.DataSourceService;
import com.changhong.data.jxw.db.service.LoadDataService;

/**
 * 
 * 根据数据源提供的表名，在平台建表
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月20日下午1:52:43
 */
@Controller
@RequestMapping("/api/v1/loadData")
public class LoadDataController {

	@Autowired
	private LoadDataService loadDataService;

	@Autowired
	private DataSourceService dsService;

	// 根据表名在平台创建对应的目标表
	@RequestMapping(value = "/loadTable", method = RequestMethod.POST)
	@ResponseBody
	public BaseRes loadTable(@RequestBody LoadDataPackageReq ldr) {
		BaseRes br = new BaseRes();
		DatasourceInfo dsInfo = dsService.getOne(ldr.getDatasourceId());
		if (dsInfo == null) {
			br.setError(1);
			br.setMsg("未找到相关数据源信息");
			return br;
		}
		try {
			JSONObject json = new JSONObject();
			String ldpid = loadDataService.insert(ldr, dsInfo);
			br.setError(0);
			json.put("id", ldpid);
			br.setData(json);
		} catch (ConnException e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg("操作失败:" + e.getMessage());
		}
		return br;
	}

	// 删除
	@RequestMapping(value = "/{loadPackageId}", method = RequestMethod.DELETE)
	@ResponseBody
	public BaseRes delTable(@PathVariable("loadPackageId") String loadPackageId) {
		BaseRes br = new BaseRes();
		try {
			loadDataService.delete(loadPackageId);
			br.setError(0);
			br.setData("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(0);
			br.setMsg("操作失败");
		}
		return br;
	}

	// 返回与平台同步的表，及其结构信息
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public BaseRes getTablesAsyncPlat(@PathVariable("userId") String userId) {
		BaseRes br = new BaseRes();
		try {
			List<LoadDataPackageRes> list = loadDataService.getTableAsyncPlat(userId);
			br.setError(0);
			br.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(0);
			br.setMsg("获取表信息失败：" + e.getMessage());
		}
		return br;
	}

}
