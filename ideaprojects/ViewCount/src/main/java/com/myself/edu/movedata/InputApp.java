package com.myself.edu.movedata;

import com.myself.edu.utils.Logger;
import com.myself.edu.utils.QuickCrawlUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class InputApp {

    public static void main(String[] args) {
        inputCost("84BA01D2FC75300E2D9BA2AFD18EFC87", "112690", "2017-01", "2017-01", "148.1");
    }

    /**
     * 录入党费
     * 需传参userId,start,end,cash
     * start,end的格式为2017-01
     */

    public static void inputCost(String jsessionId, String userId, String start, String end, String cash) {
        //设置基本信息
        QuickCrawlUtil.INS.setRequestMethod("POST")
                .setProxySiteName("CHAPP")
                .addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addExtraHeader("Cookie", "JSESSIONID=" + jsessionId)
                .addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .addExtraHeader("X-Requested-With", "XMLHttpRequest")
        ;

        //接口地址
        final String url = "http://mzxfweb.my.gov.cn/paid/addMore.xhtml";

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("userId", userId));
        list.add(new BasicNameValuePair("start", start));
        list.add(new BasicNameValuePair("end", end));
        list.add(new BasicNameValuePair("cash", cash));
        //添加请求参数
        QuickCrawlUtil.INS.addPostParam(list);

        //完成请求发送，并记录返回信息
        Logger.INS.info(QuickCrawlUtil.INS.download(url));
        QuickCrawlUtil.INS.clearPostParam();
    }
}