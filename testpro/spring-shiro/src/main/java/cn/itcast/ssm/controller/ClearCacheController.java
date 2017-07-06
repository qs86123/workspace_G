package cn.itcast.ssm.controller;

import org.omg.CORBA.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.shiro.CustomRealm;

@Controller
public class ClearCacheController {

	@Autowired
	private CustomRealm cr;
	
	@RequestMapping("clearCache")
	public void clearCache(){
		cr.clearCache();
	}
	
}
