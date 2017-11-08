package com.myself.edu.qgdyxx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:38 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public class DangyuanInfo2 {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DangyuanInfo2.class);

    public static String dangyuanInfo2(String cookie, boolean isContact, String fileNAme, int page) {
        //设置基本信息
        MyQuickCrawlUtil.INS.setRequestMethod("POST")
                .addExtraHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addExtraHeader("Accept-Encoding", "gzip, deflate")
                .addExtraHeader("Accept-Language", "zh-CN,zh;q=0.8")
                .addExtraHeader("Connection", "keep-alive")
                .addExtraHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addExtraHeader("Cache-Control", "no-cache")
                .addExtraHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .addExtraHeader("X-Requested-With", "XMLHttpRequest")
                .addExtraHeader("Cookie", cookie);

        final String url = "https://182.140.197.53/64a0a160/dirMemberJsonEdit.action";
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("isSQR", "0"));
        list.add(new BasicNameValuePair("isParent", "false"));
        list.add(new BasicNameValuePair("partyOrganCode", "50B7984F4DB84C00AFC0D6A6A0847DDC"));
        list.add(new BasicNameValuePair("organCode", "e118d5ce351842eb9ed1a6fe5e995ad4"));
        list.add(new BasicNameValuePair("containSub", "1"));
        list.add(new BasicNameValuePair("schemaName", "SC_MY_MY_FK"));
        list.add(new BasicNameValuePair("keyWord", ""));
        list.add(new BasicNameValuePair("idCard", ""));
        list.add(new BasicNameValuePair("dylb", ""));
        list.add(new BasicNameValuePair("dyzt", ""));
        list.add(new BasicNameValuePair("order", ""));
        list.add(new BasicNameValuePair("page.currentPage", page + ""));
        list.add(new BasicNameValuePair("page.pageSize", "500"));
        if (isContact)
            list.add(new BasicNameValuePair("iscontact", "1"));
        //添加请求参数
        MyQuickCrawlUtil.INS.addPostParam(list);
        System.out.println("开始获取第< " + page + " >页数据");
        String content = MyQuickCrawlUtil.INS.dangyuanInfo2(url, null, null, fileNAme);
        System.out.println("第< " + page + " >页数据获取完成");
        //完成请求发送，并记录返回信息

        MyQuickCrawlUtil.INS.clearPostParam();
        list.clear();
        return content;
    }

    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException, NoSuchProviderException {
        SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }
}
