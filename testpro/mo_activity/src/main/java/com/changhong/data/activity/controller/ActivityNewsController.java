package com.changhong.data.activity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.data.activity.entity.ActivityInfo;
import com.changhong.data.activity.entity.ActivityNews;
import com.changhong.data.activity.service.ActivitiyNewsService;
import com.changhong.data.activity.service.ActivityInfoService;

@Controller
@RequestMapping("/news")
public class ActivityNewsController {

	@Autowired
	private ActivitiyNewsService anService;

	@Autowired
	private ActivityInfoService aiService;

	@RequestMapping("/add")
	@ResponseBody
	public JSONObject addNews(@RequestBody JSONObject params) {
		JSONObject result = new JSONObject();
		try {
			String activityId = params.getString("activityId");
			ActivityInfo ai = aiService.findById(activityId);
			if (ai == null) {
				result.put("error", 1);
				result.put("msg", "会议信息有误，请重试");
				return result;
			}
			JSONArray newsUrls = params.getJSONArray("newsUrls");
			if (newsUrls == null) {
				result.put("error", 1);
				result.put("msg", "参数名不对");
				return result;
			}
			List<ActivityNews> ans = new ArrayList<>();
			for (int i = 0; i < newsUrls.size(); i++) {
				ActivityNews an = new ActivityNews();
				an.setActivityId(activityId);
				an.setUrl(newsUrls.getString(i));
				ans.add(an);
			}
			anService.saveAll(ans);
			result.put("error", 0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("msg", "内部错误：" + e.getMessage());
			return result;
		}
	}

	@RequestMapping("/findNewsByActivityId/{activityId}")
	@ResponseBody
	public JSONObject findNewsByActivityId(@PathVariable("activityId") String activityId) {
		JSONObject json = new JSONObject();
		try {
			List<ActivityNews> ans = anService.findByActivityId(activityId);
			json.put("error", 0);
			json.put("newsUrls", ans);
			return json;
		} catch (Exception e) {
			json.put("error", 1);
			json.put("msg", "内部错误：" + e.getMessage());
			return json;
		}
	}

}
