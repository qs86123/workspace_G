package com.changhong.data.jxw.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.req.UserDataSourceReq;
import com.changhong.data.jxw.db.entity.res.BaseRes;
import com.changhong.data.jxw.db.service.DataSourceService;

/**
 * 用户添加数据源控制器
 * 
 * Description: Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月14日上午9:54:25
 */
@Controller
@RequestMapping("/api/v1/datasource")
public class DatasourceController {

	@Autowired
	private DataSourceService dsService;

	// 添加用户数据源
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public BaseRes add(@RequestBody DatasourceInfo dsInfo) {
		BaseRes br = new BaseRes();
		try {
			JSONObject result = dsService.add(dsInfo);
			br.setError(0);
			br.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg("添加数据源失败:" + e.getMessage());
		}
		return br;
	}

	// 删除用户数据源
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@ResponseBody
	public BaseRes delete(@RequestBody UserDataSourceReq udsr) {
		String userId = udsr.getUserId();
		String datasourceId = udsr.getDatasourceId();
		BaseRes br = new BaseRes();
		try {
			dsService.delete(userId, datasourceId);
			br.setError(0);
			br.setData("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg("删除失败");
		}
		return br;
	}

	// 获取用户数据源列表
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public BaseRes getAll(@RequestParam("userId") String userId,
			@RequestParam(value = "datasourceId", required = false) String datasourceId) {
		BaseRes br = new BaseRes();
		try {
			if (datasourceId == null || datasourceId == "") {
				List<DatasourceInfo> list = dsService.getAll(userId);
				br.setData(list);
			} else {
				DatasourceInfo dsInfo = dsService.getOne(datasourceId);
				br.setData(dsInfo);
			}
			br.setError(0);
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg("获取数据源失败");
		}
		return br;
	}

	// // 获取用户数据源
	// @RequestMapping(value = "/getDatasource/{datasourceId}", method =
	// RequestMethod.GET)
	// @ResponseBody
	// public BaseRes getDatasource(@PathVariable("datasourceId") String
	// datasourceId) {
	// BaseRes br = new BaseRes();
	// try {
	// DatasourceInfo dsInfo = dsService.getOne(datasourceId);
	// br.setError(0);
	// br.setData(dsInfo);
	// } catch (Exception e) {
	// e.printStackTrace();
	// br.setError(1);
	// br.setMsg("获取数据源失败");
	// }
	// return br;
	// }

	// 更新用户数据源
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public BaseRes edit(@RequestBody DatasourceInfo dsInfo) {
		BaseRes br = new BaseRes();
		try {
			JSONObject json = dsService.update(dsInfo);
			br.setError(0);
			br.setData(json);
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg("更新失败:" + e.getMessage());
		}
		return br;
	}

}
