package com.myself.edu.movedata;

import com.myself.edu.utils.Logger;
import com.myself.edu.utils.QuickCrawlUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:28 2017/6/27
 * @Email:tao8.wang@changhong.com
 */
public class ChangeStatus {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ChangeStatus.class);

    public static void main(String[] args) {
        //设置基本信息
        QuickCrawlUtil.INS.setRequestMethod("POST")
                .setProxySiteName("CHAPP")
                .addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addExtraHeader("Cookie", "JSESSIONID=23E7C7635985C9DCF0656B960AAC12F2")
                .addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .addExtraHeader("X-Requested-With", "XMLHttpRequest");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("id", "627856"));
        list.add(new BasicNameValuePair("userId", "112690"));
        list.add(new BasicNameValuePair("cash", "295"));
        list.add(new BasicNameValuePair("dateStr", "2017-01"));
        list.add(new BasicNameValuePair("state", "未交"));

        final String url = "http://mzxfweb.my.gov.cn/paid/updateOne.xhtml";

        //添加请求参数
        QuickCrawlUtil.INS.addPostParam(list);

        //完成请求发送，并记录返回信息
        Logger.INS.info(QuickCrawlUtil.INS.download(url));
        QuickCrawlUtil.INS.clearPostParam();
    }

    public static void changeState(String jsessionid, String id, String userId, String cash, String dateStr, String state) {
        //设置基本信息
        QuickCrawlUtil.INS.setRequestMethod("POST")
                .setProxySiteName("CHAPP")
                .addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addExtraHeader("Cookie", "JSESSIONID=" + jsessionid)
                .addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .addExtraHeader("X-Requested-With", "XMLHttpRequest");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("id", id));
        list.add(new BasicNameValuePair("userId", userId));
        list.add(new BasicNameValuePair("cash", cash));
        list.add(new BasicNameValuePair("dateStr", dateStr));
        list.add(new BasicNameValuePair("state", state));

        final String url = "http://mzxfweb.my.gov.cn/paid/updateOne.xhtml";

        //添加请求参数
        QuickCrawlUtil.INS.addPostParam(list);

        //完成请求发送，并记录返回信息
        Logger.INS.info(QuickCrawlUtil.INS.download(url));
        QuickCrawlUtil.INS.clearPostParam();
    }

}
