package com.changhong.data.meeting.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.entity.MeetingPlanInfo;
import com.changhong.data.meeting.entity.MeetingSeatInfo;
import com.changhong.data.meeting.entity.QRMeeting;
import com.changhong.data.meeting.entity.UserInfo;
import com.changhong.data.meeting.entity.UserOpenid;
import com.changhong.data.meeting.service.MeetingPlanInfoService;
import com.changhong.data.meeting.service.MeetingSeatInfoService;
import com.changhong.data.meeting.service.QRMeetingService;
import com.changhong.data.meeting.service.UserInfoService;
import com.changhong.data.meeting.service.UserOpenidService;
import com.changhong.data.meeting.service.UserQRSignService;
import com.changhong.data.meeting.utils.JsonUtils;

/**
 * 会议计划以及会议计划座位信息接口
 * 
 * @author wangtao
 *
 */
@Controller
@RequestMapping("/meetingPlan")
public class MeetingPlanInfoController {

	@Autowired
	private MeetingPlanInfoService mpiService;

	@Autowired
	private MeetingSeatInfoService msiService;

	@Autowired
	private UserOpenidService uoService;

	@Autowired
	private UserInfoService uiService;

	@Autowired
	private QRMeetingService qrService;

	@Autowired
	private UserQRSignService uqService;

	/**
	 * 保存会议计划和人员座位信息
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/saveMeetingAndSeatInfo")
	@ResponseBody
	public Object saveMeetingSeatInfo(@RequestBody JSONObject params) {
		JSONObject result = new JSONObject();
		result.put("error", 1);
		List<MeetingSeatInfo> list = new ArrayList<MeetingSeatInfo>();
		String roomId = params.getString("roomId");
		String name = params.getString("name");
		String content = params.getString("content");
		String imgMediaId = params.getString("imgMediaId");
		String startTime = params.getString("startTime");
		String endTime = params.getString("endTime");
		if (!checkData(roomId)) {
			result.put("msg", "没有选择会议室");
			return result;
		}
		if (!checkData(name)) {
			result.put("msg", "会议名称不能为空");
			return result;
		}
		if (!checkData(content)) {
			result.put("msg", "会议内容不能为空");
		}
		if (!checkData(startTime) || !checkData(endTime)) {
			result.put("msg", "会议时间不能为空");
			return result;
		}
		// 将时间转换成时间戳存储
		try {
			startTime = dateChange(startTime);
			endTime = dateChange(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
			result.put("msg", "日期解析出错");
		}
		MeetingPlanInfo mpi = new MeetingPlanInfo();
		mpi.setContent(content);
		mpi.setRoomId(roomId);
		mpi.setName(name);
		mpi.setStartTime(startTime);
		mpi.setEndTime(endTime);
		mpi.setImgMediaId(imgMediaId);
		String meetingId = null;
		try {
			meetingId = mpiService.save(mpi);
		} catch (Exception e) {
			result.put("msg", "内部错误，保存会议计划失败");
			return result;
		}
		JSONArray persons = params.getJSONArray("persons");
		for (int i = 0; i < persons.size(); i++) {
			JSONObject json = persons.getJSONObject(i);
			MeetingSeatInfo msi = new MeetingSeatInfo();
			msi.setMeetingId(meetingId);
			msi.setUserId(json.getString("person"));
			msi.setX(json.getDouble("x"));
			msi.setY(json.getDouble("y"));
			msi.setDegree(json.getDouble("r"));
			msi.setSeatFlag(json.getString("seatFlag"));
			list.add(msi);
		}
		try {
			msiService.saveAll(list);
		} catch (Exception e) {
			result.put("msg", "会议计划保存成功,参会人员座位信息保存失败,请进入相应会议计划重新添加");
			return result;
		}
		result.put("error", 0);
		return result;
	}

	/**
	 * 获得所有会议计划信息
	 * 
	 * @return
	 */
	@RequestMapping("/getMeetingPlan")
	@ResponseBody
	public Object getMeetingPlan() {
		List<MeetingPlanInfo> mpis = mpiService.findAll();
		return mpis;
	}

