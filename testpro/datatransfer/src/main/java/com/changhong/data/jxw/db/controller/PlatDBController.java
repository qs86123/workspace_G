//package com.changhong.data.jxw.db.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.changhong.data.jxw.db.entity.TableInfo;
//import com.changhong.data.jxw.db.entity.res.BaseRes;
//import com.changhong.data.jxw.db.service.PlatDBService;
//
///**
// * 
// * 平台数据库管理控制器，暂未使用，不删
// *  
// * Company: changhong
// * @author wangtao
// * @date 2017年3月13日下午12:40:55
// */
//
//@Controller
//@RequestMapping("/plat")
//public class PlatDBController {
//
//	@Autowired
//	private PlatDBService plantDBSertice;
//
//	/**
//	 * 获取所有表名
//	 * @return
//	 */
//	@RequestMapping("/getTables")
//	@ResponseBody
//	private BaseRes getTables() {
//		BaseRes br = new BaseRes();
//		try {
//			List<String> tables = plantDBSertice.getTables();
//			br.setError(0);
//			br.setData(tables);
//			return br;
//		} catch (Exception e) {
//			br.setError(1);
//			br.setMsg("获取表名错误");
//			return br;
//		}
//	}
//
//	/**
//	 * 获取所有表结构信息
//	 * @return
//	 */
//	@RequestMapping("/getAllTableInfo")
//	@ResponseBody
//	private BaseRes getAllTableInfo() {
//		BaseRes br = new BaseRes();
//		try {
//			List<TableInfo> tbInfos = plantDBSertice.getAllTableInfo();
//			br.setError(0);
//			br.setData(tbInfos);
//			return br;
//		} catch (Exception e) {
//			e.printStackTrace();
//			br.setError(1);
//			br.setMsg("读取表结构信息错误");
//			return br;
//		}
//	}
//
//}
