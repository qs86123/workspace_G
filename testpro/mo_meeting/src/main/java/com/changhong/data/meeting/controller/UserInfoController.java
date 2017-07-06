package com.changhong.data.meeting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.entity.UserInfo;
import com.changhong.data.meeting.service.UserInfoService;

/**
 * 用户信息接口
 * @author wangtao
 *
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService uiService;

	/**
	 * 批量录入用户信息，添加测试数据的时候用
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object saveUser() {
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			UserInfo ui = new UserInfo();
			ui.setName("老王" + i);
			ui.setSex(i % 2 + "");
			ui.setTel("1354131188" + i);
			ui.setCardNum("51018319930101007" + i);
			String id = uiService.save(ui);
			l.add(id);
		}
		return l;
	}

	/**
	 * 根据身份证号获取用户姓名
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/getNameByCardNum")
	@ResponseBody
	public Object getNameByCardNum(@RequestBody JSONObject params) {
		JSONObject result = new JSONObject();
		result.put("error", 0);
		String cardNum = params.getString("cardNum");
		if (cardNum != null && !cardNum.trim().equals("")) {
			UserInfo ui = uiService.findByCardNum(cardNum);
			if (ui != null) {
				result.put("cardNum", ui.getCardNum());
				result.put("name", ui.getName());
				return result;
			}
		}
		result.put("msg", "没有找到相关信息");
		return result;
	}

}
