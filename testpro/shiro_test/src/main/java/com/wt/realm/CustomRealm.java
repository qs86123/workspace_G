package com.wt.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {

	@Override
	public void setName(String name) {
		super.setName("CustomRealm");
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token是用户输入的
		// token中取出身份信息
		String userCode = (String) token.getPrincipal();
		// 根据用户输入的用户名(userCode)从数据库中获取用户密码
		// ......
		// 模拟查询到的密码(加密过后的密码)
		String password = "111111";

		// 如果查询不到返回null
		// 查询到则返回SimpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password,
				this.getName());
		return simpleAuthenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// 从principal获取主身份信息
		// 将getPrimaryPrincipal方法返回值转成真实身份类型，
		// 也就是上面doGetAuthenticationInfo方法认证通过后填充到SimpleAuthenticationInfo中的身份信息
		String userCode = (String) principal.getPrimaryPrincipal();
		// 根据身份信息获取权限信息
		// ......
		// 模拟从数据库获取到权限信息
		List<String> permissoins = new ArrayList<String>();
		permissoins.add("user:create");// 用户创建权限
		permissoins.add("items:add");// 商品添加权限

		// 查询到权限数据，返回授权信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 将上面查询到的权限信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissoins);

		return simpleAuthorizationInfo;
	}

}