	@RequestMapping("/getMeetingPlanByRoomId/{roomId}")
	@ResponseBody
	public Object getMeetingPlanByRoomId(@PathVariable("roomId") String roomId) {
		List<MeetingPlanInfo> mpis = mpiService.findByRoomId(roomId);
		return mpis;
	}

	/**
	 * 根据会议计划id获取会议计划信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getMeetingPlanById/{id}")
	@ResponseBody
	public Object getMeetingPlanById(@PathVariable("id") String id) {
		MeetingPlanInfo mpi = mpiService.findById(id);
		mpi.setStartTime(dateToYMD(mpi.getStartTime()));
		mpi.setEndTime(dateToYMD(mpi.getEndTime()));
		// MeetingPlanInfo mpi=new MeetingPlanInfo();
		// mpi.setId(id);//如果存在id就更新，不存在就保存
		// mpi.setContent("abdefg");
		// mpiService.save(mpi);
		return mpi;
	}

	/**
	 * 根据会议id获取该会议的所有成员座位信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSeatInfoByMeetingId/{id}")
	@ResponseBody
	public Object getSeatInfoByMeetingId(@PathVariable("id") String id) {
		return msiService.getByMeetingId(id);
	}

	/**
	 * 根据会议id获取该会议的所有成员座位信息包括姓名
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSeatInfoAndNameByMeetingId/{id}")
	@ResponseBody
	public Object getSeatInfoByMeetingIdIncludeName(@PathVariable("id") String id) {
		JSONArray result = new JSONArray();
		List<MeetingSeatInfo> msis = msiService.getByMeetingId(id);
		// JSONArray jsonMsis = new JSONArray();
		// jsonMsis.addAll(msis);
		List<String> cardNums = new ArrayList<String>();
		for (MeetingSeatInfo msi : msis) {
			if (msi.getUserId() != null || !msi.getUserId().trim().equals(""))
				cardNums.add(msi.getUserId());
		}
		List<UserInfo> uis = uiService.findByCardNumIn(cardNums);
		// map集合以身份证号码为key，名字为value存储，便于好取
		Map<String, String> map = new HashMap<String, String>();
		for (UserInfo ui : uis) {
			map.put(ui.getCardNum(), ui.getName());
		}
		// for (int i = 0; i < jsonMsis.size(); i++) {
		// String cardNum = jsonMsis.getJSONObject(i).getString("userId");
		// String name = map.get(cardNum);
		// JSONObject j = jsonMsis.getJSONObject(i);
		// j.put("name", name == null ? cardNum : name);
		// result.add(j);
		// }
		for (MeetingSeatInfo m : msis) {
			JSONObject j = JsonUtils.POJOtoJson(MeetingSeatInfo.class, m);
			String cardNum = m.getUserId();
			String name = map.get(cardNum);
			j.put("name", name == null ? cardNum : name);
			result.add(j);
		}
		return result;
	}

	/**
	 * 根据会议信息和openid获取该成员座位信息
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/getUserSeat")
	@ResponseBody
	public Object getUserSeat(@RequestBody JSONObject params) {
		JSONObject result = new JSONObject();
		String openid = params.getString("openid");
		String meetingId = params.getString("meetingId");
		UserOpenid uo = uoService.findByOpenid(openid);
		if (uo == null) {
			result.put("error", "没有找到该成员信息");
			return result;
		}
		String cardNum = uo.getCardNum();// 根据openid获取到身份证号码
		MeetingSeatInfo msi = msiService.getByMeetingIdAndUserId(meetingId, cardNum);// 根据身份证号码和会议id获取会议座位
		if (msi == null) {
			result.put("error", "没有找到该成员座位信息");
			return result;
		}
		return msi;
	}

	/**
	 * 更新某次会议计划
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateMeetingAndSeatInfo")
	@ResponseBody
	public Object updateMeetingPlan(@RequestBody JSONObject params) {
		JSONObject result = new JSONObject();
		result.put("error", 1);
		String meetingId = params.getString("meetingId");
		String roomId = params.getString("roomId");
		String name = params.getString("name");
		String content = params.getString("content");
		String imgMediaId = params.getString("imgMediaId");
		String startTime = params.getString("startTime");
		String endTime = params.getString("endTime");
		if (!checkData(roomId)) {
			result.put("msg", "没有选择会议室");
			return result;
		}
		if (!checkData(name)) {
			result.put("msg", "会议名称不能为空");
			return result;
		}
		if (!checkData(content)) {
			result.put("msg", "会议内容不能为空");
		}
		if (!checkData(startTime) || !checkData(endTime)) {
			result.put("msg", "会议时间不能为空");
			return result;
		}
		// 将时间转换成时间戳存储
		try {
			startTime = dateChange(startTime);
			endTime = dateChange(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
			result.put("msg", "日期解析出错");
		}
		MeetingPlanInfo mpi = new MeetingPlanInfo();
		mpi.setContent(content);
		mpi.setRoomId(roomId);
		mpi.setName(name);
		mpi.setStartTime(startTime);
		mpi.setEndTime(endTime);
		mpi.setId(meetingId);
		mpi.setImgMediaId(imgMediaId);
		try {
			meetingId = mpiService.save(mpi);
		} catch (Exception e) {
			result.put("msg", "内部错误，更新会议计划失败");
			return result;
		}
		JSONArray persons = params.getJSONArray("persons");
		List<MeetingSeatInfo> list = new ArrayList<MeetingSeatInfo>();
		for (int i = 0; i < persons.size(); i++) {
			JSONObject json = persons.getJSONObject(i);
			MeetingSeatInfo msi = new MeetingSeatInfo();
			msi.setMeetingId(meetingId);
			msi.setUserId(json.getString("person"));
			msi.setX(json.getDouble("x"));
			msi.setY(json.getDouble("y"));
			msi.setDegree(json.getDouble("r"));
			msi.setSeatFlag(json.getString("seatFlag"));
			list.add(msi);
		}
		try {
			// 根据meetingId删除该会议所有座位信息，然后重新保存新的数据
			msiService.deleteByMeetingId(meetingId);
			msiService.saveAll(list);
		} catch (Exception e) {
			result.put("msg", "会议计划更新成功,参会人员座位信息更新失败,请进入相应会议计划重新尝试");
			return result;
		}
		result.put("error", 0);
		return result;
	}

	@RequestMapping("/deletePlanByMeetingId/{meetingId}")
	@ResponseBody
	public Object deletePlanByMeetingId(@PathVariable("meetingId") String meetingId) {
		JSONObject result = new JSONObject();
		try {
			// 最后删除会议计划信息，万一删除的时候出了差错，会议计划没有被删除，就还存在会议id，下次可以继续删除相应信息
			MeetingPlanInfo mpi = mpiService.findById(meetingId);
			QRMeeting qrm = qrService.findByMeetingId(mpi.getId());
			if (qrm != null) {// 如果不为空说明有会议计划的签到信息或者二维码信息
				// 根据meetingId删除该会议计划的签到信息
				uqService.deleteByMeetingId(meetingId);
				// 删除会议计划签到二维码信息
				qrService.deleteByMeetingId(meetingId);
			}
			// 删除会议计划座位信息
			msiService.deleteByMeetingId(meetingId);
			mpiService.deleteById(meetingId);
		} catch (Exception e) {
			result.put("error", 1);
			result.put("msg", "内部错误，删除失败");
		}
		result.put("error", 0);
		return result;
	}

	/**
	 * 根据会议id获取推送信息所需要的openid,会议信息内容,该接口暂未用
	 * 
	 * @param meetingId
	 * @return
	 */
	@RequestMapping("/getMeetingPushInfoByMeetingId/{meetingId}")
	@ResponseBody
	public Object getUserOpenidByMeetingId(@PathVariable("meetingId") String meetingId) {
		JSONObject result = new JSONObject();
		// 根据meetingId获取到座位信息中每个人的cardNum，再根据cardNum去查询对应的openid，每个cardNum可能有多个openid
		List<MeetingSeatInfo> msis = msiService.getByMeetingId(meetingId);
		List<String> strs = new ArrayList<String>();
		for (MeetingSeatInfo msi : msis) {
			strs.add(msi.getUserId());
		}
		List<UserOpenid> uos = uoService.findByCardNums(strs);
		List<String> openids = new ArrayList<String>();
		for (UserOpenid uo : uos) {
			openids.add(uo.getOpenid());
		}
		result.put("openids", openids);
		// 根据openid查询会议信息
		MeetingPlanInfo mpi = mpiService.findById(meetingId);
		// 封装信息
		result.put("name", mpi.getName());
		result.put("content", mpi.getContent());
		result.put("startTime", mpi.getStartTime());
		result.put("endTime", mpi.getEndTime());
		return result;
	}

