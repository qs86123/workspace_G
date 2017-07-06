package com.changhong.data.meeting.domain.repsponse;

/**
 * @author iplus
 * @date 16/10/27
 * @e-mail iplus.wjy@gmail.com
 * @description 响应消息基类
 */
public class BaseMessage {
	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 消息创建时间（整型）
	private long CreateTime;
	// 消息类型（text/image/voice/video/music/news）
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

}
