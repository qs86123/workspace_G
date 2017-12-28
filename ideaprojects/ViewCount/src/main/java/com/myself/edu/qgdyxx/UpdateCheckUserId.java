package com.myself.edu.qgdyxx;

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
 * @Description https://182.140.197.53/vpn/user/auth/password
 * @Author: wangtao
 * @Date:15:57 2017/11/6
 * @Email:tao8.wang@changhong.com
 */
public class UpdateCheckUserId {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UpdateCheckUserId.class);

    public static String updateCheckUserId(String cookie) {
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

        List<NameValuePair> list = new ArrayList<>();
        final String checkUsrId = "https://182.140.197.53/64a0a160/checkUserID.action";
        list.add(new BasicNameValuePair("zjhm", "510702197507160541"));
        list.add(new BasicNameValuePair("dyid", "c00519fb86f4c619e86c2d54bbd087a"));
        list.add(new BasicNameValuePair("iscontact", "2"));
        MyQuickCrawlUtil.INS.addPostParam(list);
        String content = MyQuickCrawlUtil.INS.updateDyxxInfo(checkUsrId, null, null);
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
