package com.changhong.data.meeting.apipush;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 推送管理
 * @author wangyang@broadengate.com
 * 2016年10月19日
 */
public class PushManager {

	private static Logger logger = LoggerFactory.getLogger(PushManager.class);
	
	private static Map<String, Push> pushMap = new HashMap<String, Push>();
	
	public static void register(String key,Push push){
		pushMap.put(key, push);
	}
	
	public static Object doPush(JSONObject pushData) throws Exception {
		StringBuffer key = new StringBuffer();
		if(pushData.containsKey("MsgType")){
			key.append(pushData.get("MsgType"));
		}
		if(pushData.containsKey("Event")){
			key.append(pushData.get("Event"));
		}
		Push push = pushMap.get(key.toString());
		if(push == null){
			logger.error("没有找到适合的推送类：{}",key.toString());
			return null;
		}

		return push.dopush(pushData);
	}
}
