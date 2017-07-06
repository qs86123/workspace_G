package com.changhong.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.changhong.location.utils.HttpQueryUtil;
import com.netflix.discovery.converters.Auto;

@Controller
public class TestController {

	@Autowired
	RestTemplate restTemplate;
	
	private final String SERVICE_NAME="location-analyse-service";
	
	@RequestMapping("/good")
	@ResponseBody
	public String useserver(){
		try {
			String ll=restTemplate.getForObject("http://"+SERVICE_NAME+"/baidulocation?lng=104.671234&lat=31.470741",String.class);
			System.out.println(ll);
//			System.out.println("-----------------------------------------------");
//			String lll=HttpQueryUtil.getDataFromUrl("http://127.0.0.1:8080/baidulocation?lng=104.671234&lat=31.470741&parse=false");
//			System.out.println("lll"+lll.toString());
			return ll;
		} catch (Exception e) {
			e.printStackTrace();
			return "出错";
		}
	}
	
	@RequestMapping("/goodd")
	@ResponseBody
	public String useserverd(){
		try {
			String ll=HttpQueryUtil.getDataFromUrl("http://127.0.0.1:8080/googlelocation?lng=104.671234&lat=31.470741");
			System.out.println(ll);
			System.out.println("-----------------------------------------------");
			String lll=HttpQueryUtil.getDataFromUrl("http://127.0.0.1:8080/googlelocation?lng=104.671234&lat=31.470741&parse=false");
			System.out.println("lll"+lll.toString());
			return ll;
		} catch (Exception e) {
			e.printStackTrace();
			return "出错";
		}
	}
	
}
