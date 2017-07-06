package com.myself.edu.movedata.compare.util;

import com.myself.edu.movedata.compare.bean.Account;
import com.myself.edu.utils.Logger;
import com.myself.edu.utils.QuickCrawlUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Login {

//	public static void login(Account account) {
//		//设置基本信息
//		QuickCrawlUtil.INS.setRequestMethod("POST")
//		.setProxySiteName("CHAPP")
//		.addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
//		.addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//		.addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
//		.addExtraHeader("X-Requested-With", "XMLHttpRequest")
//		;
//
//
//		final String url = "http://mzxfweb.my.gov.cn/loginByAdmin.xhtml";
//		List<NameValuePair> list = new ArrayList<NameValuePair>();
//		list.add(new BasicNameValuePair("userName", account.getUsername()));
//		list.add(new BasicNameValuePair("passWord", "123456"));
//
//		//添加请求参数
//		QuickCrawlUtil.INS.addPostParam(list);
//
//		//完成请求发送，并记录返回信息
//		Logger.INS.info(QuickCrawlUtil.INS.login(url, null, null));
//		QuickCrawlUtil.INS.clearPostParam();
//		list.clear();
//	}

	public static String login(Account account) {
		//设置基本信息
		QuickCrawlUtil.INS.setRequestMethod("POST")
				.setProxySiteName("CHAPP")
				.addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
				.addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
				.addExtraHeader("X-Requested-With", "XMLHttpRequest")
				.removeExtraHeader("Cookie");
		final String url = "http://mzxfweb.my.gov.cn/loginByAdmin.xhtml";
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("userName", account.getUsername()));
		list.add(new BasicNameValuePair("passWord", account.getPassword()));
		//添加请求参数
		QuickCrawlUtil.INS.addPostParam(list);
		String jsessionid = QuickCrawlUtil.INS.login(url, null, null);
		System.out.println("JsessionId=" + jsessionid);
		//完成请求发送，并记录返回信息
		QuickCrawlUtil.INS.clearPostParam();
		list.clear();
		return jsessionid;
	}



}