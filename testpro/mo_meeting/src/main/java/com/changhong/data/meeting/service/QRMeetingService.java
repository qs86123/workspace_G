package com.changhong.data.meeting.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.dao.QRMeetingDao;
import com.changhong.data.meeting.entity.QRMeeting;
import com.changhong.data.meeting.service.wapi.WXTokenService;
import com.changhong.data.meeting.utils.HttpQueryUtil;

@Service
public class QRMeetingService {

	@Value("${wapi.account_code}")
	private String qrurl;

	@Autowired
	private QRMeetingDao qrmeetingDao;

	@Autowired
	private WXTokenService tkService;

	public String save(QRMeeting entity) {
		QRMeeting q = qrmeetingDao.save(entity);
		return q.getId();
	}

	public QRMeeting findByQrId(String qrId) {
		QRMeeting q = qrmeetingDao.findByQrId(qrId);
		return q;
	}

	public QRMeeting findByMeetingId(String meetingId) {
		QRMeeting q = qrmeetingDao.findByMeetingId(meetingId);
		return q;
	}

	public void deleteByMeetingId(String meetingId) {
		qrmeetingDao.deleteByMeetingId(meetingId);
	}

	public JSONObject createQR(String meetingId) {
		JSONObject result = new JSONObject();
		try {
			String token = tkService.getAccessToken();
			String url = MessageFormat.format(qrurl, token);
			int scene_id = Math.abs(meetingId.hashCode());
			// String str = "{\"expire_seconds\": 604800, \"action_name\":
			// \"QR_SCENE\", "
			// + "\"action_info\": {\"scene\": {\"scene_id\": " + scene_id +
			// "}}}";
			JSONObject params = new JSONObject();
			// 设置二维码过期时间最长30天
			params.put("expire_seconds", 2592000);
			params.put("action_name", "QR_SCENE");
			JSONObject action_info = new JSONObject();
			JSONObject scene = new JSONObject();
			scene.put("scene_id", scene_id);
			action_info.put("scene", scene);
			params.put("action_info", action_info);

			String response = HttpQueryUtil.postWithJSON(url, params.toJSONString());
			result = JSONObject.parseObject(response);
			result.put("scene_id", scene_id);
			result.put("error", 0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("msg", "内部错误");
			return result;
		}
	}

}
