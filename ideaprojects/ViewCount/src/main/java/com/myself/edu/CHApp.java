package com.myself.edu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.myself.edu.utils.Logger;
import com.myself.edu.utils.QuickCrawlUtil;

public class CHApp {

	public static void main(String[] args) {
		//设置基本信息
		QuickCrawlUtil.INS.setRequestMethod("POST")
		.setProxySiteName("CHAPP")
		.addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
//		.addExtraHeader("Accept-Encoding", "gzip, deflate")
//		.addExtraHeader("Accept-Language", "zh-CN,zh;q=0.8")
//		.addExtraHeader("Connection", "keep-alive")
		.addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
		.addExtraHeader("Cookie", "JSESSIONID=65F68F124932CDAB08540721E23FD0A6")
//		.addExtraHeader("Host", "211.149.204.181:8090")
//		.addExtraHeader("Origin", "http://211.149.204.181:8090")
//		.addExtraHeader("Referer", "http://211.149.204.181:8090/admin/index.xhtml")
		.addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
		.addExtraHeader("X-Requested-With", "XMLHttpRequest")
		;
		
		/*final Map<String, String> bMap = new HashMap<String, String>();
		bMap.put("relyId", "");
		bMap.put("tel", "2418095");
		bMap.put("address", "四川省绵阳市涪城区高新区绵兴东路35号");
		bMap.put("longitude", "104.675077");
		bMap.put("latitude", "31.467744");
		bMap.put("mapIntro", "");
		bMap.put("mapTs", "");
		bMap.put("passWord", "123456");
		bMap.put("ips", "");
		bMap.put("checkTr", "on,on,on,on,on,on,on,on,on");
		bMap.put("create", "on,on,on,on,on,on,on,on");
		bMap.put("update", "on,on,on,on,on,on,on,on,on");
		bMap.put("delete", "on,on,on,on,on,on,on,on,on");
		bMap.put("view", "on,on,on,on,on,on,on,on,on");
		bMap.put("review", "on,on,on,on,on,on,on,on,on");
		bMap.put("newsTypeIds", "37,38,39,");
		bMap.put("permissions", "[{\"resourceId\":\"31\",\"permissionIds\":\"1\"},{\"resourceId\":\"32\",\"permissionIds\":\"1\"},{\"resourceId\":\"39\",\"permissionIds\":\"1\"},{\"resourceId\":\"40\",\"permissionIds\":\"1\"},{\"resourceId\":\"41\",\"permissionIds\":\"1\"},{\"resourceId\":\"10\",\"permissionIds\":\"1\"},{\"resourceId\":\"49\",\"permissionIds\":\"3,4,5,6\"},{\"resourceId\":\"54\",\"permissionIds\":\"1\"},{\"resourceId\":\"58\",\"permissionIds\":\"1\"}]");
		bMap.put("id", "");
		bMap.put("regionId", "510701");*/
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("relyId", ""));
		list.add(new BasicNameValuePair("tel", "2418095"));
		list.add(new BasicNameValuePair("address", "四川省绵阳市涪城区高新区绵兴东路35号"));
		list.add(new BasicNameValuePair("longitude", "104.675077"));
		list.add(new BasicNameValuePair("latitude", "31.467744"));
		list.add(new BasicNameValuePair("mapType", "国有企业"));
		list.add(new BasicNameValuePair("mapIntro", ""));
		list.add(new BasicNameValuePair("mapTs", ""));
		list.add(new BasicNameValuePair("passWord", "123456"));
		list.add(new BasicNameValuePair("ips", ""));
		list.add(new BasicNameValuePair("checkTr", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("create", "on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("update", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("delete", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("view", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("review", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("newsTypeIds", "37,38,39,"));
		list.add(new BasicNameValuePair("permissions", "[{\"resourceId\":\"31\",\"permissionIds\":\"1\"},{\"resourceId\":\"32\",\"permissionIds\":\"1\"},{\"resourceId\":\"39\",\"permissionIds\":\"1\"},{\"resourceId\":\"40\",\"permissionIds\":\"1\"},{\"resourceId\":\"41\",\"permissionIds\":\"1\"},{\"resourceId\":\"10\",\"permissionIds\":\"1\"},{\"resourceId\":\"49\",\"permissionIds\":\"3,4,5,6\"},{\"resourceId\":\"54\",\"permissionIds\":\"1\"},{\"resourceId\":\"58\",\"permissionIds\":\"1\"}]"));
		list.add(new BasicNameValuePair("id", ""));
		list.add(new BasicNameValuePair("regionId", "510701"));
		
		final String url = "http://211.149.204.181:8080/admin/siteInfo/add.xhtml";
		String[] filePaths = {
				"C:\\Users\\Administrator\\Desktop\\t.txt"
				};
		//for (String fPath : filePaths) {
			//List<Map<String, String>> lMap = readFileLines(fPath);
			List<List<NameValuePair>> lists = genLL();
			
			//for (Map<String, String> mParam : lMap) {
			for (List<NameValuePair> list_ : lists) {
				/*Logger.INS.info("mParam is :{}", mParam.toString());
				mParam.putAll(bMap);*/
				list_.addAll(list);
				//添加请求参数
				QuickCrawlUtil.INS.addPostParam(list_);
				
				//完成请求发送，并记录返回信息
				Logger.INS.info(QuickCrawlUtil.INS.download(url));
				QuickCrawlUtil.INS.clearPostParam();
			}
		//}
	}

	private static List<NameValuePair> genPostParam(Map<String, String> mParam) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> en : mParam.entrySet()) {
			list.add(new BasicNameValuePair(en.getKey(), en.getValue()));
		}
		/*list.add(new BasicNameValuePair("relyId", ""));
		list.add(new BasicNameValuePair("tel", "2418095"));
		list.add(new BasicNameValuePair("address", "四川省绵阳市涪城区高新区绵兴东路35号"));
		list.add(new BasicNameValuePair("longitude", "104.675077"));
		list.add(new BasicNameValuePair("latitude", "31.467744"));
		list.add(new BasicNameValuePair("mapIntro", ""));
		list.add(new BasicNameValuePair("mapTs", ""));
		list.add(new BasicNameValuePair("passWord", "123456"));
		list.add(new BasicNameValuePair("ips", ""));
		list.add(new BasicNameValuePair("checkTr", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("create", "on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("update", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("delete", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("view", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("review", "on,on,on,on,on,on,on,on,on"));
		list.add(new BasicNameValuePair("newsTypeIds", "37,38,39,"));
		list.add(new BasicNameValuePair("permissions", "[{\"resourceId\":\"31\",\"permissionIds\":\"1\"},{\"resourceId\":\"32\",\"permissionIds\":\"1\"},{\"resourceId\":\"39\",\"permissionIds\":\"1\"},{\"resourceId\":\"40\",\"permissionIds\":\"1\"},{\"resourceId\":\"41\",\"permissionIds\":\"1\"},{\"resourceId\":\"10\",\"permissionIds\":\"1\"},{\"resourceId\":\"49\",\"permissionIds\":\"3,4,5,6\"},{\"resourceId\":\"54\",\"permissionIds\":\"1\"},{\"resourceId\":\"58\",\"permissionIds\":\"1\"}]"));
		list.add(new BasicNameValuePair("id", ""));
		list.add(new BasicNameValuePair("regionId", "510701"));*/
		
		return list;
	}
	
