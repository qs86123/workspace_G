package com.wt.springboot.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.springboot.demo.ProConfig;
import com.wt.springboot.demo.YMLConfig;

@Controller
public class ConfigController {

	@Autowired
	private YMLConfig config;

	@Autowired
	private ProConfig proConfig;

	@RequestMapping("/getProConfig")
	@ResponseBody
	public String getProConfig() {
		System.out.println("pro.my.name=" + proConfig.getName());
		System.out.println("pro.my.value=" + proConfig.getValue());
		System.out.println("moren.config.name=" + proConfig.getMorenName());
		System.out.println("moren.config.value=" + proConfig.getMorenValue());
		return "请在控制台查看输出结果";
	}

	@RequestMapping("/getConfigs")
	@ResponseBody
	public String getConfigs() {
		System.out.println("------------String-----------");
		System.out.println("simpleProp: " + config.getSimpleProp());
		System.out.println("-------------String[]-------------");
		for (String s : config.getArrayProps()) {
			System.out.println("arrayProps: " + s);
		}
		System.out.println("---------------List<Map<String,String>>----------------");
		for (Map<String, String> map : config.getListProp1()) {
			System.out.println("list.size()" + config.getListProp1().size());
			for (String key : map.keySet()) {
				System.out.println("listProp1:" + key + "=" + map.get(key));
			}
		}
		System.out.println("--------------List<String>-----------------");
		for (String s : config.getListProp2()) {
			System.out.println("listProp2: " + s);

		}
		System.out.println("---------------Map<Stirng,String>----------------");
		Map<String, String> mapPros = config.getMapProps();
		for (String key : mapPros.keySet()) {
			System.out.println("mapProps" + key + "=" + mapPros.get(key));
		}
		return "请在控制台查看输出结果";
	}

}
