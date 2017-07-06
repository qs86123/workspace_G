package com.changhong.data.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_openid")
public class UserOpenid {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	@Column(name = "card_num")
	private String cardNum;
	
	@Column(unique = true)
	private String openid;

	public String getId() {
		return id;
	}

	public String getCardNum() {
		return cardNum;
	}

	public String getOpenid() {
		return openid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
