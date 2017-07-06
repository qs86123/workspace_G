package cn.itcast.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.ActiveUser;

@Controller
public class FirstAction {
	// 系统首页
	@RequestMapping("/first")
	public String first(HttpServletRequest re, Model model) throws Exception {

		Subject subject = SecurityUtils.getSubject();
		// 从shiro的session中取出在realm中放在SimpleAuthenticationInfo中的身份信息
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		// 将身份信息传递到页面
		re.getSession().setAttribute("activeUser", activeUser);
		// 效果一样
		// model.addAttribute("activeUser", activeUser);

		return "/first";
	}

	// 欢迎页面
	@RequestMapping("/welcome")
	public String welcome(Model model) throws Exception {

		return "/welcome";

	}
}
