package com.changhong.data.meeting.apipush.account;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.apipush.Push;
import com.changhong.data.meeting.domain.repsponse.TextMessage;
import com.changhong.data.meeting.entity.MeetingPlanInfo;
import com.changhong.data.meeting.entity.QRMeeting;
import com.changhong.data.meeting.entity.UserQRSign;
import com.changhong.data.meeting.service.MeetingPlanInfoService;
import com.changhong.data.meeting.service.QRMeetingService;
import com.changhong.data.meeting.service.UserQRSignService;
import com.changhong.data.meeting.utils.MessageUtil;

/**
 * 带参数的二维码事件
 * 
 * @author wangtao
 */
@Component
public class QrCodePush extends Push {

	// @Autowired
	// private PushService pushService;

	@Autowired
	private UserQRSignService uqSignService;

	@Autowired
	private QRMeetingService qmService;

	@Autowired
	private MeetingPlanInfoService mpiService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public QrCodePush() {
		super("event", "SCAN");
	}

	@Override
	public Object dopush(JSONObject pushData) {
		String result = "";
		// PushEntity pushentity = new PushEntity();
		// pushentity.setPushData(pushData.toJSONString());
		// pushentity.setPushId(getPushId());
		TextMessage tm = new TextMessage();
		String eventKey = pushData.getString("EventKey");
		QRMeeting q = qmService.findByQrId(eventKey);
		if (q != null) {
			MeetingPlanInfo mpi = mpiService.findById(q.getMeetingId());
			tm.setContent("会议名称：" + mpi.getName() + "\n签到成功");
			tm.setCreateTime(new Date().getTime());
			tm.setFromUserName(pushData.getString("ToUserName"));
			tm.setToUserName(pushData.getString("FromUserName"));
			tm.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			UserQRSign entity = uqSignService.findByMeetingIdAndOpenid(q.getMeetingId(),
					pushData.getString("FromUserName"));
			if (entity != null) {
				tm.setContent("会议名称：" + mpi.getName() + "\n你已经签过到了");
			} else {
				entity = new UserQRSign();
				entity.setOpenid(pushData.getString("FromUserName"));
				entity.setMeetingId(q.getMeetingId());
				uqSignService.save(entity);
			}
			result = MessageUtil.messageToXml(tm);
			System.out.println(result);
		}
		// pushService.save(pushentity);
		logger.info("扫描二维码事件数据:{}", pushData.toJSONString());
		return result;
	}

}
