package com.wt.aotuorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthorizationTest {
	@Test
	public void testAuthorization() {
		Subject subject = getSubject("classpath:shiro-permission.ini", "zhang", "123");
		// 认证通过后执行授权
		// 对角色进行授权
		boolean isHasRole = subject.hasRole("role1");
		System.out.println("isHasRole:" + isHasRole);
		// 对资源进行授权
		boolean isPermitted = subject.isPermitted("user:create");
		System.out.println("isPermitted:" + isPermitted);
	}
	
	@Test
	public void testAuthorizationCustomRealm() {
		Subject subject = getSubject("classpath:shiro-realm.ini", "zhangsan", "111111");
		// 认证通过后执行授权
		// 对角色进行授权
		boolean isHasRole = subject.hasRole("role1");
		System.out.println("isHasRole:" + isHasRole);
		// 对资源进行授权
		boolean isPermitted = subject.isPermitted("user:create");
		System.out.println("isPermitted:" + isPermitted);
	}
	
	

	private Subject getSubject(String cfg, String username, String password) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(cfg);
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		System.out.println("认证状态：" + subject.isAuthenticated());

		return subject;
	}
}
