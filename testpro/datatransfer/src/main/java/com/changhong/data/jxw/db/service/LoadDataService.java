package com.changhong.data.jxw.db.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.jxw.db.dao.DbDao;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.LoadDataPackage;
import com.changhong.data.jxw.db.entity.LoadTable;
import com.changhong.data.jxw.db.entity.TableFieldInfo;
import com.changhong.data.jxw.db.entity.req.LoadDataPackageReq;
import com.changhong.data.jxw.db.entity.req.LoadTableReq;
import com.changhong.data.jxw.db.entity.res.LoadDataPackageRes;
import com.changhong.data.jxw.db.entity.res.LoadTableRes;
import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.db.mapper.LoadDataPackageMapper;
import com.changhong.data.jxw.db.mapper.LoadTableMapper;

/**
 * 用于选择表与平台同步的service层
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午9:56:18
 */
@Service
public class LoadDataService {

	@Value("${db.Name}")
	private String platSchema;

	@Autowired
	private LoadDataPackageMapper ldpMapper;

	@Autowired
	private LoadTableMapper ltMapper;

	public String insert(LoadDataPackageReq ldr, DatasourceInfo dbInfo) throws ConnException {
		List<String> allCreateSqls = new ArrayList<>();
		String dsid = ldr.getDatasourceId();
		String name = ldr.getName();
		String desc = ldr.getDescription();

		// 床架保存实体类
		LoadDataPackage ldp = new LoadDataPackage();
		String ldpid = UUID.randomUUID().toString().substring(0, 32);
		ldp.setDsid(dsid);
		ldp.setDescription(desc);
		ldp.setAddTime(new Date());
		ldp.setName(name);
		ldp.setId(ldpid);

		List<LoadTableReq> tables = ldr.getTables();
		// 保存需要insert到load_table的实体集合
		List<LoadTable> loadTables = new ArrayList<>();
		// 拼接建表语句
		for (LoadTableReq tbInfo : tables) {
			// 为了避免平台建表重复，表名加上uuid字段
			String uuid = UUID.randomUUID().toString().substring(0, 32);
			// 目标表表名
			String targetTableName = uuid + "_target_" + tbInfo.getTableSource();
			// 如果名字长度超过128，则截取前128个字符
			if (targetTableName.length() > 128)
				targetTableName = targetTableName.substring(0, 128);
			// 获取建表sql
			String sql = DbDao.getDao(dbInfo.getDbType()).getCreateTableDDL(dbInfo, tbInfo.getTableSource());
			// 重组sql
			String createTargetTableSql = "CREATE TABLE `" + targetTableName + "`" + sql + ";";
			// 将sql拼接在一起，最后一起执行
			allCreateSqls.add(createTargetTableSql);

			// 创建需要保存的实体LoadTable
			LoadTable lt = new LoadTable();
			lt.setId(UUID.randomUUID().toString().substring(0, 32));
			lt.setLdpid(ldpid);
			lt.setTableName(tbInfo.getTableName());
			lt.setTableSource(tbInfo.getTableSource());
			lt.setTargetTable(targetTableName);
			lt.setIncrementField(tbInfo.getIncrementField());
			loadTables.add(lt);
		}

		doCreateAndSave(allCreateSqls, ldp, loadTables, dbInfo.getDbType());
		return ldpid;
	}

	@Transactional
	public void doCreateAndSave(List<String> allCreateSqls, LoadDataPackage ldp, List<LoadTable> lts, String dbType)
			throws ConnException {
		// 在平台建表
		DbDao dao = DbDao.getDao("mysql");
		dao.createTable(allCreateSqls);
		// 执行保存操作
		ldpMapper.insert(ldp);
		for (LoadTable lt : lts) {
			ltMapper.insert(lt);
		}
	}

	// 删除用户选择上传到平台的表，改一个状态
	public void delete(String loadPackageId) {
		ldpMapper.deleteByChangeIsDel(loadPackageId);
	}

	public List<LoadDataPackageRes> getTableAsyncPlat(String userId) {
		// 根据用户id查询所有数据源对应的与平台同步的表
		List<LoadDataPackageRes> ldprList = ldpMapper.selectByUserId(userId);
		for (LoadDataPackageRes ldpr : ldprList) {
			List<LoadTableRes> tableList = ldpr.getTableList();
			// 遍历所有表名，查询表的字段信息，这里表都是平台的目标表，所以这里取值取targettable，tablesource是用户数据源的表的名字
			for (LoadTableRes ltr : tableList) {
				// 执行查询表的结构信息，将数据放到结果中
				List<TableFieldInfo> fieldInfo = ldpMapper.selectFieldInfo(platSchema, ltr.getTargetTable());
				ltr.setTableFieldInfos(fieldInfo);
			}
		}
		return ldprList;
		// 通过leftjoin的方式查询字段信息一并封装，差U型你语句有点长，容易出错，暂不使用此种方式
		// return ldpMapper.selectWithColumnInfoByUserId(platSchema, userId);
	}

}
