package com.changhong.location.utils;



import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpQueryUtil {
	
    /**
     * 从指定url获取数据
     *
     * @param url
     * @return
     * @throws Exception 
     * @throws GeneralException
     */
    public static String getDataFromUrl(String url) throws Exception {
        String rev = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response=null;
        try {
            //添加http头信息
            response = httpclient.execute(get);
            int code = response.getStatusLine().getStatusCode();
            rev = EntityUtils.toString(response.getEntity(),Charset.forName("UTF-8"));
            if (code == 200) {
                return rev;
            }else{
                throw new Exception("返回状态码不正确");
            }
        }catch (Exception e) {
            throw new Exception(e);
        } finally {
            try {
            	if(response!=null){
            		response.close();
            	}
                get.releaseConnection();
                httpclient.close();
            } catch (IOException e) {
                throw new Exception("网络I/O错误",e);
            }
        }
    }

}
