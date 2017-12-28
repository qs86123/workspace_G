package com.myself.edu.qgdyxx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.myself.edu.utils.Logger;
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
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 */

public enum MyQuickCrawlUtil {
    INS;

    MyQuickCrawlUtil() {
        queue = new ArrayBlockingQueue(20);
        builder = RequestConfig.custom().setConnectTimeout(30 * 1000).setSocketTimeout(30 * 1000);
    }

    private Map<String, String> EXTRA_HEADERS_MAP = Maps.newHashMap();
    private Map<String, List<NameValuePair>> PostParamsMap = Maps.newHashMap();
    private BlockingQueue<String> queue;
    private RequestConfig.Builder builder;
    private String PROXY_SITE_NAME;
    private String requestMethod;

    public MyQuickCrawlUtil setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public MyQuickCrawlUtil clearPostParam() {
        PostParamsMap.clear();
        return this;
    }

    public MyQuickCrawlUtil addPostParam(String key, String value) {
        NameValuePair nameValuePair = new BasicNameValuePair(key, value);
        PostParamsMap.get(PROXY_SITE_NAME).add(nameValuePair);
        return this;
    }

    public MyQuickCrawlUtil addPostParam(NameValuePair nameValuePair) {
        PostParamsMap.get(PROXY_SITE_NAME).add(nameValuePair);
        return this;
    }

    public MyQuickCrawlUtil addPostParam(List<NameValuePair> nameValuePair) {
        PostParamsMap.put(PROXY_SITE_NAME, nameValuePair);
        return this;
    }

    public MyQuickCrawlUtil addExtraHeader(String key, String value) {
        EXTRA_HEADERS_MAP.put(key, value);
        return this;
    }

    public MyQuickCrawlUtil removeExtraHeader(String key) {
        EXTRA_HEADERS_MAP.remove(key);
        return this;
    }

    public MyQuickCrawlUtil addExtraHeaders(Map<String, String> map) {
        EXTRA_HEADERS_MAP.putAll(map);
        return this;
    }

    public MyQuickCrawlUtil setProxySiteName(String name) {
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

    public String loginvpn(String url, String host, Integer port) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }

            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = VpnLogin.createIgnoreVerifySSL();

