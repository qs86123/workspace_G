package com.changhong.data.meeting.utils;


import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * @author wangtao
 *
 */
public class WeixinDataUtil {

	public static String valid(String timestamp,String nonce,String token){
		String paramsList[] = {timestamp,nonce,token};
		StringBuffer sb = new StringBuffer();
		Arrays.sort(paramsList);
		for(String param : paramsList){
			sb.append(param);
		}
		return DigestUtils.sha1Hex(sb.toString());
	}
}
