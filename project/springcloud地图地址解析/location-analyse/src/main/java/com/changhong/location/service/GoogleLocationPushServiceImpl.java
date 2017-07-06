package com.changhong.location.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.location.utils.HttpQueryUtil;

/**
 * Description: 使用google的那套经纬度解析地址信息 Company: changhong
 * 
 * @author wangtao
 * @date 2016年10月25日上午9:02:33
 */
@Service
public class GoogleLocationPushServiceImpl implements LocationPushService {

	@Value("${google.location.url}")
	private String baseurl;

	@Override
	public Object getLocation(String lng, String lat, boolean parse) {
		// String url = "http://maps.google.cn/maps/api/geocode/json?latlng=" +
		// lat + "," + lng + "&sensor=true";
		String url = MessageFormat.format(baseurl, lat, lng);
		String response = null;
		try {
			response = HttpQueryUtil.getDataFromUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (parse && response != null && !response.equals(""))
			return parseResponse(response);
		return response;

	}

	private String parseResponse(String response) {
		JSONObject resultjson = new JSONObject();
		JSONObject jo = JSONObject.parseObject(response);
		if (jo != null && !jo.toJSONString().equals("") && jo.get("status").toString().equals("OK")) {
			JSONArray ja = jo.getJSONArray("results");
			JSONObject jo2 = ja.getJSONObject(0);
			System.out.println("jo3:" + jo2.getString("address_components"));
			JSONArray ja_address = jo2.getJSONArray("address_components");
			int arrNum = ja_address.size();
			for (int i = 0; i < arrNum; i++) {
				JSONObject j = ja_address.getJSONObject(i);
				String type = j.getJSONArray("types").getString(0);
				type = type.equals("country") ? "country"
						: type.equals("street_number") ? "street_number"
								: type.equals("route") ? "street"
										: type.equals("political") ? "district"
												: type.equals("locality") ? "province"
														: type.equals("administrative_area_level_1") ? "city"
																: type.equals("administrative_area_level_1") ? "city2"
																		: null;
				if (type == null || type.equals(""))
					continue;
				resultjson.put(type, j.get("long_name"));
			}
		}
		return resultjson.toJSONString();
	}

}
