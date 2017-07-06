package com.wt.devtools.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chatOnline")
public class ChatOnlineController {

	private StringBuffer sb = new StringBuffer();

	@RequestMapping("/userLogin")
	@ResponseBody
	public String userLogin(@RequestParam("d") String date, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String old = (String) session.getAttribute("username");
		if (old == null)
			System.out.println("nullll");
		username += "<br/>" + (old == null ? "" : old);
		session.setAttribute("username", username);
		return "true";
	}

	@RequestMapping("/getOnlineList")
	@ResponseBody
	public String getOblineList(@RequestParam("d") String date, HttpServletRequest request) {
		String names = (String) request.getSession().getAttribute("username");
		return names == null ? "" : names;
	}

	@RequestMapping("/getMessageList")
	@ResponseBody
	public String getMessageList(@RequestParam("d") String date) {
		String content = sb.toString();
		return content;
	}

	@RequestMapping("/sendContent")
	@ResponseBody
	public String sendContent(@RequestParam("d") String date,@RequestParam("content")String content) {
		sb.append(date+"<br/>"+content+"<br/>");
		return "true";
	}
}
