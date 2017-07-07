package com.wt.shirotest.extendsentity;

import java.io.Serializable;

/**
 * 用户认证后的身份信息，shiro认证后的身份信息为Object，所有我们可以使用自定义实体类来保存，以便于后边好用
 * 
 * @author The_kid
 *
 */
public class Principal implements Serializable {

	private static final long serialVersionUID = 870339310434972408L;
	public String login_name;
	public String id;
	private Account account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
