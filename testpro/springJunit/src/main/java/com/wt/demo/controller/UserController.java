package com.wt.demo.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wt.demo.entity.User;
import com.wt.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService us;
	
	@RequestMapping(value="/user/user")
	@ResponseBody
	public User returnJson() {
		return new User("测试name", "测试sex", 23);
	}

	@RequestMapping("/test")
	@ResponseBody
	public User test() {
		return us.findUserByName("wt1");
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	public String test2() {
		try {
			us.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@RequestMapping(value="/test/{id}",method=RequestMethod.POST)
	@ResponseBody
	public String returnTestStr(@PathVariable Integer id) {
		return "id=" + id;
	}
	
	
	/////////////////////////////////////////
	
	@RequestMapping(value="/getParams")
	@ResponseBody
	public String getParams(HttpServletRequest request) {
		String a=request.getParameter("a");
		String c=request.getParameter("c");
		return "params: a="+a+": c="+c;
	}
	
	@RequestMapping(value="/postParams",method=RequestMethod.POST)
	@ResponseBody
	public String postParams(HttpServletRequest request) {
		String a=request.getParameter("a");
		String c=request.getParameter("c");
		return "params: a="+a+": c="+c;
	}
	
	@RequestMapping(value="/postJson",method=RequestMethod.POST)
	@ResponseBody
	public User postJson(@RequestBody User user) {
		return user;
	}
	
	//////////////////////////////////
	@RequestMapping("/toforward")
	public String forward() {
		return "myforward";
	}
	
	//返回一张页面
	@RequestMapping("/getForward")
	public String getForward() {
		return "success";
	}
	
	//请求转发到一个返回页面的路径
	@RequestMapping("/getForward2")
	public String getForward2() {
		return "forward:/toforward";
	}
	
	//请求转发到一个返回body的路径
	@RequestMapping(value="/getForward3",method=RequestMethod.GET)
	public String getForward3() {
		return "forward:/user/user";
	}
	
	//重定向到一个返回页面路径
	@RequestMapping("/getRedirect")
	public String getRedirect() {
		return "redirect:/toforward";
	}
	
	//重定向到一个返回body的路径
	@RequestMapping(value="/getRedirect2",method=RequestMethod.GET)
	public String getRedirect2() {
		return "redirect:/user/user";
	}
	
	@RequestMapping("/aaa")
	@ResponseBody
	public String aaa(){
		return "aaa";
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file")MultipartFile file){
		System.out.println("aaaaaaaaaa");
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		byte[] b=new byte[2048];
		try {
			file.getInputStream().read(b);
			System.out.println(new String(b,"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "aaa";
	}
	
	@RequestMapping("/upload2")
	@ResponseBody
	public String upload(@RequestParam("file")MultipartFile file,
			@RequestParam("abcd")String value,
			HttpServletRequest re){
		System.out.println("aaaaaaaaaa");
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
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
	
	@RequestMapping("/upload3")
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
	
	@RequestMapping("/postToInputStream")
	@ResponseBody
	public String postToInputStream(HttpServletRequest request){
		try {
			InputStream is=request.getInputStream();
			StringBuffer sb=new StringBuffer();
			int len=-1;
			byte[] b=new byte[1024];
			while((len=is.read(b))!=-1){
				sb.append(new String(b, 0, len, "utf-8"));
			}
			return "success:"+sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
}
