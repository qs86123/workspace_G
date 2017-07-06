package com.wt.acountweb.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

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

	public static <T> T jsonToPOJO(JSONObject json, Class<T> clz) {
		try {
			Field[] fs = clz.getDeclaredFields();
			T t = clz.newInstance();
			Method m = null;
			for (Field f : fs) {
				f.setAccessible(true);
				String fName = f.getName();
				if (fName.equals("id"))
					continue;
				String mName = "set" + fName.substring(0, 1).toUpperCase() + fName.substring(1);
				Object value = null;
				Class<?> fType = f.getType();
				if (json.containsKey(fName)) {
					value = json.getObject(fName, fType);
				}
				m = clz.getMethod(mName, fType);
				m.invoke(t, value);
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