	/**
	 * 代签接口
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/replace")
	@ResponseBody
	public Object replace(@RequestBody JSONObject params) {
		JSONObject result = new JSONObject();
		result.put("error", 1);
		String meetingId = params.getString("meetingId");
		String userOpenid = params.getString("userOpenid");
		String replaceCardNum = params.getString("replaceCardNum");
		System.out.println("meetintId" + meetingId);
		System.out.println("userOpenid" + userOpenid);
		System.out.println("replaceCardNum" + replaceCardNum);
		UserOpenid user = uoService.findByOpenid(userOpenid);
		if (user == null) {
			result.put("msg", "没有找到你的信息，请检查是否实名认证");
			return result;
		}
		String userId = user.getCardNum();
		MeetingSeatInfo msi = msiService.getByMeetingIdAndUserId(meetingId, userId);
		if (msi == null) {
			result.put("msg", "该会议不存在");
			return result;
		}
		List<String> cardNums = new ArrayList<String>();
		cardNums.add(replaceCardNum);
		List<UserOpenid> replace = uoService.findByCardNums(cardNums);
		if (replace == null || replace.size() == 0) {
			result.put("msg", "你输入的代签人员未找到");
			return result;
		}
		MeetingSeatInfo msiRep = msiService.getByMeetingIdAndUserId(meetingId, replaceCardNum);
		if (msiRep != null) {
			result.put("msg", "该人员已参与该会议，不允许代签");
			return result;
		}
		MeetingSeatInfo msiRep2 = msiService.getByMeetingIdAndReplaceId(meetingId, replaceCardNum);
		if (msiRep2 != null) {
			result.put("msg", "该人员已带别个参与会议，不允许重复代签");
			return result;
		}
		msi.setReplaceId(replaceCardNum);// 把代签的人保存到代签字段
		// 重新保存该条数据
		try {
			msiService.save(msi);
			result.put("error", 0);
			result.put("msg", "代签成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "内部错误，代签失败");
			return result;
		}

	}

	@RequestMapping("/replaceCheck")
	@ResponseBody
	public Object replaceCheck(@RequestBody JSONObject params) {
		JSONObject result = new JSONObject();
		result.put("error", 1);
		String meetingId = params.getString("meetingId");
		String replaceCardNum = params.getString("replaceCardNum");
		MeetingSeatInfo msi = msiService.getByMeetingIdAndUserId(meetingId, replaceCardNum);
		if (msi != null) {
			result.put("msg", "该人员已参与该会议，不允许代签");
			return result;
		}
		return result;
	}

	private boolean checkData(String data) {
		if (data == null || data.trim().equals(""))
			return false;
		return true;
	}

	public String dateChange(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d = sdf.parse(time);
		long p = d.getTime();
		return p + "";
	}

	public String dateToYMD(String time) {
		long t = Long.parseLong(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d = new Date();
		d.setTime(t);
		String tt = sdf.format(d);
		return tt;
	}

}