	private static List<Map<String, String>> readFileLines(String filePath) {
		List<Map<String, String>> rList = new ArrayList<Map<String, String>>();
		try {
			List<String> lines = FileUtils.readLines(new File(filePath));
			for (String fLine : lines) {
				Logger.INS.info("fLine is :{}", fLine);
				String[] lArr = fLine.split("	");
				Map<String, String> map = new HashMap<String, String>();
				map.put("pid", lArr[0]);
				map.put("siteName", lArr[1]);
				map.put("userName", lArr[2]);
				map.put("type", lArr[3]);
				rList.add(map);
			}
		} catch (IOException e) {
			Logger.INS.error("{}", e);
		}
		
		return rList;
	}
	
	private static List<List<NameValuePair>> genLL(){
		List<List<NameValuePair>> nList = new ArrayList<List<NameValuePair>>();
		
		/*List<NameValuePair> list61 = new ArrayList<NameValuePair>();
		list61.add(new BasicNameValuePair("pid", "1116"));
		list61.add(new BasicNameValuePair("siteName", "综合党支部"));
		list61.add(new BasicNameValuePair("userName", "CH61"));
		list61.add(new BasicNameValuePair("type", "支部"));
		nList.add(list61);
		
		List<NameValuePair> list28 = new ArrayList<NameValuePair>();
		list28.add(new BasicNameValuePair("pid", "1103"));
		list28.add(new BasicNameValuePair("siteName", "技术中心党支部"));
		list28.add(new BasicNameValuePair("userName", "CH28"));
		list28.add(new BasicNameValuePair("type", "支部"));
		nList.add(list28);
		
		List<NameValuePair> list15 = new ArrayList<NameValuePair>();	
		list15.add(new BasicNameValuePair("pid", "1100"));
		list15.add(new BasicNameValuePair("siteName", "机关党支部"));
		list15.add(new BasicNameValuePair("userName", "CH15"));
		list15.add(new BasicNameValuePair("type", "支部"));
		nList.add(list15);*/
		
		/*List<NameValuePair> list03 = new ArrayList<NameValuePair>();
		list03.add(new BasicNameValuePair("pid", "1099"));
		list03.add(new BasicNameValuePair("siteName", "财务运营党支部"));
		list03.add(new BasicNameValuePair("userName", "CH03"));
		list03.add(new BasicNameValuePair("type", "支部"));
		nList.add(list03);*/
		
/*		List<NameValuePair> list04 = new ArrayList<NameValuePair>();
		list04.add(new BasicNameValuePair("pid", "1099"));
		list04.add(new BasicNameValuePair("siteName", "制造与供应链管理党支部"));
		list04.add(new BasicNameValuePair("userName", "CH04"));
		list04.add(new BasicNameValuePair("type", "支部"));
		nList.add(list04);
		
		List<NameValuePair> list05 = new ArrayList<NameValuePair>();
		list05.add(new BasicNameValuePair("pid", "1099"));
		list05.add(new BasicNameValuePair("siteName", "产品策划中心党支部"));
		list05.add(new BasicNameValuePair("userName", "CH05"));
		list05.add(new BasicNameValuePair("type", "支部"));
		nList.add(list05);
		
		List<NameValuePair> list06 = new ArrayList<NameValuePair>();
		list06.add(new BasicNameValuePair("pid", "1099"));
		list06.add(new BasicNameValuePair("siteName", "品质中心党支部"));
		list06.add(new BasicNameValuePair("userName", "CH06"));
		list06.add(new BasicNameValuePair("type", "支部"));
		nList.add(list06);
		
		List<NameValuePair> list07 = new ArrayList<NameValuePair>();
		list07.add(new BasicNameValuePair("pid", "1099"));
		list07.add(new BasicNameValuePair("siteName", "平板显示技术所党支部"));
		list07.add(new BasicNameValuePair("userName", "CH07"));
		list07.add(new BasicNameValuePair("type", "支部"));
		nList.add(list07);
		
		List<NameValuePair> list08 = new ArrayList<NameValuePair>();
		list08.add(new BasicNameValuePair("pid", "1099"));
		list08.add(new BasicNameValuePair("siteName", "结构技术所党支部"));
		list08.add(new BasicNameValuePair("userName", "CH08"));
		list08.add(new BasicNameValuePair("type", "支部"));
		nList.add(list08);
		
		List<NameValuePair> list09 = new ArrayList<NameValuePair>();
		list09.add(new BasicNameValuePair("pid", "1099"));
		list09.add(new BasicNameValuePair("siteName", "绵阳制造分部党支部"));
		list09.add(new BasicNameValuePair("userName", "CH09"));
		list09.add(new BasicNameValuePair("type", "支部"));
		nList.add(list09);
		
		List<NameValuePair> list11 = new ArrayList<NameValuePair>();
		list11.add(new BasicNameValuePair("pid", "1100"));
		list11.add(new BasicNameValuePair("siteName", "仓储中心党支部"));
		list11.add(new BasicNameValuePair("userName", "CH11"));
		list11.add(new BasicNameValuePair("type", "支部"));
		nList.add(list11);
		
		List<NameValuePair> list12 = new ArrayList<NameValuePair>();
		list12.add(new BasicNameValuePair("pid", "1100"));
		list12.add(new BasicNameValuePair("siteName", "成型中心党支部"));
		list12.add(new BasicNameValuePair("userName", "CH12"));
		list12.add(new BasicNameValuePair("type", "支部"));
		nList.add(list12);
		
		List<NameValuePair> list13 = new ArrayList<NameValuePair>();
		list13.add(new BasicNameValuePair("pid", "1100"));
		list13.add(new BasicNameValuePair("siteName", "电镀厂党支部"));
		list13.add(new BasicNameValuePair("userName", "CH13"));
		list13.add(new BasicNameValuePair("type", "支部"));
		nList.add(list13);
		
		List<NameValuePair> list14 = new ArrayList<NameValuePair>();
		list14.add(new BasicNameValuePair("pid", "1100"));
		list14.add(new BasicNameValuePair("siteName", "工业事业部党支部"));
		list14.add(new BasicNameValuePair("userName", "CH14"));
		list14.add(new BasicNameValuePair("type", "支部"));
		nList.add(list14);
		
		List<NameValuePair> list15 = new ArrayList<NameValuePair>();
		list15.add(new BasicNameValuePair("pid", "1100"));
		list15.add(new BasicNameValuePair("siteName", "机关党支部"));
		list15.add(new BasicNameValuePair("userName", "CH15"));
		list15.add(new BasicNameValuePair("type", "支部"));
		nList.add(list15);
		
		List<NameValuePair> list16 = new ArrayList<NameValuePair>();
		list16.add(new BasicNameValuePair("pid", "1100"));
		list16.add(new BasicNameValuePair("siteName", "技术党支部"));
		list16.add(new BasicNameValuePair("userName", "CH16"));
		list16.add(new BasicNameValuePair("type", "支部"));
		nList.add(list16);
		
		List<NameValuePair> list17 = new ArrayList<NameValuePair>();
		list17.add(new BasicNameValuePair("pid", "1100"));
		list17.add(new BasicNameValuePair("siteName", "接触对厂党支部"));
		list17.add(new BasicNameValuePair("userName", "CH17"));
		list17.add(new BasicNameValuePair("type", "支部"));
		nList.add(list17);
		
		List<NameValuePair> list18 = new ArrayList<NameValuePair>();
		list18.add(new BasicNameValuePair("pid", "1100"));
		list18.add(new BasicNameValuePair("siteName", "军工事业部党支部"));
		list18.add(new BasicNameValuePair("userName", "CH18"));
		list18.add(new BasicNameValuePair("type", "支部"));
		nList.add(list18);
		
		List<NameValuePair> list19 = new ArrayList<NameValuePair>();
		list19.add(new BasicNameValuePair("pid", "1100"));
		list19.add(new BasicNameValuePair("siteName", "壳体生产厂党支部"));
		list19.add(new BasicNameValuePair("userName", "CH19"));
		list19.add(new BasicNameValuePair("type", "支部"));
		nList.add(list19);
		
		List<NameValuePair> list20 = new ArrayList<NameValuePair>();
		list20.add(new BasicNameValuePair("pid", "1100"));
		list20.add(new BasicNameValuePair("siteName", "零部件综合管理处党支部"));
		list20.add(new BasicNameValuePair("userName", "CH20"));
		list20.add(new BasicNameValuePair("type", "支部"));
		nList.add(list20);
		
		List<NameValuePair> list21 = new ArrayList<NameValuePair>();
		list21.add(new BasicNameValuePair("pid", "1100"));
		list21.add(new BasicNameValuePair("siteName", "通讯事业部党支部"));
		list21.add(new BasicNameValuePair("userName", "CH21"));
		list21.add(new BasicNameValuePair("type", "支部"));
		nList.add(list21);
		
		List<NameValuePair> list22 = new ArrayList<NameValuePair>();
		list22.add(new BasicNameValuePair("pid", "1100"));
		list22.add(new BasicNameValuePair("siteName", "营销物资党支部"));
		list22.add(new BasicNameValuePair("userName", "CH22"));
		list22.add(new BasicNameValuePair("type", "支部"));
		nList.add(list22);
		
		List<NameValuePair> list23 = new ArrayList<NameValuePair>();
		list23.add(new BasicNameValuePair("pid", "1100"));
		list23.add(new BasicNameValuePair("siteName", "运营人力财务党支部"));
		list23.add(new BasicNameValuePair("userName", "CH23"));
		list23.add(new BasicNameValuePair("type", "支部"));
		nList.add(list23);
		
		List<NameValuePair> list24 = new ArrayList<NameValuePair>();
		list24.add(new BasicNameValuePair("pid", "1100"));
		list24.add(new BasicNameValuePair("siteName", "质量部党支部"));
		list24.add(new BasicNameValuePair("userName", "CH24"));
		list24.add(new BasicNameValuePair("type", "支部"));
		nList.add(list24);
		
		List<NameValuePair> list25 = new ArrayList<NameValuePair>();
		list25.add(new BasicNameValuePair("pid", "1103"));
		list25.add(new BasicNameValuePair("siteName", "机关一党支部"));
		list25.add(new BasicNameValuePair("userName", "CH25"));
		list25.add(new BasicNameValuePair("type", "支部"));
		nList.add(list25);
		
		List<NameValuePair> list26 = new ArrayList<NameValuePair>();
		list26.add(new BasicNameValuePair("pid", "1103"));
		list26.add(new BasicNameValuePair("siteName", "机关二党支部"));
		list26.add(new BasicNameValuePair("userName", "CH26"));
		list26.add(new BasicNameValuePair("type", "支部"));
		nList.add(list26);
		
		List<NameValuePair> list27 = new ArrayList<NameValuePair>();
		list27.add(new BasicNameValuePair("pid", "1103"));
		list27.add(new BasicNameValuePair("siteName", "机关三党支部"));
		list27.add(new BasicNameValuePair("userName", "CH27"));
		list27.add(new BasicNameValuePair("type", "支部"));
		nList.add(list27);
		
		List<NameValuePair> list28 = new ArrayList<NameValuePair>();
		list28.add(new BasicNameValuePair("pid", "1103"));
		list28.add(new BasicNameValuePair("siteName", "技术中心党支部"));
		list28.add(new BasicNameValuePair("userName", "CH28"));
		list28.add(new BasicNameValuePair("type", "支部"));
		nList.add(list28);
		
		List<NameValuePair> list29 = new ArrayList<NameValuePair>();
		list29.add(new BasicNameValuePair("pid", "1103"));
		list29.add(new BasicNameValuePair("siteName", "机电、物流党支部"));
		list29.add(new BasicNameValuePair("userName", "CH29"));
		list29.add(new BasicNameValuePair("type", "支部"));
		nList.add(list29);
		
		List<NameValuePair> list30 = new ArrayList<NameValuePair>();
		list30.add(new BasicNameValuePair("pid", "1103"));
		list30.add(new BasicNameValuePair("siteName", "电池事业一部党支部"));
		list30.add(new BasicNameValuePair("userName", "CH30"));
		list30.add(new BasicNameValuePair("type", "支部"));
		nList.add(list30);
		
		List<NameValuePair> list31 = new ArrayList<NameValuePair>();
		list31.add(new BasicNameValuePair("pid", "1103"));
		list31.add(new BasicNameValuePair("siteName", "锂电本部党支部"));
		list31.add(new BasicNameValuePair("userName", "CH31"));
		list31.add(new BasicNameValuePair("type", "支部"));
		nList.add(list31);		
		
		List<NameValuePair> list32 = new ArrayList<NameValuePair>();
		list32.add(new BasicNameValuePair("pid", "1103"));
		list32.add(new BasicNameValuePair("siteName", "电源事业党支部"));
		list32.add(new BasicNameValuePair("userName", "CH32"));
		list32.add(new BasicNameValuePair("type", "支部"));
		nList.add(list32);
		
		List<NameValuePair> list33 = new ArrayList<NameValuePair>();
		list33.add(new BasicNameValuePair("pid", "1103"));
		list33.add(new BasicNameValuePair("siteName", "物业公司党支部"));
		list33.add(new BasicNameValuePair("userName", "CH33"));
		list33.add(new BasicNameValuePair("type", "支部"));
		nList.add(list33);
		
		List<NameValuePair> list34 = new ArrayList<NameValuePair>();
		list34.add(new BasicNameValuePair("pid", "1111"));
		list34.add(new BasicNameValuePair("siteName", "电视产品部党支部"));
		list34.add(new BasicNameValuePair("userName", "CH34"));
		list34.add(new BasicNameValuePair("type", "支部"));
		nList.add(list34);
		
		List<NameValuePair> list35 = new ArrayList<NameValuePair>();
		list35.add(new BasicNameValuePair("pid", "1111"));
		list35.add(new BasicNameValuePair("siteName", "云服务部绵阳党支部"));
		list35.add(new BasicNameValuePair("userName", "CH35"));
		list35.add(new BasicNameValuePair("type", "支部"));
		nList.add(list35);
		
		List<NameValuePair> list36 = new ArrayList<NameValuePair>();
		list36.add(new BasicNameValuePair("pid", "1111"));
		list36.add(new BasicNameValuePair("siteName", "云服务部成都党支部"));
		list36.add(new BasicNameValuePair("userName", "CH36"));
		list36.add(new BasicNameValuePair("type", "支部"));
		nList.add(list36);
		
		List<NameValuePair> list37 = new ArrayList<NameValuePair>();
		list37.add(new BasicNameValuePair("pid", "1111"));
		list37.add(new BasicNameValuePair("siteName", "数据服务部党支部"));
		list37.add(new BasicNameValuePair("userName", "CH37"));
		list37.add(new BasicNameValuePair("type", "支部"));
		nList.add(list37);
		
		List<NameValuePair> list38 = new ArrayList<NameValuePair>();
		list38.add(new BasicNameValuePair("pid", "1111"));
		list38.add(new BasicNameValuePair("siteName", "综合管理绵阳党支部"));
		list38.add(new BasicNameValuePair("userName", "CH38"));
		list38.add(new BasicNameValuePair("type", "支部"));
		nList.add(list38);
		
		List<NameValuePair> list39 = new ArrayList<NameValuePair>();
		list39.add(new BasicNameValuePair("pid", "1111"));
		list39.add(new BasicNameValuePair("siteName", "综合管理成都党支部"));
		list39.add(new BasicNameValuePair("userName", "CH39"));
		list39.add(new BasicNameValuePair("type", "支部"));
		nList.add(list39);
		
		List<NameValuePair> list40 = new ArrayList<NameValuePair>();
		list40.add(new BasicNameValuePair("pid", "1112"));
		list40.add(new BasicNameValuePair("siteName", "营销党支部"));
		list40.add(new BasicNameValuePair("userName", "CH40"));
		list40.add(new BasicNameValuePair("type", "支部"));
		nList.add(list40);
		
		List<NameValuePair> list41 = new ArrayList<NameValuePair>();
		list41.add(new BasicNameValuePair("pid", "1112"));
		list41.add(new BasicNameValuePair("siteName", "中央空调党支部"));
		list41.add(new BasicNameValuePair("userName", "CH41"));
		list41.add(new BasicNameValuePair("type", "支部"));
		nList.add(list41);
		
		List<NameValuePair> list42 = new ArrayList<NameValuePair>();
		list42.add(new BasicNameValuePair("pid", "1112"));
		list42.add(new BasicNameValuePair("siteName", "管理研发党支部"));
		list42.add(new BasicNameValuePair("userName", "CH42"));
		list42.add(new BasicNameValuePair("type", "支部"));
		nList.add(list42);
		
		List<NameValuePair> list43 = new ArrayList<NameValuePair>();
		list43.add(new BasicNameValuePair("pid", "1112"));
		list43.add(new BasicNameValuePair("siteName", "制造系统党支部"));
		list43.add(new BasicNameValuePair("userName", "CH43"));
		list43.add(new BasicNameValuePair("type", "支部"));
		nList.add(list43);
		
		List<NameValuePair> list44 = new ArrayList<NameValuePair>();
		list44.add(new BasicNameValuePair("pid", "1112"));
		list44.add(new BasicNameValuePair("siteName", "绵阳美菱党支部"));
		list44.add(new BasicNameValuePair("userName", "CH44"));
		list44.add(new BasicNameValuePair("type", "支部"));
		nList.add(list44);
		
		List<NameValuePair> list45 = new ArrayList<NameValuePair>();
		list45.add(new BasicNameValuePair("pid", "1113"));
		list45.add(new BasicNameValuePair("siteName", "行政党支部"));
		list45.add(new BasicNameValuePair("userName", "CH45"));
		list45.add(new BasicNameValuePair("type", "支部"));
		nList.add(list45);
		
		List<NameValuePair> list46 = new ArrayList<NameValuePair>();
		list46.add(new BasicNameValuePair("pid", "1113"));
		list46.add(new BasicNameValuePair("siteName", "虹锐党支部"));
		list46.add(new BasicNameValuePair("userName", "CH46"));
		list46.add(new BasicNameValuePair("type", "支部"));
		nList.add(list46);
		
		List<NameValuePair> list47 = new ArrayList<NameValuePair>();
		list47.add(new BasicNameValuePair("pid", "1113"));
		list47.add(new BasicNameValuePair("siteName", "遥控器党支部"));
		list47.add(new BasicNameValuePair("userName", "CH47"));
		list47.add(new BasicNameValuePair("type", "支部"));
		nList.add(list47);
		
		List<NameValuePair> list48 = new ArrayList<NameValuePair>();
		list48.add(new BasicNameValuePair("pid", "1113"));
		list48.add(new BasicNameValuePair("siteName", "印制板党支部"));
		list48.add(new BasicNameValuePair("userName", "CH48"));
		list48.add(new BasicNameValuePair("type", "支部"));
		nList.add(list48);
		
		List<NameValuePair> list49 = new ArrayList<NameValuePair>();
		list49.add(new BasicNameValuePair("pid", "1113"));
		list49.add(new BasicNameValuePair("siteName", "部品党支部"));
		list49.add(new BasicNameValuePair("userName", "CH49"));
		list49.add(new BasicNameValuePair("type", "支部"));
		nList.add(list49);
		
		List<NameValuePair> list50 = new ArrayList<NameValuePair>();
		list50.add(new BasicNameValuePair("pid", "1113"));
		list50.add(new BasicNameValuePair("siteName", "研究所党支部"));
		list50.add(new BasicNameValuePair("userName", "CH50"));
		list50.add(new BasicNameValuePair("type", "支部"));
		nList.add(list50);
		
		List<NameValuePair> list51 = new ArrayList<NameValuePair>();
		list51.add(new BasicNameValuePair("pid", "1113"));
		list51.add(new BasicNameValuePair("siteName", "照明电工党支部"));
		list51.add(new BasicNameValuePair("userName", "CH51"));
		list51.add(new BasicNameValuePair("type", "支部"));
		nList.add(list51);
		
		List<NameValuePair> list52 = new ArrayList<NameValuePair>();
		list52.add(new BasicNameValuePair("pid", "1114"));
		list52.add(new BasicNameValuePair("siteName", "运营党支部"));
		list52.add(new BasicNameValuePair("userName", "CH52"));
		list52.add(new BasicNameValuePair("type", "支部"));
		nList.add(list52);
		
		List<NameValuePair> list53 = new ArrayList<NameValuePair>();
		list53.add(new BasicNameValuePair("pid", "1114"));
		list53.add(new BasicNameValuePair("siteName", "模具公司党支部"));
		list53.add(new BasicNameValuePair("userName", "CH53"));
		list53.add(new BasicNameValuePair("type", "支部"));
		nList.add(list53);
		
		List<NameValuePair> list54 = new ArrayList<NameValuePair>();
		list54.add(new BasicNameValuePair("pid", "1114"));
		list54.add(new BasicNameValuePair("siteName", "小件事业部党支部"));
		list54.add(new BasicNameValuePair("userName", "CH54"));
		list54.add(new BasicNameValuePair("type", "支部"));
		nList.add(list54);
		
		List<NameValuePair> list55 = new ArrayList<NameValuePair>();
		list55.add(new BasicNameValuePair("pid", "1114"));
		list55.add(new BasicNameValuePair("siteName", "塑料二厂党支部"));
		list55.add(new BasicNameValuePair("userName", "CH55"));
		list55.add(new BasicNameValuePair("type", "支部"));
		nList.add(list55);
		
		List<NameValuePair> list56 = new ArrayList<NameValuePair>();
		list56.add(new BasicNameValuePair("pid", "1114"));
		list56.add(new BasicNameValuePair("siteName", "塑料四厂党支部"));
		list56.add(new BasicNameValuePair("userName", "CH56"));
		list56.add(new BasicNameValuePair("type", "支部"));
		nList.add(list56);
		
		List<NameValuePair> list57 = new ArrayList<NameValuePair>();
		list57.add(new BasicNameValuePair("pid", "1115"));
		list57.add(new BasicNameValuePair("siteName", "运营管理处党支部"));
		list57.add(new BasicNameValuePair("userName", "CH57"));
		list57.add(new BasicNameValuePair("type", "支部"));
		nList.add(list57);
		
		List<NameValuePair> list58 = new ArrayList<NameValuePair>();
		list58.add(new BasicNameValuePair("pid", "1115"));
		list58.add(new BasicNameValuePair("siteName", "冲压件厂党支部"));
		list58.add(new BasicNameValuePair("userName", "CH58"));
		list58.add(new BasicNameValuePair("type", "支部"));
		nList.add(list58);
		
		List<NameValuePair> list59 = new ArrayList<NameValuePair>();
		list59.add(new BasicNameValuePair("pid", "1115"));
		list59.add(new BasicNameValuePair("siteName", "压铸件厂党支部"));
		list59.add(new BasicNameValuePair("userName", "CH59"));
		list59.add(new BasicNameValuePair("type", "支部"));
		nList.add(list59);
		
		List<NameValuePair> list60 = new ArrayList<NameValuePair>();
		list60.add(new BasicNameValuePair("pid", "1115"));
		list60.add(new BasicNameValuePair("siteName", "电镀装饰公司党支部"));
		list60.add(new BasicNameValuePair("userName", "CH60"));
		list60.add(new BasicNameValuePair("type", "支部"));
		nList.add(list60);
		
		List<NameValuePair> list61 = new ArrayList<NameValuePair>();
		list61.add(new BasicNameValuePair("pid", "1116"));
		list61.add(new BasicNameValuePair("siteName", "综合党支部"));
		list61.add(new BasicNameValuePair("userName", "CH61"));
		list61.add(new BasicNameValuePair("type", "支部"));
		nList.add(list61);
		
		List<NameValuePair> list62 = new ArrayList<NameValuePair>();
		list62.add(new BasicNameValuePair("pid", "1116"));
		list62.add(new BasicNameValuePair("siteName", "绵阳公司党支部"));
		list62.add(new BasicNameValuePair("userName", "CH62"));
		list62.add(new BasicNameValuePair("type", "支部"));
		nList.add(list62);
		
		List<NameValuePair> list63 = new ArrayList<NameValuePair>();
		list63.add(new BasicNameValuePair("pid", "1117"));
		list63.add(new BasicNameValuePair("siteName", "绵阳物业分公司党支部"));
		list63.add(new BasicNameValuePair("userName", "CH63"));
		list63.add(new BasicNameValuePair("type", "支部"));
		nList.add(list63);
		
		List<NameValuePair> list64 = new ArrayList<NameValuePair>();
		list64.add(new BasicNameValuePair("pid", "1117"));
		list64.add(new BasicNameValuePair("siteName", "佳虹公司党支部"));
		list64.add(new BasicNameValuePair("userName", "CH64"));
		list64.add(new BasicNameValuePair("type", "支部"));
		nList.add(list64);
		
		List<NameValuePair> list65 = new ArrayList<NameValuePair>();
		list65.add(new BasicNameValuePair("pid", "1118"));
		list65.add(new BasicNameValuePair("siteName", "长虹老干部党支部"));
		list65.add(new BasicNameValuePair("userName", "CH65"));
		list65.add(new BasicNameValuePair("type", "支部"));
		nList.add(list65);
		
		List<NameValuePair> list66 = new ArrayList<NameValuePair>();
		list66.add(new BasicNameValuePair("pid", "1118"));
		list66.add(new BasicNameValuePair("siteName", "长虹退休一党支部"));
		list66.add(new BasicNameValuePair("userName", "CH66"));
		list66.add(new BasicNameValuePair("type", "支部"));
		nList.add(list66);
		
		List<NameValuePair> list67 = new ArrayList<NameValuePair>();
		list67.add(new BasicNameValuePair("pid", "1118"));
		list67.add(new BasicNameValuePair("siteName", "长虹退休二党支部"));
		list67.add(new BasicNameValuePair("userName", "CH67"));
		list67.add(new BasicNameValuePair("type", "支部"));
		nList.add(list67);
		
		List<NameValuePair> list68 = new ArrayList<NameValuePair>();
		list68.add(new BasicNameValuePair("pid", "1118"));
		list68.add(new BasicNameValuePair("siteName", "长虹退休三党支部"));
		list68.add(new BasicNameValuePair("userName", "CH68"));
		list68.add(new BasicNameValuePair("type", "支部"));
		nList.add(list68);
		
		List<NameValuePair> list69 = new ArrayList<NameValuePair>();
		list69.add(new BasicNameValuePair("pid", "1118"));
		list69.add(new BasicNameValuePair("siteName", "长虹退休四党支部"));
		list69.add(new BasicNameValuePair("userName", "CH69"));
		list69.add(new BasicNameValuePair("type", "支部"));
		nList.add(list69);
		
		List<NameValuePair> list70 = new ArrayList<NameValuePair>();
		list70.add(new BasicNameValuePair("pid", "1118"));
		list70.add(new BasicNameValuePair("siteName", "华丰老干部党支部"));
		list70.add(new BasicNameValuePair("userName", "CH70"));
		list70.add(new BasicNameValuePair("type", "支部"));
		nList.add(list70);
		
		List<NameValuePair> list71 = new ArrayList<NameValuePair>();
		list71.add(new BasicNameValuePair("pid", "1118"));
		list71.add(new BasicNameValuePair("siteName", "华丰退休一党支部"));
		list71.add(new BasicNameValuePair("userName", "CH71"));
		list71.add(new BasicNameValuePair("type", "支部"));
		nList.add(list71);
		
		List<NameValuePair> list72 = new ArrayList<NameValuePair>();
		list72.add(new BasicNameValuePair("pid", "1118"));
		list72.add(new BasicNameValuePair("siteName", "华丰退休二党支部"));
		list72.add(new BasicNameValuePair("userName", "CH72"));
		list72.add(new BasicNameValuePair("type", "支部"));
		nList.add(list72);
		
		List<NameValuePair> list73 = new ArrayList<NameValuePair>();
		list73.add(new BasicNameValuePair("pid", "1118"));
		list73.add(new BasicNameValuePair("siteName", "华丰退休三党支部"));
		list73.add(new BasicNameValuePair("userName", "CH73"));
		list73.add(new BasicNameValuePair("type", "支部"));
		nList.add(list73);
		
		List<NameValuePair> list74 = new ArrayList<NameValuePair>();
		list74.add(new BasicNameValuePair("pid", "1118"));
		list74.add(new BasicNameValuePair("siteName", "华丰退休四党支部"));
		list74.add(new BasicNameValuePair("userName", "CH74"));
		list74.add(new BasicNameValuePair("type", "支部"));
		nList.add(list74);*/
		
		/*List<NameValuePair> list0101 = new ArrayList<NameValuePair>();
		list0101.add(new BasicNameValuePair("pid", "3540"));
		list0101.add(new BasicNameValuePair("siteName", "华北党支部"));
		list0101.add(new BasicNameValuePair("userName", "CH0101"));
		list0101.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0101);
		
		List<NameValuePair> list0102 = new ArrayList<NameValuePair>();
		list0102.add(new BasicNameValuePair("pid", "3540"));
		list0102.add(new BasicNameValuePair("siteName", "东北党支部"));
		list0102.add(new BasicNameValuePair("userName", "CH0102"));
		list0102.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0102);
		
		List<NameValuePair> list0103 = new ArrayList<NameValuePair>();
		list0103.add(new BasicNameValuePair("pid", "3540"));
		list0103.add(new BasicNameValuePair("siteName", "闽赣党支部"));
		list0103.add(new BasicNameValuePair("userName", "CH0103"));
		list0103.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0103);
		
		List<NameValuePair> list0104 = new ArrayList<NameValuePair>();
		list0104.add(new BasicNameValuePair("pid", "3540"));
		list0104.add(new BasicNameValuePair("siteName", "川南党支部"));
		list0104.add(new BasicNameValuePair("userName", "CH0104"));
		list0104.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0104);
		
		List<NameValuePair> list0105 = new ArrayList<NameValuePair>();
		list0105.add(new BasicNameValuePair("pid", "3540"));
		list0105.add(new BasicNameValuePair("siteName", "云桂黔党支部"));
		list0105.add(new BasicNameValuePair("userName", "CH0105"));
		list0105.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0105);
		
		List<NameValuePair> list0106 = new ArrayList<NameValuePair>();
		list0106.add(new BasicNameValuePair("pid", "3540"));
		list0106.add(new BasicNameValuePair("siteName", "广东党支部"));
		list0106.add(new BasicNameValuePair("userName", "CH0106"));
		list0106.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0106);
		
		List<NameValuePair> list0107 = new ArrayList<NameValuePair>();
		list0107.add(new BasicNameValuePair("pid", "3540"));
		list0107.add(new BasicNameValuePair("siteName", "沪浙党支部"));
		list0107.add(new BasicNameValuePair("userName", "CH0107"));
		list0107.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0107);
		
		List<NameValuePair> list0108 = new ArrayList<NameValuePair>();
		list0108.add(new BasicNameValuePair("pid", "3540"));
		list0108.add(new BasicNameValuePair("siteName", "江苏党支部"));
		list0108.add(new BasicNameValuePair("userName", "CH0108"));
		list0108.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0108);
		
		List<NameValuePair> list0109 = new ArrayList<NameValuePair>();
		list0109.add(new BasicNameValuePair("pid", "3540"));
		list0109.add(new BasicNameValuePair("siteName", "湘鄂党支部"));
		list0109.add(new BasicNameValuePair("userName", "CH0109"));
		list0109.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0109);
		
		List<NameValuePair> list0110 = new ArrayList<NameValuePair>();
		list0110.add(new BasicNameValuePair("pid", "3540"));
		list0110.add(new BasicNameValuePair("siteName", "山东党支部"));
		list0110.add(new BasicNameValuePair("userName", "CH0110"));
		list0110.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0110);
		
		List<NameValuePair> list0111 = new ArrayList<NameValuePair>();
		list0111.add(new BasicNameValuePair("pid", "3540"));
		list0111.add(new BasicNameValuePair("siteName", "川北党支部"));
		list0111.add(new BasicNameValuePair("userName", "CH0111"));
		list0111.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0111);
		
		List<NameValuePair> list0112 = new ArrayList<NameValuePair>();
		list0112.add(new BasicNameValuePair("pid", "3540"));
		list0112.add(new BasicNameValuePair("siteName", "重庆党支部"));
		list0112.add(new BasicNameValuePair("userName", "CH0112"));
		list0112.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0112);
		
		List<NameValuePair> list0113 = new ArrayList<NameValuePair>();
		list0113.add(new BasicNameValuePair("pid", "3540"));
		list0113.add(new BasicNameValuePair("siteName", "豫皖党支部"));
		list0113.add(new BasicNameValuePair("userName", "CH0113"));
		list0113.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0113);
		
		List<NameValuePair> list0114 = new ArrayList<NameValuePair>();
		list0114.add(new BasicNameValuePair("pid", "3540"));
		list0114.add(new BasicNameValuePair("siteName", "西北党支部"));
		list0114.add(new BasicNameValuePair("userName", "CH0114"));
		list0114.add(new BasicNameValuePair("type", "支部"));
		nList.add(list0114);*/
		
		return nList;
	}
}