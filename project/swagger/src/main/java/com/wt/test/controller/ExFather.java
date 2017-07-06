package com.wt.test.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;

@Controller
public class ExFather {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Object exceptionHandler(Exception ex) throws IOException {
		JSONObject exJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		exJson.put("code", HttpStatus.BAD_REQUEST.value());
		exJson.put("errMsg", ex.getMessage());
		return exJson;
	}
}
