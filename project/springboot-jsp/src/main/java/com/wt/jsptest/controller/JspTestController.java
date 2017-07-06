package com.wt.jsptest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JspTestController {

	@RequestMapping(value = "/tojsp", method = RequestMethod.GET)
	public ModelAndView toUeditor(HttpServletRequest req) {
		System.out.println("进来了:" + req.getServletPath());
		ModelAndView view = new ModelAndView();
		view.setViewName("WEB-INF/index.jsp");
		// 如果properties文件中配置了前后缀就这样写
		// view.setViewName("index");
		return view;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toUeditord() {
		System.out.println("进来了a");
		return "index";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String toUeditordview() {
		System.out.println("进来了aa");
		return "WEB-INF/index.jsp";
	}
}
