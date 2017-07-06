package com.changhong.location.service;

import java.text.MessageFormat;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.changhong.location.utils.HttpQueryUtil;

/**
 * Description: 使用百度的那套经纬度解析地址信息 Company: changhong
 * 
 * @author wangtao
 * @date 2016年10月25日上午9:01:31
 */
@Service
public class BaiduLocationPushServiceImpl implements LocationPushService {

	@Value("${tobaiduXY.location.url}")
	private String tobaiduXYurl;

	@Value("${baidu.location.url}")
	private String baseurl;

	@Override
	public Object getLocation(String lng, String lat, boolean parse) {
		Object result = new Object();
		// 将google经纬度转换成百度经纬度的url
		// String url =
		// "http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x=" + lng +
		// "&y=" + lat + "";
		String url = MessageFormat.format(tobaiduXYurl, lng, lat);
		try {
			String response = HttpQueryUtil.getDataFromUrl(url);
			System.out.println("response:" + response);
			JSONObject jo = JSONObject.parseObject(response);
			if (jo != null && !jo.toString().equals("") && jo.getIntValue("error") == 0) {
				byte[] xLng = Base64.decodeBase64(jo.get("x").toString());
				byte[] yLat = Base64.decodeBase64(jo.get("y").toString());
				System.out.println(new String(yLat) + "," + new String(xLng));
				result = getBaiduLocationString(new String(xLng), new String(yLat), parse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private Object getBaiduLocationString(String lng, String lat, boolean parse) {
		// 百度地图api为开发者提供的密钥
		// String ak = "VH2lk83taKFG4Ac54oWDiSzpjf94B9ND";
		// 把百度经纬度转换成位置信息的url
		// String urlLocation = "http://api.map.baidu.com/geocoder/v2/?ak=" + ak
		// + "&callback=renderReverse&location="
		// + lat + "," + lng + "&output=json&pois=0";
		String urlLocation = MessageFormat.format(baseurl, lat, lng);
		System.out.println(urlLocation);
		String response = null;
		try {
			response = HttpQueryUtil.getDataFromUrl(urlLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (parse && response != null && !response.equals("")) {
			return parseRespose(response);
		}
		return response;
	}

	private String parseRespose(String response) {
		JSONObject result = new JSONObject();
		try {
			System.out.println(response.substring(response.indexOf("{"), response.lastIndexOf(")")));
			JSONObject jo = JSONObject
					.parseObject(response.substring(response.indexOf("{"), response.lastIndexOf(")")));
			JSONObject jo2 = jo.getJSONObject("result");
			JSONObject jo3 = jo2.getJSONObject("addressComponent");
			String[] jsonKey = { "country", "province", "city", "district", "street", "street_number" };
			for (String key : jsonKey) {
				if (jo3.containsKey(key))
					result.put(key, jo3.get(key));
			}
			System.out.println("位置信息：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toJSONString();
	}

}
