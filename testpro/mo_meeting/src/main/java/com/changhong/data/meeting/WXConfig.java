package com.changhong.data.meeting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

@Configuration
@PropertySource("classpath:application-dev.properties")
public class WXConfig {

	@Value("${wapi.appid}")
	private String appid;

	@Value("${wapi.appsecret}")
	private String appsecret;

	private static WxMpInMemoryConfigStorage wxConfig = null;
	private static WxMpServiceImpl wmService = null;

	@Bean
	public WxMpInMemoryConfigStorage getWxMpInMemoryConfigStorage() {
		if (wxConfig == null) {
			wxConfig = new WxMpInMemoryConfigStorage();
			wxConfig.setAppId(appid);
			wxConfig.setSecret(appsecret);
		}
		return wxConfig;
	}

	@Bean
	public WxMpServiceImpl getWxMpService() {
		if (wmService == null) {
			wmService = new WxMpServiceImpl();
			if (wxConfig == null) {
				wxConfig = getWxMpInMemoryConfigStorage();
			}
			wmService.setWxMpConfigStorage(wxConfig);
		}
		return wmService;
	}
}
