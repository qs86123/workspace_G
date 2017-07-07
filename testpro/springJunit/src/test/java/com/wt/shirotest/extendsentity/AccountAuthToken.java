package com.wt.shirotest.extendsentity;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登陆令牌，也可以直接使用UsernamePasswordToken
 * 
 * @author li
 *
 */
public class AccountAuthToken extends UsernamePasswordToken {
	private static final long serialVersionUID = -2940013965128967419L;

	public Object type;

	private String captcha;

	public AccountAuthToken(String username, String password, boolean rememberMe, Object type, String captcha) {
		super(username, password, rememberMe);
		this.type = type;
		this.captcha = captcha;
	}

	public AccountAuthToken(String username, String password, boolean rememberMe, Object type) {
		super(username, password, rememberMe);
		this.type = type;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public Object getType() {
		return type;
	}

	public void setType(Object type) {
		this.type = type;
	}

}
