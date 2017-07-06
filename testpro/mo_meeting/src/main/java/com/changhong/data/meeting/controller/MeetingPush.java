package com.changhong.data.meeting.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.entity.MeetingPlanInfo;
import com.changhong.data.meeting.entity.MeetingSeatInfo;
import com.changhong.data.meeting.entity.UserOpenid;
import com.changhong.data.meeting.service.MeetingPlanInfoService;
import com.changhong.data.meeting.service.MeetingSeatInfoService;
import com.changhong.data.meeting.service.UserOpenidService;

import sun.misc.BASE64Decoder;

/**
 * 微信推送消息接口
 * 
 * @author wangtao
 *
 */
@Controller
@RequestMapping("/meet")
public class MeetingPush {

	@Autowired
	private MeetingPushService mpService;

	@Autowired
	private MeetingPlanInfoService mpiService;

	@Autowired
	private MeetingSeatInfoService msiService;

	@Autowired
	private UserOpenidService uoService;

	@RequestMapping("/pushMeetingInfoByMeetingId/{meetingId}")
	@ResponseBody
	public Object pushMeetingInfo(@PathVariable("meetingId") String meetingId) {
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
		// 根据openid查询会议信息
		MeetingPlanInfo mpi = mpiService.findById(meetingId);
		// 微信群发推送
		String author = "";
		String title = mpi.getName();
		String content = mpi.getContent();
		String contentUrl = "http://10.7.73.88:8081/momx/relay.html&response_type=code&scope=snsapi_userinfo&state="
				+ meetingId + "#wechat_redirect";
		String digest = "";
		String imgMediaId = mpi.getImgMediaId();
		if (imgMediaId == null || imgMediaId.trim().equals("")) {
			JSONObject json = new JSONObject();
			json.put("error", "该会议没有保存过会议图片，请保存图片后再试");
		}
		if (openids == null || openids.size() < 2) {
			JSONObject json = new JSONObject();
			json.put("error", "开会人数小于两个，不允许推送");
		}
		return mpService.pushMeetingInfo(imgMediaId, openids, author, title, content, contentUrl, digest);
	}

	/**
	 * 上传素材图片，返回图片的mediaid
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/meetingImageBase")
	@ResponseBody
	public Object uploadMeetingImage(@RequestParam("media") MultipartFile file) {
		JSONObject json = new JSONObject();
		String result = mpService.uploadMpnewsFile2(file);
		if (result == null || result.equals("")) {
			json.put("error", 1);
			json.put("msg", "上传图片错误");
		}
		json.put("error", 0);
		json.put("msg", result);
		return json;
	}

	@RequestMapping("/meetingImage")
	@ResponseBody
	public Object uploadMeetingImage(@RequestBody JSONObject params) {
		String file = params.getString("file");
		file = file.substring(file.indexOf(",") + 1);
		System.out.println(file);
		JSONObject json = new JSONObject();
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(file);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			InputStream in = new ByteArrayInputStream(bytes);
			String result = mpService.testuploadfile("xxx.png", in);
			if (result == null || result.equals("")) {
				json.put("error", 1);
				json.put("msg", "上传图片错误");
				return json;
			}
			json.put("error", 0);
			json.put("msg", result);
		} catch (Exception e) {
			json.put("error", 1);
			json.put("msg", "上传图片错误");
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 上传素材图片，返回图片的图片url
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/meetingImagegeturl")
	@ResponseBody
	public Object uploadMeetingImagegetUrl() {
		return mpService.getuploadImgUrl();
	}

	/**
	 * 根据msgid删除群发消息
	 * 
	 * @param pushid
	 * @return
	 */
	// @RequestMapping("/delMeetingMsg/{msgid}")
	// @ResponseBody
	// public Object delmeetingmsg(@PathVariable("msgid") Long msgid) {
	// JSONObject json = mpService.delmeetingMsg(msgid);
	// return json;
	//
	// }

}
