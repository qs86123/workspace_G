package com.myself.edu.movedata.compare.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.compare.bean.Account;
import com.myself.edu.movedata.compare.bean.Cash;
import com.myself.edu.utils.Logger;
import com.myself.edu.utils.QuickCrawlUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class CHAppAllPaid {

	public static List<Cash> getCashList(String jsessionid, Account account) {
		List<Cash> cashs = new ArrayList<>();
		//设置基本信息
		QuickCrawlUtil.INS.setRequestMethod("POST")
				.setProxySiteName("CHAPP")
				.addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
				.addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.addExtraHeader("Cookie", jsessionid)
				.addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
				.addExtraHeader("X-Requested-With", "XMLHttpRequest")
		;

		String page = "1";

		final String url = "http://mzxfweb.my.gov.cn/paid/allPaid.xhtml";
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("pYear", "2017"));
		list.add(new BasicNameValuePair("_search", "false"));
		list.add(new BasicNameValuePair("nd", ""));
		list.add(new BasicNameValuePair("rows", "1000"));
		list.add(new BasicNameValuePair("page", page));
		list.add(new BasicNameValuePair("sidx", "ord"));
		list.add(new BasicNameValuePair("sord", "asc"));

		//添加请求参数
		QuickCrawlUtil.INS.addPostParam(list);

		//完成请求发送，并记录返回信息
		JSONObject object = JSON.parseObject(QuickCrawlUtil.INS.download(url));
		QuickCrawlUtil.INS.clearPostParam();
		if(object==null){
			System.out.println("allpaid is null");
			return new ArrayList<>();
		}
		JSONArray rows = object.getJSONArray("rows");
		for(int i=0; i < rows.size(); i++){
			JSONObject row = rows.getJSONObject(i);
			Cash cash = new Cash();
			cash.setUsername(String.valueOf(row.get("name")));
			cash.setZzjg(account.getZzjg());
			JSONArray paids = row.getJSONArray("paids");
			for(int j = 0; j<paids.size();j++){
				JSONObject paid = paids.getJSONObject(j);
				String m = String.valueOf(paid.get("pMonth"));
				switch (m){
					case "1":
						cash.setMonth1(paid.getString("cash"));
						break;
					case "2":
						cash.setMonth2(paid.getString("cash"));
						break;
					case "3":
						cash.setMonth3(paid.getString("cash"));
						break;
					case "4":
						cash.setMonth4(paid.getString("cash"));
						break;
					case "5":
						cash.setMonth5(paid.getString("cash"));
						break;
					case "6":
						cash.setMonth6(paid.getString("cash"));
						break;
					case "7":
						cash.setMonth7(paid.getString("cash"));
						break;
					case "8":
						cash.setMonth8(paid.getString("cash"));
						break;
					case "9":
						cash.setMonth9(paid.getString("cash"));
						break;
					case "10":
						cash.setMonth10(paid.getString("cash"));
						break;
					case "11":
						cash.setMonth11(paid.getString("cash"));
						break;
					case "12":
						cash.setMonth12(paid.getString("cash"));
						break;
				}

			}
			cashs.add(cash);
		}
		return cashs;
	}
}