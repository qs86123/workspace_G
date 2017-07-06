package com.changhong.data.jxw.db.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.dao.DbDao;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.TableInfo;
import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.db.mapper.DatasourceMapper;

/**
 * 用户数据库增删改查使用到的service层
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午9:50:33
 */
@Service
public class DataSourceService {

	@Autowired
	private DatasourceMapper dsMapper;

	// 新增一个用户数据源
	public JSONObject add(DatasourceInfo dsInfo) throws ConnException {
		List<TableInfo> tbsInfo = null;
		JSONObject result = new JSONObject();
		// 拼接字符串为数据库url，保存
		String validate = "";
		try {
			tbsInfo = DbDao.getDao(dsInfo.getDbType()).getTablesInfo(dsInfo);
			// 直接获取所有表信息，没有抛异常，则连接成功，设置验证成功
			validate = "连接成功";
		} catch (ConnException e) {
			e.printStackTrace();
			validate = "连接失败";
			throw new ConnException(e.getMessage());
		}
		// 保存用户数据源
		// 设置id为uuid
		String uuid = UUID.randomUUID().toString().substring(0, 32);
		System.out.println(uuid);
		dsInfo.setId(uuid);
		dsInfo.setAddTime(new Date());
		dsInfo.setValidate(validate);
		dsMapper.insert(dsInfo);
		result.put("id", uuid);
		result.put("validate", validate);
		result.put("info", tbsInfo);
		return result;
	}

	// 删除一个用户的数据源
	public void delete(String userId, String datasourceId) {
		dsMapper.delete(userId, datasourceId);
	}

	// 查询一个用户的所有数据源
	public List<DatasourceInfo> getAll(String userId) {
		return dsMapper.selectAll(userId);
	}

	// 根据数据源id查询一个数据源的信息
	public DatasourceInfo getOne(String datasourceId) {
		return dsMapper.selectOne(datasourceId);
	}

	// 更新数据源信息
	public JSONObject update(DatasourceInfo dsInfo) throws ConnException {
		List<TableInfo> tbsInfo = null;
		JSONObject result = new JSONObject();
		// 拼接字符串为数据库url，保存
		String validate = "";
		try {
			tbsInfo = DbDao.getDao(dsInfo.getDbType()).getTablesInfo(dsInfo);
			// 直接获取所有表信息，没有抛异常，则连接成功，设置验证成功
			validate = "连接成功";
		} catch (ConnException e) {
			e.printStackTrace();
			validate = e.getMessage();
			throw new ConnException(e.getMessage());
		}
		// 更新用户数据源
		dsInfo.setAddTime(new Date());
		dsInfo.setValidate(validate);
		dsMapper.update(dsInfo);
		result.put("id", dsInfo.getId());
		result.put("validate", validate);
		result.put("info", tbsInfo);
		return result;
	}

	//方法暂未使用
	public DatasourceInfo selectByDataPackageId(String dataPackageId) {
		return dsMapper.selectByDataPackageId(dataPackageId);
	}

}
