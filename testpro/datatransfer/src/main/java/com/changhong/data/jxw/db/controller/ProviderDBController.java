package com.changhong.data.jxw.db.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.data.jxw.db.entity.DataEntity;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.TableInfo;
import com.changhong.data.jxw.db.entity.req.UserDataReq;
import com.changhong.data.jxw.db.entity.res.BaseRes;
import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.db.service.DataSourceService;
import com.changhong.data.jxw.db.service.ProviderDBService;

/**
 * 提供方数据库连接
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月8日下午4:00:41
 */
@Controller
@RequestMapping("/api/v1/userData")
public class ProviderDBController {

	@Autowired
	private ProviderDBService providerDBService;

	@Autowired
	private DataSourceService dsService;

	/**
	 * 根据数据源id查询某个数据源并获取所有表名
	 * 
	 * @param dbInfo
	 * @return
	 */
	@RequestMapping(value = "/tables/{datasourceId}", method = RequestMethod.POST)
	@ResponseBody
	public BaseRes getTables(@PathVariable String datasourceId) {
		BaseRes br = new BaseRes();
		// 根据数据源id查询数据源信息
		DatasourceInfo dsInfo = dsService.getOne(datasourceId);
		// 如果没找到数据源，则返回数据源未找到的信息
		if (dsInfo == null) {
			br.setError(1);
			br.setMsg("未找到相关数据源信息");
			return br;
		}
		try {
			// 找到数据源后直接执行查询所有表名
			String[] tables = providerDBService.getTables(dsInfo);
			br.setData(tables);
			br.setError(0);
			return br;
		} catch (ConnException e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg(e.getMessage());
		}
		return br;
	}

	/**
	 * 分页获取某张表的数据
	 * 
	 * @param dbInfo
	 * @return
	 */
	@RequestMapping(value = "/dataFromTable", method = RequestMethod.POST)
	@ResponseBody
	public BaseRes getDataFromTable(@RequestBody UserDataReq udr) {
		BaseRes br = new BaseRes();
		// 根据数据源id查询数据源信息
		DatasourceInfo dsInfo = dsService.getOne(udr.getDatasourceId());
		// 如果没找到数据源，则返回数据源未找到的信息
		if (dsInfo == null) {
			br.setError(1);
			br.setMsg("未找到相关数据源信息");
			return br;
		}
		try {
			// 查询到数据源后直接执行查询表数据操作
			DataEntity result = providerDBService.getDataFromTable(dsInfo, udr.getTableSource(), udr.getPageSize(),
					udr.getPageNum());
			br.setError(0);
			br.setData(result);
		} catch (ConnException e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg(e.getMessage());
		}
		return br;
	}

	/**
	 * 根据数据源id查询某个数据源并获取所有表的结构信息
	 * 
	 * @param dbInfo
	 * @return
	 */
	@RequestMapping(value = "/allTableInfo/{datasourceId}", method = RequestMethod.POST)
	@ResponseBody
	public BaseRes getTableInfo(@PathVariable String datasourceId) {
		BaseRes br = new BaseRes();
		// 根据数据源id查询数据源信息
		DatasourceInfo dsInfo = dsService.getOne(datasourceId);
		// 如果没找到数据源，则返回数据源未找到的信息
		if (dsInfo == null) {
			br.setError(1);
			br.setMsg("未找到相关数据源信息");
			return br;
		}
		try {
			// 找到数据源直接查询所有表结构信息
			List<TableInfo> tbInfos = new ArrayList<TableInfo>();
			tbInfos = providerDBService.getTablesInfo(dsInfo);
			br.setError(0);
			br.setData(tbInfos);
		} catch (ConnException e) {
			e.printStackTrace();
			br.setError(1);
			br.setMsg(e.getMessage());
		}
		return br;
	}

}
