package com.changhong.data.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
public class WXTokenService {

	@Autowired
	private WxMpServiceImpl wmService;

	public String getAccessToken() {
		String token = "";
		try {
			token = wmService.getAccessToken();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 获取网页授权token
	 * 
	 * @param code
	 * @return
	 * @throws WxErrorException
	 */
	public WxMpOAuth2AccessToken oauth2getAccessToken(String code) throws WxErrorException {
		WxMpOAuth2AccessToken result = wmService.oauth2getAccessToken(code);
		return result;
	}

	/**
	 * 刷新网页授权token
	 * 
	 * @param code
	 * @return
	 * @throws WxErrorException
	 */
	public WxMpOAuth2AccessToken oauth2refreshAccessToken(String refreshToken) throws WxErrorException {
		WxMpOAuth2AccessToken result = wmService.oauth2refreshAccessToken(refreshToken);
		return result;
	}

	/**
	 * 刷新网页授权获取用户信息
	 * 
	 * @param code
	 * @return
	 * @throws WxErrorException
	 */
	public WxMpUser oauth2getUserInfo(WxMpOAuth2AccessToken oAuth2AccessToken, String lang) throws WxErrorException {
		WxMpUser userInfo = wmService.oauth2getUserInfo(oAuth2AccessToken, lang);
		if (userInfo == null || userInfo.getOpenId() == null || userInfo.getOpenId().equals("")) {
			WxMpOAuth2AccessToken refresh = oauth2refreshAccessToken(oAuth2AccessToken.getRefreshToken());
			userInfo = wmService.oauth2getUserInfo(refresh, lang);
		}
		return userInfo;
	}

}
