package com.changhong.data.meeting.utils.aes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 加密解密
 * @author wangyang@broadengate.com
 *
 * 2016年10月31日
 */
@Component
public class SignUtil {
	
	@Value("${wapi.appid}")  
	private String appid;
	
	@Value("${wapi.appsecret}")  
	private String appsecret;
	
	@Value("${wapi.token}")  
	private String token;
	
	@Value("${wapi.ek}")  
	private String ek;
	


	public  String  decryptMsg(String signature,String timestamp, String nonce,String originalXml) {
	try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, ek, appid);
			return pc.DecryptMsg(signature, timestamp, nonce, originalXml);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public  String  encryptMsg(String timestamp, String nonce,String replyXml) {
	try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, ek, appid);
			return pc.EncryptMsg(replyXml, timestamp, nonce);
		} catch (AesException e) {
			e.printStackTrace();
		}
		return null;
	}

}
