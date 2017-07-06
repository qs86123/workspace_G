package com.changhong.data.meeting.apipush;

import com.alibaba.fastjson.JSONObject;

/**
 * 推送基类
 * @author wangyang@broadengate.com
 * 2016年10月19日
 */
public abstract class Push {
	

	private String msgType;
	
	private String event;
	
	private String pushId;
	
//	public Push(){
//		pushId=this.msgType+this.event+this.eventKey;
//		register();
//	}
	
	public Push(String msgType,String event){
		if(event==null){
			event="";
		}
		this.msgType=msgType;
		this.event=event;
		pushId=this.msgType+this.event;
		register();
	}
	
	public abstract Object dopush(JSONObject pushData) throws Exception;
	
	public void register(){
		PushManager.register(pushId, this);
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	
	
	
}
