package com.changhong.data.activity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.activity.entity.ActivityInfo;
import com.changhong.data.activity.entity.CommentInfo;
import com.changhong.data.activity.service.ActivityInfoService;
import com.changhong.data.activity.service.CommentInfoService;

/**
 * 活动评论控制器
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2016年11月25日下午2:29:15
 */
@Controller
@RequestMapping("/comment")
public class CommentInfoController {

	@Autowired
	private CommentInfoService ciService;

	@Autowired
	private ActivityInfoService aiService;

	/**
	 * 添加评论
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object addComment(@RequestBody CommentInfo entity) {
		JSONObject json = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setTime(sdf.format(new Date()));
		if (entity.getActivityId() == null || entity.getActivityId().trim().equals("")) {
			json.put("error", 1);
			json.put("msg", "该活动信息有误，请重新进入后再试");
			return json;
		}
		try {
			ActivityInfo ai = aiService.findById(entity.getActivityId());
			if (ai == null) {
				json.put("error", 1);
				json.put("msg", "该活动信息有误，请重新进入后再试");
				return json;
			}
			ciService.save(entity);
			json.put("error", 0);
			json.put("msg", "评论成功");
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", 0);
			json.put("msg", "内部错误，评论失败："+e.getMessage());
			return json;
		}
	}

}
