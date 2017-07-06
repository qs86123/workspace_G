package com.changhong.data.meeting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name = "meeting_qr")
@Entity
public class QRMeeting {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column(name = "qr_id")
	private String qrId;
	@Column(name = "meeting_id")
	private String meetingId;

	@Column(name = "qr_ticket")
	private String qrTicket;

	public String getId() {
		return id;
	}

	public String getQrId() {
		return qrId;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQrId(String qrId) {
		this.qrId = qrId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getQrTicket() {
		return qrTicket;
	}

	public void setQrTicket(String qrTicket) {
		this.qrTicket = qrTicket;
	}

}
