package com.changhong.data.meeting.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.apipush.PushManager;
import com.changhong.data.meeting.utils.WeixinDataUtil;
import com.changhong.data.meeting.utils.XmlUtil;
import com.changhong.data.meeting.utils.aes.SignUtil;

/**
 * 推送接收接口
 * 
 * @author wangtao
 */
@RestController
@RequestMapping(value = "/weixin")
public class WeixinPushController extends ExController {

	private Logger logger = LoggerFactory.getLogger(WeixinPushController.class);

	@Autowired
	private SignUtil signUtil;

	@Value("${wapi.token}")
	private String token;

	@RequestMapping(value = "/weixinpush", method = RequestMethod.GET)
	public Object valid(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
		String afterValid = WeixinDataUtil.valid(timestamp, nonce, token);
		logger.info("签名后:{}", afterValid);
		if (afterValid.equals(signature)) {
			logger.info("认证成功");
			return echostr;
		}
		logger.info("认证失败");
		return "false";
	}

	@RequestMapping(value = "/weixinpush", method = RequestMethod.POST)
	public Object valid(@RequestBody String xml, HttpServletRequest req) throws Exception {
		logger.info("微信推送原信息:{}", xml);
		if (xml.contains("Encrypt")) {
			String msg_signature = req.getParameter("msg_signature");
			String timestamp = req.getParameter("timestamp");
			String nonce = req.getParameter("nonce");
			String dexml = signUtil.decryptMsg(msg_signature, timestamp, nonce, xml);
			logger.info("微信推送原信息解密:{}", dexml);
			xml = dexml;
		}
		JSONObject json = XmlUtil.ParseToJson(xml);
		logger.info("微信推送原信息转json:{}", json);
		Object res = PushManager.doPush(json);
		if (res != null) {
			logger.info("回复微信服务器消息:{}", res);
			if (xml.contains("Encrypt")) {
				return signUtil.encryptMsg(String.valueOf(System.currentTimeMillis()),
						String.valueOf((int) (Math.random() * 100)), res.toString());
			}
			return res;
		}
		return "success";
	}

//	@RequestMapping(value = "/token", method = RequestMethod.GET)
//	public Object token(HttpServletRequest req) throws Exception {
//		String appid = req.getParameter("appid");
//		String secret = req.getParameter("secret");
//		if (StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret)) {
//			throw new Exception("参数不对");
//		}
//		return wapiAccessTokenService.getAccessToken(appid, secret);
//	}
}
