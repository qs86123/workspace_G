package com.changhong.data.meeting.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author wangtao
 *
 */
public class HttpQueryUtil {

	 /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";
	
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
		CloseableHttpResponse response = null;
		try {
			// 添加http头信息
			response = httpclient.execute(get);
			int code = response.getStatusLine().getStatusCode();
			rev = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
			if (code == 200) {
				return rev;
			} else {
				throw new Exception("返回状态码不正确");
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				get.releaseConnection();
				httpclient.close();
			} catch (IOException e) {
				throw new Exception("网络I/O错误", e);
			}
		}
	}

	public static String get(String url) {
        StringBuffer bufferRes = null;
        try {
            URL urlGet = new URL(url);
            HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection();
            // 连接超时
            http.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢，增大时间
            http.setReadTimeout(25000);
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();

            InputStream in = http.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
            String valueString = null;
            bufferRes = new StringBuffer();
            while ((valueString = read.readLine()) != null){
                bufferRes.append(valueString);
            }
            in.close();
            if (http != null) {
                // 关闭连接
                http.disconnect();
            }
            return bufferRes.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 从指定url通过json string参数获取数据
	 *
	 * @param url
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 * @throws GeneralException
	 */
	public static String postWithJSON(String url, String jsonStr) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.addHeader("charset", "UTF-8");
			HttpEntity httpEntity = null;
			httpEntity = new StringEntity(jsonStr, "UTF-8");
			httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
			httpPost.setEntity(httpEntity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			// 释放链接
			httpPost.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
				throw new Exception("网络I/O错误");
			}
		}
	}

	/**
	 * 文件上傳
	 * 
	 * @param url
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(String url, File file) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		try {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addBinaryBody("buffer", file, ContentType.DEFAULT_BINARY, file.getName());
			httppost.setEntity(builder.build());
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			String res = (EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8")));
			EntityUtils.consume(resEntity);
			return res;
		} catch (ClientProtocolException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			// 释放链接
			httppost.releaseConnection();
			try {
				httpclient.close();
			} catch (IOException e) {
				throw new Exception("网络I/O错误");
			}
		}
	}

	public String http(String url, Map<String, String> params) {
		URL u = null;
		HttpURLConnection con = null;
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			for (Map.Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		System.out.println("send_url:" + url);
		System.out.println("send_data:" + sb.toString());
		OutputStreamWriter osw = null;
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			//// POST 只能为大写，严格限制，post会不识别
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(sb.toString());
			osw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = null;
		try {
			// 一定要有返回值，否则无法把请求发送给server端。
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			if (br != null) {
				while ((temp = br.readLine()) != null) {
					buffer.append(temp);
					buffer.append("\n");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return buffer.toString();
	}
}
