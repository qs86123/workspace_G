package com.wt.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthenticationTest {

	// 用户的登录和退出
	@Test
	public void testLoginAndLogout() {
		doAhthorication("classpath:shiro-first.ini", "zhangsan", "111111");
	}

	// 测试自定义Realm
	@Test
	public void testCustomRealm() {
		doAhthorication("classpath:shiro-realm.ini", "zhangsan", "111111");
	}

	// 测试自定义Realm，验证时使用md5加密
	@Test
	public void testCustomRealmMD5() {
		doAhthorication("classpath:shiro-realm-md5.ini", "zhangsan", "111111");
	}

	private void doAhthorication(String cfg, String username, String password) {
		// 通过ini配置文件创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(cfg);
		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		// 将SecurityManager设置到当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		// 从SecurityUtils中获得Subject
		Subject subject = SecurityUtils.getSubject();
		// 在认证提交前准备Token（令牌）
		UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);

		try {
			// 执行认证提交
			subject.login(upToken);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		boolean isAuthenticated = subject.isAuthenticated();
		System.out.println("是否认证通过:" + isAuthenticated);

		subject.logout();

		isAuthenticated = subject.isAuthenticated();
		System.out.println("退出后是否认证通过:" + isAuthenticated);
	}

}
