package com.changhong.data.jxw.ktr.util;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * http请求工具类:采用Httpclicent实现get和post请求方式 注意：要使用忽略证书接口通用，使用环境需要JDK8
 * 
 * @author wengchengjie
 * @date 2016年11月18日下午12:21:22
 * @version 1.0
 */
public class HttpClientUtil
{

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 重写验证方法，取消检测ssl
     */
    private static TrustManager trustAll = new X509TrustManager()
    {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
        {

        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
        {

        }

        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }

    };

    /**
     * get请求，返回响应数据
     * 
     * @param url：请求路径
     * @param params：请求参数，没有请求参数时传null
     * @return 返回String类型的响应数据
     */
    public static String doGet(String url, List<BasicNameValuePair> params)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestBuilder reqBuilder = RequestBuilder.get();
        reqBuilder.addHeader("Content-Type", "application/json; charset=utf-8");
        reqBuilder.setUri(url);

        // 构建请求参数
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(60 * 1000) // 创建连接的最长时间
            .setConnectionRequestTimeout(20 * 1000) // 从连接池中获取到连接的最长时间
            .setSocketTimeout(120 * 1000) // 数据传输的最长时间
            .build();
        // 设置请求配置信息
        reqBuilder.setConfig(config);

        // 添加请求参数
        if (null != params)
        {
            for (BasicNameValuePair nvp : params)
            {
                reqBuilder.addParameter(nvp);
            }
        }
        HttpUriRequest req = reqBuilder.build();
        CloseableHttpResponse response = null;
        String retSrc = null;
        try
        {
            response = httpClient.execute(req);
            // 获取响应数据（包含错误响应）
            if (response.getEntity() != null)
            {
                retSrc = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            /*
             * if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { retSrc =
             * EntityUtils.toString(response.getEntity(), "UTF-8"); }
             */
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("CloudMonitor-->HttpUtil-Error:" + e.getMessage());
        }
        finally
        {
            close(httpClient, response);
        }
        return retSrc;
    }

    /**
     * get请求，返回响应数据（包含失败的响应数据）和响应码的json数据
     * 
     * @param url：请求路径
     * @param params：请求参数，没有传null
     * @return json类型的数据，包含返回响应数据(默认"")和响应码(默认-1)
     */
    public static JSONObject doGetReturnCode(String url, List<BasicNameValuePair> params)
    {

        JSONObject returnJson = new JSONObject();// 用于返回的json
        returnJson.put("code", -1);// 默认-1
        returnJson.put("resStr", "");// 默认为空

        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestBuilder reqBuilder = RequestBuilder.get();
        reqBuilder.addHeader("Content-Type", "application/json; charset=utf-8");
        reqBuilder.setUri(url);

        // 构建请求参数
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(60 * 1000) // 创建连接的最长时间
            .setConnectionRequestTimeout(20 * 1000) // 从连接池中获取到连接的最长时间
            .setSocketTimeout(120 * 1000) // 数据传输的最长时间
            .build();
        // 设置请求配置信息
        reqBuilder.setConfig(config);

        // 添加请求参数
        if (null != params)
        {
            for (BasicNameValuePair nvp : params)
            {
                reqBuilder.addParameter(nvp);
            }
        }
        HttpUriRequest req = reqBuilder.build();
        CloseableHttpResponse response = null;
        String retSrc = null;
        try
        {
            response = httpClient.execute(req);
            returnJson.put("code", response.getStatusLine().getStatusCode());

            // 获取响应数据（包含错误响应）
            if (response.getEntity() != null)
            {
                retSrc = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (!StringUtils.isEmpty(retSrc))
                {
                    returnJson.put("resStr", retSrc);
                }
            }
            /*
             * if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { retSrc =
             * EntityUtils.toString(response.getEntity(), "UTF-8"); if (!StringUtils.isEmpty(retSrc)) {
             * returnJson.put("resStr", retSrc); } }
             */
        }
        catch (Exception e)
        {
            e.printStackTrace();
            returnJson.put("resStr", e.getMessage());
            logger.error("CloudMonitor-->HttpUtil-Error:" + e.getMessage());
        }
        finally
        {
            close(httpClient, response);
        }
        return returnJson;
    }

