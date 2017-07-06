package com.changhong.data.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.activity.entity.ActivityInfo;
import com.changhong.data.activity.entity.CommentInfo;
import com.changhong.data.activity.entity.JoinActivityInfo;
import com.changhong.data.activity.service.ActivitiyNewsService;
import com.changhong.data.activity.service.ActivityInfoService;
import com.changhong.data.activity.service.CommentInfoService;
import com.changhong.data.activity.service.JoinActivityService;

/**
 * 
 * 活动管理controller
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2016年11月25日下午2:06:48
 */
@Controller
@RequestMapping("/activity")
public class ActivityInfoController {

	@Autowired
	private ActivityInfoService aiService;

	@Autowired
	private JoinActivityService jaService;

	@Autowired
	private CommentInfoService ciService;

	@Autowired
	private ActivitiyNewsService anService;

	/**
	 * 添加，更新活动
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping({ "/add", "/update" })
	@ResponseBody
	public Object addActivity(@RequestBody ActivityInfo entity) {
		JSONObject json = new JSONObject();
		try {
			aiService.save(entity);
			json.put("error", 0);
			json.put("msg", "操作成功");
			return json;
		} catch (Exception e) {
			json.put("error", 1);
			json.put("msg", "内部错误，操作失败：" + e.getMessage());
			return json;
		}
	}

	/**
	 * 根据id查询活动
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById/{activityId}")
	@ResponseBody
	public JSONObject findById(@PathVariable("activityId") String activityId) {
		JSONObject json = new JSONObject();
		try {
			ActivityInfo ai = aiService.findById(activityId);
			if (ai == null) {
				json.put("error", 1);
				json.put("msg", "该活动不存在，请重试");
				return json;
			}
			List<JoinActivityInfo> jais = jaService.findByActivityId(activityId);
			List<CommentInfo> cis = ciService.findByActivityId(activityId);
			json.put("activity", ai);
			json.put("joinPersons", jais);
			json.put("comments", cis);
			json.put("error", 0);
			return json;
		} catch (Exception e) {
			json.put("error", 1);
			json.put("msg", "内部错误：" + e.getMessage());
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 根据id删除活动
	 * 
	 * @param id
	 */
	@RequestMapping("/deleteById/{activityId}")
	@ResponseBody
	public JSONObject deleteById(@PathVariable("activityId") String activityId) {
		JSONObject json = new JSONObject();
		try {
			// 删除活动相关新闻链接
			anService.deleteByActivityId(activityId);
			// 删除参加活动人员信息
			jaService.deleteByActivityId(activityId);
			// 删除该活动评论信息
			ciService.deleteByActivityId(activityId);
			// 删除该活动
			aiService.deleteById(activityId);
			json.put("error", 0);
			json.put("msg", "删除活动成功");
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", 1);
			json.put("msg", "删除活动失败");
			return json;
		}
	}

	/**
	 * 查询所有活动
	 * 
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public Object findAll() {
		List<ActivityInfo> ais = aiService.findAll();
		return ais;
	}

	/**
	 * 按名字模糊查询
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/findByNameContaining")
	@ResponseBody
	public List<ActivityInfo> findByNameContaining(@RequestBody JSONObject params) {
		List<ActivityInfo> ais = aiService.findByNameContaining(params.getString("name"));
		return ais;
	}

	/**
	 * 根据活动状态查询的活动
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/findByStatus/{status}")
	@ResponseBody
	public List<ActivityInfo> findByStatus(@PathVariable("status") Integer status) {
		List<ActivityInfo> ais = aiService.findByStatus(status);
		return ais;
	}

	@RequestMapping("/findByLngAndLat")
	@ResponseBody
	public JSONObject findByLngAndLat(@RequestBody JSONObject params) {
		JSONObject json = new JSONObject();
		double lng = 0d;
		double lat = 0d;
		double radius = 0d;
		try {
			lng = params.getDouble("lng");
			lat = params.getDouble("lat");
			radius = params.getDoubleValue("radius");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", 1);
			json.put("msg", "参数有误");
			return json;
		}
		try {
			List<ActivityInfo> ais = aiService.getPositions(lng, lat, radius);
			json.put("error", 0);
			json.put("nearby", ais);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", 1);
			json.put("msg", "内部有误，请重试");
			return json;
		}
	}

}
