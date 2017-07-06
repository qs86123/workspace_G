//package com.changhong.data.jxw.db.service;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.SingleColumnRowMapper;
//import org.springframework.stereotype.Service;
//
//import com.changhong.data.jxw.db.entity.TableFieldInfo;
//import com.changhong.data.jxw.db.entity.TableInfo;
//
///**
// * 改类未使用，暂时保留
// * 
// * Company: changhong
// * 
// * @author wangtao
// * @date 2017年3月24日上午10:00:39
// */
//@Service
//public class PlatDBService {
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	// 获取所有表名
//	public List<String> getTables() {
//		return jdbcTemplate.query("show tables", new SingleColumnRowMapper<String>());
//	}
//
//	// 获取所有表的结构信息
//	public List<TableInfo> getAllTableInfo() {
//		List<TableInfo> tbInfos = new ArrayList<>();
//		List<TableFieldInfo> tfInfos = new ArrayList<>();
//		// 获得所有表名
//		List<String> tables = getTables();
//		// 遍历表名，获取每张表的结构信息
//		for (String tb : tables) {
//			TableInfo tbInfo = new TableInfo();
//			tbInfo.setTableName(tb);
//			tfInfos = jdbcTemplate.query("desc " + tb.trim(), new RowMapper<TableFieldInfo>() {
//				@Override
//				public TableFieldInfo mapRow(ResultSet rs, int paramInt) throws SQLException {
//					TableFieldInfo ti = new TableFieldInfo();
//					// ti.setField(rs.getString("Field"));
//					// ti.setType(rs.getString("Type"));
//					// ti.setCanNull(rs.getString("Null"));
//					// ti.setKey(rs.getString("Key"));
//					// ti.setFieldDefault(rs.getString("Default"));
//					// ti.setFieldExtra(rs.getString("Extra"));
//					return ti;
//				}
//			});
//			tbInfo.setTableFieldInfos(tfInfos);
//			tbInfos.add(tbInfo);
//		}
//		return tbInfos;
//	}
//
//}
