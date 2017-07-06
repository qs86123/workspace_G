package com.changhong.data.meeting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "meeting_qrsign")
public class UserQRSign {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column(name = "meeting_id")
	private String meetingId;

	private String openid;

	public String getId() {
		return id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

}
