package com.changhong.data.meeting.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	public String handleError() {
		// 错误页面不能用英文
		return "404.html";
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
