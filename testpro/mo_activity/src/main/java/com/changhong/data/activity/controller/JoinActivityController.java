package com.changhong.data.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.activity.entity.ActivityInfo;
import com.changhong.data.activity.entity.JoinActivityInfo;
import com.changhong.data.activity.entity.UserOpenid;
import com.changhong.data.activity.service.ActivityInfoService;
import com.changhong.data.activity.service.JoinActivityService;
import com.changhong.data.activity.service.UserOpenidService;
import com.changhong.data.activity.utils.StringUtils;

/**
 * 参加或者退出活动控制器
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2016年11月28日上午11:22:01
 */
@Controller
@RequestMapping("/activity")
public class JoinActivityController {

	@Autowired
	private JoinActivityService jaService;

	@Autowired
	private ActivityInfoService aiService;

	@Autowired
	private UserOpenidService uoService;

	/**
	 * 参加活动
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping("/join")
	@ResponseBody
	public JSONObject joinActivity(@RequestBody JoinActivityInfo entity) {
		JSONObject json = new JSONObject();
		UserOpenid uo = uoService.findByOpenid(entity.getOpenId());
		if (uo == null) {
			json.put("error", 1);
			json.put("msg", "请先进行实名认证");
			return json;
		}
		JoinActivityInfo jai = jaService.findByActivityIdAndCardNum(entity.getActivityId(), uo.getCardNum());
		if (jai != null) {
			json.put("error", 1);
			json.put("msg", "你已经参加了该活动");
			return json;
		}
		ActivityInfo ai = aiService.findById(entity.getActivityId());
		if (ai == null) {
			json.put("error", 1);
			json.put("msg", "该活动信息有误，请尝试重新进入后再试");
			return json;
		}
		try {
			entity.setCardNum(uo.getCardNum());
			jaService.save(entity);
			json.put("error", 0);
			json.put("msg", "参加活动成功");
			return json;
		} catch (Exception e) {
			json.put("error", 1);
			json.put("msg", "内部错误，参加活动失败："+e.getMessage());
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 退出活动
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/quit")
	@ResponseBody
	public JSONObject quitActivity(@RequestBody JSONObject params) {
		JSONObject json = new JSONObject();
		String activityId = params.getString("activityId");
		String openId = params.getString("openId");
		if (StringUtils.isNull(activityId) || StringUtils.isNull(openId)) {
			json.put("error", 1);
			json.put("msg", "活动信息有误，请尝试重新进入后再试");
			return json;
		}
		try {
			UserOpenid uo = uoService.findByOpenid(openId);
			jaService.deleteByActivityIdAndCardNum(activityId, uo.getCardNum());
			json.put("error", 0);
			json.put("msg", "退出活动成功");
			return json;
		} catch (Exception e) {
			json.put("error", 1);
			json.put("msg", "内部错误，退出活动失败："+e.getMessage());
			e.printStackTrace();
			return json;
		}
	}

	@RequestMapping("/isJoin")
	@ResponseBody
	public JSONObject isJoin(@RequestBody JSONObject params) {
		JSONObject json = new JSONObject();
		String activityId = params.getString("activityId");
		String openId = params.getString("openId");
		try {
			if (isJoin(activityId, openId)) {
				json.put("error", 0);
				json.put("isJoin", 0);
				return json;
			}
			json.put("error", 0);
			json.put("isJoin", 1);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", 1);
			json.put("msg", e.getMessage());
			return json;
		}
	}

	public boolean isJoin(String activityId, String openId) throws Exception {
		UserOpenid uo = uoService.findByOpenid(openId);
		if (uo == null)
			throw new Exception("没有此用户认证信息,请先进行实名认证");
		JoinActivityInfo jai = jaService.findByActivityIdAndCardNum(activityId, uo.getCardNum());
		if (jai != null) {
			return true;
		}
		return false;
	}

}
