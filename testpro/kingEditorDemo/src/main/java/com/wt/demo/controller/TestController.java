package com.wt.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

@Controller
public class TestController {

	@RequestMapping("/test")
	public String test() {
		return "item-add";
	}

	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upload(@RequestParam("uploadFile") MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String name = file.getName();
		System.out.println("originalFilename:" + originalFilename + "name:" + name);
		JSONObject json = new JSONObject();
		json.put("url", "aaaa");
		json.put("error", 0);
		return json;
	}

}
