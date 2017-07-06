package com.wt.devtools.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wt.devtools.entity.Abcd;
import com.wt.devtools.entity.Item;
import com.wt.devtools.entity.Person;
import com.wt.devtools.entity.Statement;

@Controller
public class MyController {

	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request){
		String strName = request.getParameter("name");
		strName=URLDecoder.decode(strName);
		String strHtml = "<div class='clsShow'>";
		strHtml += "姓名：" + strName + "<br/>";
		strHtml += "性别：nan<br/>";
		strHtml += "邮箱：12345678910@qq.com<hr/>";
		strHtml += "</div>";
		return strHtml;
	}

	@RequestMapping("/statement")
	@ResponseBody
	public JSONObject test1(@RequestBody Statement sm) {
		System.out.println(sm);
		JSONObject json=new JSONObject();
		if(sm.getContent().contains("123")){
			json.put("error", "1");
			return json;
		}
		json.put("error", "0");
		json.put("msg", "succes");
		return json;
	}
	
}
