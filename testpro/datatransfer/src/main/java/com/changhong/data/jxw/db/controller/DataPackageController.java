package com.changhong.data.jxw.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.data.jxw.db.entity.DataPackageInfo;
import com.changhong.data.jxw.db.entity.req.DataPackageReq;
import com.changhong.data.jxw.db.entity.res.BaseRes;
import com.changhong.data.jxw.db.entity.res.DataPackageInfoRes;
import com.changhong.data.jxw.db.service.DataPackageService;

/**
 * 
 * 用户数据打包控制器
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:02:00
 */
@Controller
@RequestMapping("/api/v1/package")
public class DataPackageController {

	@Autowired
	private DataPackageService dpService;

	/**
	 * 打包数据
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public BaseRes add(@RequestBody DataPackageReq dpr) {
		BaseRes br = new BaseRes();
		try {
			String dpid = dpService.addDataPackage(dpr);
			br.setError(0);
			br.setData(dpid);
		} catch (Exception e) {
			br.setError(1);
			br.setMsg("上传数据包出错：" + e.getMessage());
		}
		return br;
	}

	// 根据userId查询数据源，在根据数据源查询所有的包列表（只查datapackage单表）
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public BaseRes getPackages(@RequestParam("userId") String userId) {
		BaseRes br = new BaseRes();
		try {
			// 查询包列表
			List<DataPackageInfo> dpis = dpService.getPackages(userId);
			br.setData(dpis);
			br.setError(0);
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg("获取数据包信息失败");
		}
		return br;
	}

	// 查询单个包的信息，包括package包含的表名，列名
	@RequestMapping(value = "/{datapackageId}", method = RequestMethod.GET)
	@ResponseBody
	public BaseRes getPackageInfo(@PathVariable("datapackageId") String datapackageId) {
		BaseRes br = new BaseRes();
		try {
			// 查询单个包的信息
			DataPackageInfoRes dpInfo = dpService.getDataPackageInfo(datapackageId);
			br.setError(0);
			br.setData(dpInfo);
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg("获取数据包信息失败");
		}
		return br;
	}

	// 删除package，修改一下包的状态，不删除任何数据，用户只是看不到而已
	@RequestMapping(value = "/{dataPackageId}", method = RequestMethod.DELETE)
	@ResponseBody
	public BaseRes deletePackage(@PathVariable("dataPackageId") String dataPackageId) {
		BaseRes br = new BaseRes();
		try {
			dpService.deletePackageChangeIsDel(dataPackageId);
			br.setError(0);
			br.setData("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg("删除失败，请重试");
		}
		return br;
	}

	////////////////////// 删除package所有数据挤以及drop掉相应的目标表和临时数据表///////////////////
	// 该接口未使用，已改为修改状态的方式删除package
//	@RequestMapping(value = "/dontDeleteThis/{dataPackageId}", method = RequestMethod.DELETE)
//	@ResponseBody
//	public BaseRes deletePackage2(@PathVariable("dataPackageId") String dataPackageId) {
//		BaseRes br = new BaseRes();
//		try {
//			dpService.deletePackage(dataPackageId);
//			br.setError(0);
//			br.setData("删除成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			br.setError(1);
//			br.setMsg("删除失败，请重试");
//		}
//		return br;
//	}

}
