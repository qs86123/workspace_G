package com.changhong.data.meeting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "meeting_seat_info")
public class MeetingSeatInfo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	private double x;
	private double y;
	private double degree;

	@Column(name = "meeting_id")
	private String meetingId;

	@Column(name = "room_id")
	private String roomId;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "seat_flag")
	private String seatFlag;

	// 代签标记，如果不为空表示有代签
	@Column(name = "replace_id")
	private String replaceId;

	public String getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDegree() {
		return degree;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getReplaceId() {
		return replaceId;
	}

	public void setReplaceId(String replaceId) {
		this.replaceId = replaceId;
	}

	public String getSeatFlag() {
		return seatFlag;
	}

	public void setSeatFlag(String seatFlag) {
		this.seatFlag = seatFlag;
	}

}
