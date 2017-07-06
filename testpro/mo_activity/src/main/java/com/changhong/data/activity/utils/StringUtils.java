package com.changhong.data.activity.utils;

public class StringUtils {

	public static boolean isNull(String data) {
		if (data == null || data.trim().equals(""))
			return true;
		return false;
	}

}
