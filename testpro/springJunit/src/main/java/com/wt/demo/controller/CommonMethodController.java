package com.wt.demo.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wt.demo.entity.User;

@Controller
@RequestMapping("/common")
public class CommonMethodController {

	@RequestMapping(value = "/getParams")
	@ResponseBody
	public String getParams(HttpServletRequest request) {
		String a = request.getParameter("a");
		String c = request.getParameter("c");
		return "params: a=" + a + ": c=" + c;
	}

	@RequestMapping(value = "/user/user")
	@ResponseBody
	public User returnJson() {
		return new User("测试name", "测试sex", 23);
	}

	@RequestMapping("/postToInputStream")
	@ResponseBody
	public String postToInputStream(HttpServletRequest request) {
		try {
			InputStream is = request.getInputStream();
			StringBuffer sb = new StringBuffer();
			int len = -1;
			byte[] b = new byte[1024];
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len, "utf-8"));
			}
			return "success:" + sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping("/getParamsandInputStream")
	@ResponseBody
	public String getParamsandInputStream(HttpServletRequest request) {
		try {
			String a = request.getParameter("a");
			String c = request.getParameter("c");
			InputStream is = request.getInputStream();
			StringBuffer sb = new StringBuffer();
			int len = -1;
			byte[] b = new byte[1024];
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len, "utf-8"));
			}
			return "success:inputStream=" + sb.toString() + ";params:a=" + a + ",c=" + c;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping("/postPojo_Param_InputStream")
	@ResponseBody
	public String upload3(@RequestParam("abcd") String value, User user, HttpServletRequest request) {
		try {
			System.out.println("aaaaaaaaaa");
			System.out.println(user);
			System.out.println("abcd=" + value + ":" + request.getParameter("p"));
			InputStream is = request.getInputStream();
			StringBuffer sb = new StringBuffer();
			int len = -1;
			byte[] b = new byte[1024];
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len, "utf-8"));
			}
			return "user:"+user+"\nabcd="+value+",p="+request.getParameter("p")+"\ninputStream="+sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping("/postPojo_Param_File")
	@ResponseBody
	public String upload3(@RequestParam("file")MultipartFile file,
			@RequestParam("abcd")String value,
			User user,
			HttpServletRequest re){
		System.out.println("aaaaaaaaaa");
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(user);
		System.out.println("abcd="+value+":"+re.getParameter("abcd"));
		byte[] b=new byte[2048];
		try {
			file.getInputStream().read(b);
			System.out.println(new String(b,"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "aaa";
	}
	
}
