package com.wt.shirotest.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wt.shirotest.extendsentity.Account;
import com.wt.shirotest.extendsentity.AccountAuthToken;
import com.wt.shirotest.extendsentity.Principal;

/**
 * Shiro授权
 * 
 * @author li
 *
 */
public class AccountShiroRealmTest extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 获取认证信息 相当于验证
	 * 
	 * @param token
	 *            令牌，需要转化成AccountType令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AccountAuthToken atoken = (AccountAuthToken) token;

		String login_name = atoken.getUsername();
		String login_pass = new String(atoken.getPassword());
		Object type = atoken.getType();
		//添加一个测试Acount，绑定到用户认证信息上面，为了避免测试的时候通过用户身份获取Acount为空
		Account account = new Account();
		//.....acount的各种属性赋值
		Principal principal = new Principal();
		principal.setLogin_name(login_name);
		principal.setId("id");
		principal.setAccount(account);
		
		//认证成功，将用户身份信息principle放到SimpleAuthenticationInfo中
		return new SimpleAuthenticationInfo(principal, login_pass, getName());
	}
}