    /**
     * post请求，返回响应数据
     * 
     * @param url：请求路径
     * @param json：请求的json数据，没有传null
     * @param params：请求参数，没有传null
     * @return 响应的String类型数据
     */
    public static String doPost(String url, String json, List<BasicNameValuePair> params)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestBuilder reqBuilder = RequestBuilder.post();
        reqBuilder.setUri(url);
        reqBuilder.addHeader("Content-Type", "application/json; charset=utf-8");

        // 构建请求参数
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(60 * 1000) // 创建连接的最长时间
            .setConnectionRequestTimeout(20 * 1000) // 从连接池中获取到连接的最长时间
            .setSocketTimeout(120 * 1000) // 数据传输的最长时间
            .build();
        // 设置请求配置信息
        reqBuilder.setConfig(config);

        if (null != params)
        {
            for (BasicNameValuePair nvp : params)
            {
                reqBuilder.addParameter(nvp);
            }
        }
        if (!StringUtils.isEmpty(json))
        {
            StringEntity entity = new StringEntity(json, "UTF-8");
            entity.setContentType("application/json");
            reqBuilder.setEntity(entity);
        }
        HttpUriRequest req = reqBuilder.build();

        CloseableHttpResponse response = null;
        String retSrc = null;
        try
        {
            response = httpClient.execute(req);

            // 获取响应数据（包含错误响应）
            if (response.getEntity() != null)
            {
                retSrc = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            /*
             * if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { retSrc =
             * EntityUtils.toString(response.getEntity(), "UTF-8"); }
             */
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("CloudMonitor-->HttpUtil-Error:" + e.getMessage());
        }
        finally
        {
            close(httpClient, response);
        }
        return retSrc;
    }

    /**
     * post请求，返回响应数据和响应码的json数据
     * 
     * @param url：请求路径
     * @param json：请求的json数据，没有传null
     * @param params：请求参数，没有传null
     * @return json类型的数据，包含返回响应数据(默认"")和响应码(默认-1)
     */
    public static JSONObject doPostReturnCode(String url, String json, List<BasicNameValuePair> params)
    {

        JSONObject returnJson = new JSONObject();// 用于返回的json
        returnJson.put("code", -1);// 默认-1
        returnJson.put("resStr", "");// 默认为空

        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestBuilder reqBuilder = RequestBuilder.post();
        reqBuilder.setUri(url);
        reqBuilder.addHeader("Content-Type", "application/json; charset=utf-8");

        // 构建请求参数
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(60 * 1000) // 创建连接的最长时间
            .setConnectionRequestTimeout(20 * 1000) // 从连接池中获取到连接的最长时间
            .setSocketTimeout(120 * 1000) // 数据传输的最长时间
            .build();
        // 设置请求配置信息
        reqBuilder.setConfig(config);

        if (null != params)
        {
            for (BasicNameValuePair nvp : params)
            {
                reqBuilder.addParameter(nvp);
            }
        }
        if (!StringUtils.isEmpty(json))
        {
            StringEntity entity = new StringEntity(json, "UTF-8");
            entity.setContentType("application/json");
            reqBuilder.setEntity(entity);
        }
        HttpUriRequest req = reqBuilder.build();

        CloseableHttpResponse response = null;
        String retSrc = null;
        try
        {
            response = httpClient.execute(req);
            returnJson.put("code", response.getStatusLine().getStatusCode());

            // 获取响应数据（包含错误响应）
            if (response.getEntity() != null)
            {
                retSrc = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (!StringUtils.isEmpty(retSrc))
                {
                    returnJson.put("resStr", retSrc);
                }
            }
            /*
             * if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { retSrc =
             * EntityUtils.toString(response.getEntity(), "UTF-8"); if (!StringUtils.isEmpty(retSrc)) {
             * returnJson.put("resStr", retSrc); } }
             */

        }
        catch (Exception e)
        {
            returnJson.put("resStr", e.getMessage());
            logger.error("CloudMonitor-->HttpUtil-Error:" + e.getMessage());
        }
        finally
        {
            close(httpClient, response);
        }
        return returnJson;
    }

