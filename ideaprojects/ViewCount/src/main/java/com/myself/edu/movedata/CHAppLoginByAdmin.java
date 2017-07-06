package com.myself.edu.movedata;

import com.myself.edu.utils.QuickCrawlUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CHAppLoginByAdmin {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CHAppLoginByAdmin.class);

    public static void main(String[] args) {
        //设置基本信息
        QuickCrawlUtil.INS.setRequestMethod("POST")
                .setProxySiteName("CHAPP")
                .addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
//		.addExtraHeader("Accept-Encoding", "gzip, deflate")
//		.addExtraHeader("Accept-Language", "zh-CN,zh;q=0.8")
//		.addExtraHeader("Connection", "keep-alive")
                .addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//		.addExtraHeader("Cookie", "JSESSIONID=567B473DAC6083070C7EAB3531272DD5")
//		.addExtraHeader("Host", "211.149.204.181:8090")
//		.addExtraHeader("Origin", "http://211.149.204.181:8090")
//		.addExtraHeader("Referer", "http://211.149.204.181:8090/admin/index.xhtml")
                .addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .addExtraHeader("X-Requested-With", "XMLHttpRequest")
        ;


        final String url = "http://mzxfweb.my.gov.cn/loginByAdmin.xhtml";
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        String userName = "";
        List<String> userNameList = new ArrayList<String>();
        userNameList.add("DZB12");
        userNameList.add("DZB13");
        userNameList.add("DZB14");
        for (String _userName : userNameList) {
            userName = _userName;
            list.add(new BasicNameValuePair("userName", userName));
            list.add(new BasicNameValuePair("passWord", "123456"));
            //添加请求参数
            QuickCrawlUtil.INS.addPostParam(list);
            String jsessionid = QuickCrawlUtil.INS.login(url, null, null);
            System.out.println(jsessionid);
            //完成请求发送，并记录返回信息

            String content = CHAppList2.List2(jsessionid);
            System.out.println(_userName);
            System.out.println(content);
            QuickCrawlUtil.INS.clearPostParam();
            list.clear();
        }
    }

    public static String login(String userName, String password) {
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
        list.add(new BasicNameValuePair("userName", userName));
        list.add(new BasicNameValuePair("passWord", password));
        //添加请求参数
        QuickCrawlUtil.INS.addPostParam(list);
        String jsessionid = QuickCrawlUtil.INS.login(url, null, null);
        logger.info("JsessionId=" + jsessionid);
        //完成请求发送，并记录返回信息
        QuickCrawlUtil.INS.clearPostParam();
        list.clear();
        return jsessionid;
    }

}