package com.changhong.data.activity.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.activity.service.WXTokenService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller
@RequestMapping("/wxInfo")
public class WXInfoController {

	@Autowired
	private WXTokenService tkService;

	@RequestMapping("/getUserInfo")
	@ResponseBody
	public JSONObject getAccessToken(HttpServletRequest request) {
		String code = request.getParameter("code");
		JSONObject json = new JSONObject();
		try {
			WxMpOAuth2AccessToken token = tkService.oauth2getAccessToken(code);
			WxMpUser userInfo = tkService.oauth2getUserInfo(token, null);
			json.put("error", 0);
			json.put("openId", userInfo.getOpenId());
			json.put("nikeName", userInfo.getNickname());
			json.put("headImgUrl", userInfo.getHeadImgUrl());
			return json;
		} catch (WxErrorException e) {
			json.put("error", 1);
			json.put("msg", "获取用户信息失败:" + e.getMessage());
			e.printStackTrace();
			return json;
		}
	}
}