            // 设置协议http和https对应的处理socket链接工厂的对象
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.INSTANCE)
//                    .register("https", new SSLConnectionSocketFactory(sslcontext))
//                    .build();
//            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            HttpClients.custom().setConnectionManager(connManager);

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpClient =
                    HttpClients.custom().setSSLSocketFactory(sslsf)
                            .setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);

            Header[] cookies = httpres.getHeaders("Set-Cookie");
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            if (200 == code) {
                String jessionid = cookies[0].getValue();
                int end = jessionid.indexOf(";") + 1;
                System.out.println(jessionid.substring(0, end));
                HttpEntity en = httpres.getEntity();
                String content = EntityUtils.toString(en, "utf-8");
                System.out.println(content);
                return jessionid.substring(0, end);
            }
        } catch (Exception e) {
            Logger.INS.error("{}", e);
        }
        return "";
    }

    public String loginweb(String url, String host, Integer port) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }

            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = VpnLogin.createIgnoreVerifySSL();

            // 设置协议http和https对应的处理socket链接工厂的对象
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.INSTANCE)
//                    .register("https", new SSLConnectionSocketFactory(sslcontext))
//                    .build();
//            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            HttpClients.custom().setConnectionManager(connManager);

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpClient =
                    HttpClients.custom().setSSLSocketFactory(sslsf)
                            .setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);

            Header[] cookies = httpres.getHeaders("Set-Cookie");
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            if (200 == code) {
                String cookie = "";
                for (int i = 0; i < cookies.length; i++) {
                    String c = cookies[i].getValue();
                    cookie += c.substring(0, c.indexOf(";") + 1);
                    System.out.println(cookie);
                }
                HttpEntity en = httpres.getEntity();
                String content = EntityUtils.toString(en, "utf-8");
                System.out.println("loginWebContent:" + content);
                return cookie;
            }
        } catch (Exception e) {
            Logger.INS.error("{}", e);
        }
        return "";
    }

    public String zTreeData(String url, String host, Integer port,String fileName) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }

            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = VpnLogin.createIgnoreVerifySSL();

            // 设置协议http和https对应的处理socket链接工厂的对象
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.INSTANCE)
//                    .register("https", new SSLConnectionSocketFactory(sslcontext))
//                    .build();
//            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            HttpClients.custom().setConnectionManager(connManager);

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpClient =
                    HttpClients.custom().setSSLSocketFactory(sslsf)
                            .setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);

            Header[] cookies = httpres.getHeaders("Set-Cookie");
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            if (200 == code) {
                String cookie = "";
                for (int i = 0; i < cookies.length; i++) {
                    String c = cookies[i].getValue();
                    cookie += c.substring(0, c.indexOf(";") + 1);
                    System.out.println(cookie);
                }
                HttpEntity en = httpres.getEntity();
                toFile(en.getContent(), fileName);
//                String content = EntityUtils.toString(en, "utf-8");
//                System.out.println("aaaaaaaa" + content);
                return cookie;
            }
        } catch (Exception e) {
            Logger.INS.error("{}", e);
        }
        return "";
    }

    public String dangyuanInfo(String url, String host, Integer port, String fileName) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }

            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = VpnLogin.createIgnoreVerifySSL();

            // 设置协议http和https对应的处理socket链接工厂的对象
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.INSTANCE)
//                    .register("https", new SSLConnectionSocketFactory(sslcontext))
//                    .build();
//            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            HttpClients.custom().setConnectionManager(connManager);

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpClient =
                    HttpClients.custom().setSSLSocketFactory(sslsf)
                            .setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);

            Header[] cookies = httpres.getHeaders("Set-Cookie");
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            if (200 == code) {
                String cookie = "";
                for (int i = 0; i < cookies.length; i++) {
                    String c = cookies[i].getValue();
                    cookie += c.substring(0, c.indexOf(";") + 1);
                    System.out.println(cookie);
                }
                HttpEntity en = httpres.getEntity();
                toFile(en.getContent(), fileName);
//                String content = EntityUtils.toString(en, "utf-8");
//                System.out.println("aaaaaaaa" + content);
                return cookie;
            }
        } catch (Exception e) {
            Logger.INS.error("{}", e);
        }
        return "";
    }

    public String dangyuanInfo2(String url, String host, Integer port, String fileName) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }

            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = VpnLogin.createIgnoreVerifySSL();

            // 设置协议http和https对应的处理socket链接工厂的对象
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.INSTANCE)
//                    .register("https", new SSLConnectionSocketFactory(sslcontext))
//                    .build();
//            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            HttpClients.custom().setConnectionManager(connManager);

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpClient =
                    HttpClients.custom().setSSLSocketFactory(sslsf)
                            .setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);

            Header[] cookies = httpres.getHeaders("Set-Cookie");
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            if (200 == code) {
                String cookie = "";
                for (int i = 0; i < cookies.length; i++) {
                    String c = cookies[i].getValue();
                    cookie += c.substring(0, c.indexOf(";") + 1);
                    System.out.println(cookie);
                }
                HttpEntity en = httpres.getEntity();
                String content = toFile(en.getContent(), fileName, false);
//                String content = EntityUtils.toString(en, "utf-8");
//                System.out.println("aaaaaaaa" + content);
                return content;
            }
        } catch (Exception e) {
            Logger.INS.error("{}", e);
            AppStart.page--;
        }
        return "";
    }

    public String updateDyxxInfo(String url, String host, Integer port) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }

            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = VpnLogin.createIgnoreVerifySSL();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpClient =
                    HttpClients.custom().setSSLSocketFactory(sslsf)
                            .setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);

            Header[] cookies = httpres.getHeaders("Set-Cookie");
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            if (200 == code) {
                HttpEntity en = httpres.getEntity();
                String content = EntityUtils.toString(en, "utf-8");
                return content;
            }
            HttpEntity en = httpres.getEntity();
            String content = EntityUtils.toString(en, "utf-8");
            return content;
        } catch (Exception e) {
            Logger.INS.error("{}", e);
        }
        return "fail";
    }

    public String getBasicInfoMini(String url, String host, Integer port) {
        try {
            if (StringUtils.isNotBlank(host) && port != null && port > 0 && port < 65536) {
                builder.setProxy(new HttpHost(host, port));
            }

            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = VpnLogin.createIgnoreVerifySSL();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpClient =
                    HttpClients.custom().setSSLSocketFactory(sslsf)
                            .setDefaultRequestConfig(builder.build()).build();
            HttpUriRequest httpRequest = getRequest(url);
            CloseableHttpResponse httpres = httpClient.execute(httpRequest);

            Header[] cookies = httpres.getHeaders("Set-Cookie");
            int code = httpres.getStatusLine().getStatusCode();
            Logger.INS.debug("code is :{}", code);
            if (200 == code) {
                HttpEntity en = httpres.getEntity();
                String content = EntityUtils.toString(en, "utf-8");
                return content;
            }
        } catch (Exception e) {
            Logger.INS.error("{}", e);
        }
        return null;
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

    public static void toFile(InputStream is, String file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] b = new byte[4096];
            int len = -1;
            while ((len = is.read(b)) != -1)
                fos.write(b, 0, len);
            fos.flush();
            is.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toFile(InputStream is, String file, boolean append) {
        try {
            FileOutputStream fos = new FileOutputStream(file, append);
            byte[] b = new byte[4096];
            int len = -1;
            StringBuilder sb = new StringBuilder();
            while ((len = is.read(b)) != -1) {
                fos.write(b, 0, len);
                sb.append(new String(b, 0, len, "UTF-8"));
            }
            fos.flush();
            is.close();
            fos.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
