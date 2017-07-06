package com.myself.edu.movedata;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.myself.edu.utils.Logger;
import com.myself.edu.utils.QuickCrawlUtil;

public class CHAppList2 {

    public static void main(String[] args) {
        //设置基本信息
        QuickCrawlUtil.INS.setRequestMethod("POST")
                .setProxySiteName("CHAPP")
                .addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
//		.addExtraHeader("Accept-Encoding", "gzip, deflate")
//		.addExtraHeader("Accept-Language", "zh-CN,zh;q=0.8")
//		.addExtraHeader("Connection", "keep-alive")
                .addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addExtraHeader("Cookie", "JSESSIONID=01436516354A87F083EC8BA93C72D5A4")
//		.addExtraHeader("Host", "211.149.204.181:8090")
//		.addExtraHeader("Origin", "http://211.149.204.181:8090")
//		.addExtraHeader("Referer", "http://211.149.204.181:8090/admin/index.xhtml")
                .addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .addExtraHeader("X-Requested-With", "XMLHttpRequest")
        ;


        final String url = "http://mzxfweb.my.gov.cn/user/list2.xhtml";
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("type", "党员"));
        //添加请求参数
        QuickCrawlUtil.INS.addPostParam(list);

        //完成请求发送，并记录返回信息
        Logger.INS.info(QuickCrawlUtil.INS.download(url));
        QuickCrawlUtil.INS.clearPostParam();
    }

    public static String List2(String jsessionId) {
        //设置基本信息
        QuickCrawlUtil.INS.setRequestMethod("POST")
                .setProxySiteName("CHAPP")
                .addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addExtraHeader("Cookie", "JSESSIONID=" + jsessionId)
                .addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .addExtraHeader("X-Requested-With", "XMLHttpRequest");
        final String url = "http://mzxfweb.my.gov.cn/user/list2.xhtml";
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("type", "党员"));
//        list.add(new BasicNameValuePair("type", "预备党员"));
        //添加请求参数
        QuickCrawlUtil.INS.addPostParam(list);
        //完成请求发送，并记录返回信息
        String content = QuickCrawlUtil.INS.download(url);
        QuickCrawlUtil.INS.clearPostParam();
        return content;
    }

}