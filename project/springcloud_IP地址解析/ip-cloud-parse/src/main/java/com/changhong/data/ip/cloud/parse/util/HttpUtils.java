package com.changhong.data.ip.cloud.parse.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月25日
 */
@Service
public class HttpUtils {
    public String doGet(String url)
    {
        String res=null;
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();
        CloseableHttpResponse response=null;
        HttpGet post=new HttpGet(url);
        try {
            response=closeableHttpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200)
            {
                res= EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
