package com.changhong.data.meeting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.entity.MeetingPlanInfo;
import com.changhong.data.meeting.entity.MeetingSeatInfo;
import com.changhong.data.meeting.entity.QRMeeting;
import com.changhong.data.meeting.entity.UserInfo;
import com.changhong.data.meeting.entity.UserOpenid;
import com.changhong.data.meeting.entity.UserQRSign;
import com.changhong.data.meeting.service.MeetingPlanInfoService;
import com.changhong.data.meeting.service.MeetingSeatInfoService;
import com.changhong.data.meeting.service.QRMeetingService;
import com.changhong.data.meeting.service.UserInfoService;
import com.changhong.data.meeting.service.UserOpenidService;
import com.changhong.data.meeting.service.UserQRSignService;

/**
 * 会议计划创建对应签到二维码接口
 * @author wangtao
 *
 */
@Controller
@RequestMapping("/qrmeeting")
public class QRMeetingController {

	@Autowired
	private QRMeetingService qrMeetingService;

	@Autowired
	private MeetingPlanInfoService mpiService;

	@Autowired
	private MeetingSeatInfoService msiService;

	@Autowired
	private UserInfoService uiService;

	@Autowired
	private UserOpenidService uoService;

	@Autowired
	private UserQRSignService uqService;

	@Value("${wapi.account_code.get}")
	private String getqrByTicketUrl;

	/**
	 * 创建某个会议签到的二维码，先从数据库查，如果存在直接返回
	 * 
	 * @param meetingId
	 * @return
	 */
	@RequestMapping("/createQR/{meetingId}")
	@ResponseBody
	public Object createQR(@PathVariable("meetingId") String meetingId) {
		JSONObject result = new JSONObject();
		MeetingPlanInfo mpi = mpiService.findById(meetingId);
		if (mpi == null) {
			result.put("error", 1);
			result.put("msg", "没有该会议信息或会议已取消，不能生成签到二维码");
			return result;
		}
		result.put("error", 0);
		QRMeeting qrmeeting = qrMeetingService.findByMeetingId(meetingId);
		if (qrmeeting != null) {
			// 查询数据库是否有该会议的二维码，如果有直接返回
			result.put("url", getqrByTicketUrl + qrmeeting.getQrTicket());
			return result;
		}
		JSONObject json = qrMeetingService.createQR(meetingId);
		if (json.containsKey("ticket")) {
			// 如果有这个字段，表示二维码创建成功，保存二维码和会议信息,保存之前先删除已存在的该会议的二维码
			String ticket = json.getString("ticket");
			QRMeeting q = new QRMeeting();
			System.out.println(json.getString("scene_id"));
			q.setQrId(json.getString("scene_id"));
			q.setMeetingId(meetingId);
			q.setQrTicket(ticket);
			qrMeetingService.save(q);
			result.put("url", getqrByTicketUrl + ticket);
			return result;
		}
		result.put("error", 1);
		result.put("msg", json);
		return json;
	}

	/**
	 * 根据会议id查询会议签到结果
	 * 
	 * @param meetingId
	 * @return
	 */
	@RequestMapping("/qdresult/{meetingId}")
	@ResponseBody
	public Object getQdResult(@PathVariable("meetingId") String meetingId) {
		JSONObject result = new JSONObject();
		// 根据meetingId查询所有参会人员信息
		List<MeetingSeatInfo> msis = msiService.getByMeetingId(meetingId);
		List<String> userIds = new ArrayList<String>();
		List<String> daiqianName = new ArrayList<String>();
		Map<String, String> daiqianMap = new HashMap<String, String>();
		// 获取所有参会人员的cardNum
		for (MeetingSeatInfo msi : msis) {
			if (msi.getReplaceId() != null && !msi.getReplaceId().equals("")) {
				String[] s = getDaiqianName(msi.getUserId(), msi.getReplaceId());
				if (daiqianMap.get(s[0]) != null && !daiqianMap.get(s[0]).trim().equals("")) {
					// 当这个人帮多个人签到时，直接将需要签到的人追加在后面，避免数据覆盖
					daiqianMap.put(s[0], daiqianMap.get(s[0]) + "," + s[1]);
				} else {
					daiqianMap.put(s[0], s[1]);// s[0]代替s[1]签到
				}
			}
			userIds.add(msi.getUserId());// 所有需要签到的人
		}
		// 查询所有参会人的名字
		List<UserInfo> uis = uiService.findByCardNumIn(userIds);
		List<String> userNames = new ArrayList<String>();
		for (UserInfo ui : uis) {
			userNames.add((ui.getName() == null || ui.getName().trim().equals("")) ? ui.getCardNum() : ui.getName());
		}
		userNames.addAll(daiqianName);
		// 通过二维码的meetingId找到已签到的人员openid
		List<UserQRSign> qds = uqService.findByMeetingId(meetingId);
		List<String> openids = new ArrayList<String>();
		for (UserQRSign qd : qds) {
			openids.add(qd.getOpenid());
		}
		// 根据已签到的人员你的openid找到cardNum
		List<UserOpenid> uos = uoService.findByOpenidIn(openids);
		List<String> qdCardNums = new ArrayList<String>();
		for (UserOpenid uo : uos) {
			qdCardNums.add(uo.getCardNum());
		}
		// 根据cardNum找到已签到人员的名字
		List<UserInfo> qdUsers = uiService.findByCardNumIn(qdCardNums);
		List<String> qdNames = new ArrayList<String>();
		for (UserInfo u : qdUsers) {
			qdNames.add((u.getName() == null || u.getName().trim().equals("")) ? u.getCardNum() : u.getName());
		}
		// 所有需要签到的人中吧已签到的移除
		for (String s : qdNames) {
			String benren = daiqianMap.get(s);
			// 这里如果本人不等于空，就表示s需要帮别个代签
			userNames.remove(s);
			// map中key是实际签到的人，就是这里的s，value是本人
			if (benren != null && !benren.equals("")) {
				// 如果是帮多个人代签，刚保存在map里面的数据是以逗号隔开的，这里以逗号分隔再取出来
				String[] benrens = benren.split(",");
				for (String b : benrens) {
					userNames.remove(b);
				}
				qdNames.remove(s);
				qdNames.add(s + " 代 " + benren);
			}
		}
		result.put("qdUsers", qdNames);
		result.put("noqd", userNames);
		return result;
	}

	private String[] getDaiqianName(String userId, String replaceId) {
		UserInfo user = uiService.findByCardNum(userId);
		UserInfo replace = uiService.findByCardNum(replaceId);
		String strUser = "";
		String strReplace = "";
		strUser = user == null ? userId
				: (user.getName() == null || user.getName().trim().equals("")) ? "未实名" : user.getName();
		strReplace = replace == null ? replaceId
				: (replace.getName() == null || replace.getName().trim().equals("")) ? "未实名" : replace.getName();
		return new String[] { strReplace, strUser };
	}

}
