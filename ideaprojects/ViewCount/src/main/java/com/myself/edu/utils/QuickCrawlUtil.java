package com.myself.edu.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.common.collect.Maps;

/**
 *
 */

public enum QuickCrawlUtil {
    INS;

    QuickCrawlUtil() {
        queue = new ArrayBlockingQueue(20);
        builder = RequestConfig.custom().setConnectTimeout(30 * 1000).setSocketTimeout(30 * 1000);
    }

    private Map<String, String> EXTRA_HEADERS_MAP = Maps.newHashMap();
    private Map<String, List<NameValuePair>> PostParamsMap = Maps.newHashMap();
    private BlockingQueue<String> queue;
    private RequestConfig.Builder builder;
    private String PROXY_SITE_NAME;
    private String requestMethod;
    private String jsessionid;

    public QuickCrawlUtil setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public QuickCrawlUtil clearPostParam() {
        PostParamsMap.clear();
        return this;
    }

    public QuickCrawlUtil addPostParam(String key, String value) {
        NameValuePair nameValuePair = new BasicNameValuePair(key, value);
        PostParamsMap.get(PROXY_SITE_NAME).add(nameValuePair);
        return this;
    }

    public QuickCrawlUtil addPostParam(NameValuePair nameValuePair) {
        PostParamsMap.get(PROXY_SITE_NAME).add(nameValuePair);
        return this;
    }

    public QuickCrawlUtil addPostParam(List<NameValuePair> nameValuePair) {
        PostParamsMap.put(PROXY_SITE_NAME, nameValuePair);
        return this;
    }

    public QuickCrawlUtil addExtraHeader(String key, String value) {
        EXTRA_HEADERS_MAP.put(key, value);
        return this;
    }

    public QuickCrawlUtil removeExtraHeader(String key) {
        EXTRA_HEADERS_MAP.remove(key);
        return this;
    }

    public QuickCrawlUtil addExtraHeaders(Map<String, String> map) {
        EXTRA_HEADERS_MAP.putAll(map);
        return this;
    }

    public QuickCrawlUtil setProxySiteName(String name) {
        PROXY_SITE_NAME = name;
        return this;
    }

    private HttpPost createPostRequest(String url) {
        HttpPost post = new HttpPost(url);
        addHeaders(post, url);
        try {
            post.setEntity(new UrlEncodedFormEntity(getPostData(PROXY_SITE_NAME), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Logger.INS.error("create post failed");
        }

        return post;
    }

    private HttpGet createGetRequest(String url) {
        HttpGet get = new HttpGet(url);
        addHeaders(get, url);
        return get;
    }

    private List<? extends NameValuePair> getPostData(String proxySiteName4Dx) {
        return PostParamsMap.get(proxySiteName4Dx);
    }

    private void addHeaders(HttpUriRequest httpGet, String string) {
//		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*;q=0.8");
//		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
//		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
//		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
//		httpGet.setHeader("User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        for (Map.Entry<String, String> en : EXTRA_HEADERS_MAP.entrySet()) {
            httpGet.setHeader(en.getKey(), en.getValue());
        }
    }

    public String download(String url) {
        return download(url, null, null);
    }

    public String download(String url, String host, Integer port) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }
            CloseableHttpClient httpClient =
                    HttpClients.custom().setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            //if (200 == code) {
            HttpEntity en = httpres.getEntity();
            String content = EntityUtils.toString(en, "utf-8");
            httpClient.close();
            EntityUtils.consume(en);
            httpres.close();
            return content;
            //}
        } catch (Exception e) {
            Logger.INS.error("{}", e);
        }
        return "";
    }

    public String login(String url, String host, Integer port) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }
            CloseableHttpClient httpClient =
                    HttpClients.custom().setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);
            Header[] cookies = httpres.getHeaders("Set-Cookie");
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            if (200 == code) {
                String jessionid = cookies[0].getValue();
                int start = jessionid.indexOf("=") + 1;
                int end = jessionid.indexOf(";");
                HttpEntity en = httpres.getEntity();
                String content = EntityUtils.toString(en, "utf-8");
                JSONObject json = JSON.parseObject(content);
                if (!json.getBoolean("success")) {
                    return "";
                }
                return jessionid.substring(start, end);
            }
        } catch (Exception e) {
            Logger.INS.error("{}", e);
        }
        return "";
    }

    private HttpUriRequest getRequest(String url) {
        if (requestMethod.equalsIgnoreCase("get")) {
            return createGetRequest(url);
        }

        if (requestMethod.equalsIgnoreCase("post")) {
            return createPostRequest(url);
        }
        return null;
    }
}
