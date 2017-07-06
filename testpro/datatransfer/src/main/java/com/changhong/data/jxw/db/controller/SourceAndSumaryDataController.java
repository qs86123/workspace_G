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

import com.changhong.data.jxw.db.dao.DbDao;
import com.changhong.data.jxw.db.entity.DataEntity;
import com.changhong.data.jxw.db.entity.DataPackageColumnInfo;
import com.changhong.data.jxw.db.entity.SumaryDataEntity;
import com.changhong.data.jxw.db.entity.SumaryTableInfo;
import com.changhong.data.jxw.db.entity.req.UserPackageDataReq;
import com.changhong.data.jxw.db.entity.res.BaseRes;
import com.changhong.data.jxw.db.entity.res.DataPackageInfoRes;
import com.changhong.data.jxw.db.entity.res.DataPackageTableRes;
import com.changhong.data.jxw.db.service.DataPackageService;

/**
 * 
 * 示例数据和生产数据获取接口
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:08:13
 */
@Controller
@RequestMapping("/api/v1/data")
public class SourceAndSumaryDataController {

	@Autowired
	private DataPackageService dpService;

	@RequestMapping(value = "/sumary/{dataPackageId}", method = RequestMethod.GET)
	@ResponseBody
	public BaseRes getSumaryData(@PathVariable("dataPackageId") String dataPackageId) {
		BaseRes br = new BaseRes();
		// 获取包信息
		DataPackageInfoRes dpInfo = dpService.getDataPackageInfo(dataPackageId);
		if (dpInfo == null) {
			br.setError(1);
			br.setMsg("该数据包不存在");
			return br;
		}
		// 获取package包含的table信息
		List<DataPackageTableRes> tableList = dpInfo.getTableList();
		// sumaryTableInfo用来存放每个table及其对应的包含的字段
		List<SumaryTableInfo> stInfos = new ArrayList<>();
		// 遍历所有table，并将所有table及其对应的字段放在sumaryTableInfo中
		for (DataPackageTableRes dpt : tableList) {
			SumaryTableInfo st = new SumaryTableInfo();
			List<String> columns = new ArrayList<>();
			// 遍历所有的字段
			for (DataPackageColumnInfo dpc : dpt.getDataPackageColumnInfoList()) {
				columns.add(dpc.getColumnSource());
			}
			st.setTableSource(dpt.getTableSource());
			st.setSumaryTable(dpt.getSumaryTable());
			st.setColumns(columns);
			stInfos.add(st);
		}
		try {
			// 执行查询
			List<SumaryDataEntity> platSumaryData = DbDao.getDao("mysql").getPlatSumaryData(stInfos);
			br.setData(platSumaryData);
			br.setError(0);
		} catch (Exception e) {
			e.printStackTrace();
			br.setMsg("获取数据出错:" + e.getMessage());
			br.setError(1);
		}
		return br;
	}

	@RequestMapping(value = "/source", method = RequestMethod.POST)
	@ResponseBody
	public BaseRes getSourceData(@RequestBody UserPackageDataReq updr) {
		BaseRes br = new BaseRes();
		// 获取需要查询的字段
		List<String> columns = updr.getColumns();
		// 获取查询的表的名字
		String tableSource = updr.getTableSource();
		if (columns == null || tableSource == null) {
			br.setError(1);
			br.setMsg("参数有误");
			return br;
		}
		if (columns.size() == 0 || tableSource.equals("")) {
			br.setError(1);
			br.setMsg("表名或字段名不能为空");
			return br;
		}

		int pageSize = updr.getPageSize();
		int pageNum = updr.getPageNum();
		try {
			// 查询数据
			DataEntity dataEntity = DbDao.getDao("mysql").getDataByColumns(columns, tableSource, pageSize, pageNum);
			br.setData(dataEntity);
			br.setError(0);
		} catch (Exception e) {
			e.printStackTrace();
			br.setMsg("获取数据出错:" + e.getMessage());
			br.setError(1);
		}
		return br;
	}

}
