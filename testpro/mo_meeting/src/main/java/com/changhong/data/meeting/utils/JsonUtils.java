package com.changhong.data.meeting.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;


/**
 * @author wangtao
 *
 */
public class JsonUtils {
	/**
	 * 实体转json
	 * 
	 * @param clz
	 * @param t
	 * @return
	 */
	public static <T> JSONObject POJOtoJson(Class<T> clz, T t) {
		JSONObject result = new JSONObject();
		Field[] fs = clz.getDeclaredFields();
		Method m = null;
		for (Field f : fs) {
			f.setAccessible(true);
			String fName = f.getName();
			try {
				String mName = "get" + fName.substring(0, 1).toUpperCase() + fName.substring(1);
				m = clz.getMethod(mName);
				Object value = m.invoke(t);
				result.put(fName, value == null ? "" : value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
