//package com.changhong.data.meeting.service.wapi;
//
//
//import java.text.MessageFormat;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.changhong.data.meeting.utils.HttpQueryUtil;
//
//@Service
//public class WapiAccessTokenService {
//	
//	@Value("${wapi.access_token}")  
//	private String accessTokenURL;
//	
//	@Value("${wapi.appid}")  
//	private String appid;
//	
//	@Value("${wapi.appsecret}")  
//	private String appsecret;
//	
//	/**
//	 * 
//	 * @param appid
//	 * @param secret
//	 * @return
//	 * @throws Exception 
//	 */
////	@Cacheable
//	public String getAccessToken1(String appid,String secret) throws Exception{
//		String url =  MessageFormat.format(accessTokenURL,appid,secret);
//		String res = HttpQueryUtil.getDataFromUrl(url);
//		if(res!=null && !res.equals("")){
//			JSONObject json = JSONObject.parseObject(res);
//			if(json.containsKey("access_token")){
//				return json.getString("access_token");
//			}else{
//				throw new Exception("获取token接口，返回结果:"+res);
//			}
//		}else{
//			throw new Exception("获取token接口，返回结果为空");
//		}
//	}
//	
//	public String getTempAccessToken1() throws Exception{
//		return getAccessToken1(appid, appsecret);
//	}
//
//}
