package com.changhong.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.location.service.BaiduLocationPushServiceImpl;
import com.changhong.location.service.GoogleLocationPushServiceImpl;

@Controller
public class LocationController {

	@Autowired
	private BaiduLocationPushServiceImpl bps;

	@Autowired
	private GoogleLocationPushServiceImpl googleps;

	@RequestMapping("/baidulocation")
	@ResponseBody
	public Object getBaiduLocation(@RequestParam("lng") String lng, @RequestParam("lat") String lat,
			@RequestParam(value="parse",required=false,defaultValue="true") String parse) {
		System.out.println("lng:" + lng + "    lat:" + lat);
		boolean isparse = true;
		try {
			isparse = Boolean.parseBoolean(parse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bps.getLocation(lng, lat, isparse);
	}

	@RequestMapping("/googlelocation")
	@ResponseBody
	public Object getGoogleLocation(@RequestParam("lng") String lng, @RequestParam("lat") String lat,
			@RequestParam(value="parse",required=false,defaultValue="true") String parse) {
		boolean isparse = true;
		try {
			isparse = Boolean.parseBoolean(parse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return googleps.getLocation(lng, lat, isparse);
	}

}