    /**
     * 带有连接池配置信息的post请求，返回响应数据
     * 
     * @param url：请求路径
     * @param json：请求的json数据，没有传null
     * @param params：请求参数，没有传null
     * @return 响应的String类型数据
     */
    public static String doPostWithConfig(String url, String json, List<BasicNameValuePair> params)
    {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        cm.setMaxTotal(200);
        // 设置每个主机地址的并发数
        cm.setDefaultMaxPerRoute(100);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        RequestBuilder reqBuilder = RequestBuilder.post();
        reqBuilder.setUri(url);
        reqBuilder.addHeader("Content-Type", "application/json; charset=utf-8");

        // 构建请求参数
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(60 * 1000) // 创建连接的最长时间
            .setConnectionRequestTimeout(20 * 1000) // 从连接池中获取到连接的最长时间
            .setSocketTimeout(120 * 1000) // 数据传输的最长时间
            .build();
        // 设置请求配置信息
        reqBuilder.setConfig(config);

        if (null != params)
        {
            for (BasicNameValuePair nvp : params)
            {
                reqBuilder.addParameter(nvp);
            }
        }
        if (!StringUtils.isEmpty(json))
        {
            StringEntity entity = new StringEntity(json, "UTF-8");
            entity.setContentType("application/json");
            reqBuilder.setEntity(entity);
        }
        HttpUriRequest req = reqBuilder.build();

        CloseableHttpResponse response = null;
        String retSrc = null;
        try
        {
            response = httpClient.execute(req);

            // 获取响应数据（包含错误响应）
            if (response.getEntity() != null)
            {
                retSrc = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            // if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            // {
            // retSrc = EntityUtils.toString(response.getEntity(), "UTF-8");
            // }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("CloudMonitor-->HttpUtil-Error:" + e.getMessage());
        }
        finally
        {
            close(httpClient, response);
        }
        return retSrc;
    }

    /**
     * 获取https的HttpClient：忽略证书，此处是最新版本HttpClient的SSL避免方式
     */
    private static CloseableHttpClient getSupportHttpsIgnoreCert() throws Exception
    {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder =
            RegistryBuilder.<ConnectionSocketFactory> create();
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainSF);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] {trustAll }, null);
        LayeredConnectionSocketFactory sslSF =
            new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        registryBuilder.register("https", sslSF);

        Registry<ConnectionSocketFactory> registry = registryBuilder.build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);

        CloseableHttpClient client = HttpClientBuilder.create().setConnectionManager(connManager).build();
        return client;
    }

    /**
     * https post请求：忽略证书验证--返回响应数据和响应码的json数据
     * 
     * @param url：请求路径
     * @param json：请求的json数据，没有传null
     * @param params：请求参数，没有传null
     * @return json类型的数据，包含返回响应数据(默认"")和响应码(默认-1)
     */
    public static JSONObject httpsIgnoreCertPostReturnCode(String url, String json,
        List<BasicNameValuePair> params)
    {

        JSONObject returnJson = new JSONObject();// 用于返回的json
        returnJson.put("code", -1);// 默认-1
        returnJson.put("resStr", "");// 默认为空

        CloseableHttpClient httpClient = null;
        try
        {
            httpClient = getSupportHttpsIgnoreCert();
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
            logger.error("CloudMonitor-->HttpUtil-Error:" + e1.getMessage());
            return returnJson;
        }
        RequestBuilder reqBuilder = RequestBuilder.post();
        reqBuilder.setUri(url);
        reqBuilder.addHeader("Content-Type", "application/json; charset=utf-8");

        // 构建请求参数
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(60 * 1000) // 创建连接的最长时间
            .setConnectionRequestTimeout(20 * 1000) // 从连接池中获取到连接的最长时间
            .setSocketTimeout(120 * 1000) // 数据传输的最长时间
            .build();
        // 设置请求配置信息
        reqBuilder.setConfig(config);

        if (null != params)
        {
            for (BasicNameValuePair nvp : params)
            {
                reqBuilder.addParameter(nvp);
            }
        }
        if (!StringUtils.isEmpty(json))
        {
            StringEntity entity = new StringEntity(json, "UTF-8");
            entity.setContentType("application/json");
            reqBuilder.setEntity(entity);
        }
        HttpUriRequest req = reqBuilder.build();

        CloseableHttpResponse response = null;
        String retSrc = null;
        try
        {
            response = httpClient.execute(req);
            returnJson.put("code", response.getStatusLine().getStatusCode());

            // 获取响应数据（包含错误响应）
            // 获取响应数据（包含错误响应）
            if (response.getEntity() != null)
            {
                retSrc = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (!StringUtils.isEmpty(retSrc))
                {
                    returnJson.put("resStr", retSrc);
                }
            }
            /*
             * if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { retSrc =
             * EntityUtils.toString(response.getEntity(), "UTF-8"); if (!StringUtils.isEmpty(retSrc)) {
             * returnJson.put("resStr", retSrc); } }
             */

        }
        catch (Exception e)
        {
            e.printStackTrace();
            returnJson.put("resStr", e.getMessage());
            logger.error("CloudMonitor-->HttpUtil-Error:" + e.getMessage());
        }
        finally
        {
            close(httpClient, response);
        }
        return returnJson;
    }

    /**
     * https get请求--忽略证书验证，返回响应数据和响应码的json数据
     * 
     * @param url：请求路径
     * @param params：请求参数，没有传null
     * @return json类型的数据，包含返回响应数据(默认"")和响应码(默认-1)
     */
    public static JSONObject httpsIgnoreCertGetReturnCode(String url, List<BasicNameValuePair> params)
    {

        JSONObject returnJson = new JSONObject();// 用于返回的json
        returnJson.put("code", -1);// 默认-1
        returnJson.put("resStr", "");// 默认为空

        CloseableHttpClient httpClient = null;
        try
        {
            httpClient = getSupportHttpsIgnoreCert();
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
            logger.error("CloudMonitor-->HttpUtil-Error:" + e1.getMessage());
            return returnJson;
        }
        RequestBuilder reqBuilder = RequestBuilder.get();
        reqBuilder.addHeader("Content-Type", "application/json; charset=utf-8");
        reqBuilder.setUri(url);

        // 构建请求参数
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(60 * 1000) // 创建连接的最长时间
            .setConnectionRequestTimeout(20 * 1000) // 从连接池中获取到连接的最长时间
            .setSocketTimeout(120 * 1000) // 数据传输的最长时间
            .build();
        // 设置请求配置信息
        reqBuilder.setConfig(config);

        // 添加请求参数
        if (null != params)
        {
            for (BasicNameValuePair nvp : params)
            {
                reqBuilder.addParameter(nvp);
            }
        }
        HttpUriRequest req = reqBuilder.build();
        CloseableHttpResponse response = null;
        String retSrc = null;
        try
        {
            response = httpClient.execute(req);
            returnJson.put("code", response.getStatusLine().getStatusCode());

            // 获取响应数据（包含错误响应）
            if (response.getEntity() != null)
            {
                retSrc = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (!StringUtils.isEmpty(retSrc))
                {
                    returnJson.put("resStr", retSrc);
                }
            }

            /*
             * if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { retSrc =
             * EntityUtils.toString(response.getEntity(), "UTF-8"); if (!StringUtils.isEmpty(retSrc)) {
             * returnJson.put("resStr", retSrc); } }
             */
        }
        catch (Exception e)
        {
            e.printStackTrace();
            returnJson.put("resStr", e.getMessage());
            logger.error("CloudMonitor-->HttpUtil-Error:" + e.getMessage());
        }
        finally
        {
            close(httpClient, response);
        }
        return returnJson;
    }

    /**
     * 关闭连接
     * 
     * @param httpClient
     * @param response
     */
    public static void close(CloseableHttpClient httpClient, CloseableHttpResponse response)
    {
        try
        {
            if (response != null)
            {
                response.close();
            }
            if (httpClient != null)
            {
                httpClient.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error("CloudMonitor-->HttpUtil-Error:" + e.getMessage());
        }
    }
}
