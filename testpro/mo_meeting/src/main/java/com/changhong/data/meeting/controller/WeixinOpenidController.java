package com.changhong.data.meeting.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.utils.HttpsUtil;

/**
 * 微信获取openid接口
 * @author wangtao
 *
 */
@Controller
@RequestMapping("/wxget")
public class WeixinOpenidController {

	@Value("${wapi.appid}")
	private String appid;

	@Value("${wapi.appsecret}")
	private String appsecret;

	@Value("${wapi.pageauth}")
	private String pageauth;

	@ResponseBody
	@RequestMapping(value = "/openid", method = RequestMethod.GET)
	public String parseWeixin(HttpServletRequest req) throws Exception {
		System.out.println("getopenid controller");
		String code = req.getParameter("code");
		System.out.println(code);
		String url = MessageFormat.format(pageauth, appid, appsecret, code);
		String res = HttpsUtil.get(url);
		JSONObject json = JSONObject.parseObject(res);
		if (json.containsKey("openid")) {
			return json.getString("openid");
		} else {
			throw new Exception("微信接口没有返回正常数据:" + res);
		}

	}
}
