package com.wt.shirotest.commom;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.LifecycleUtils;
import org.apache.shiro.util.ThreadState;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.AfterClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletResponse;

import com.wt.shirotest.extendsentity.AccountAuthToken;

public abstract class AbstractShiroTest extends WebRunner {
	private static ThreadState subjectThreadState;

	public AbstractShiroTest() {
	}

	public void securityManager() {
		String resource = "classpath*:/shiroConfigTest.xml";
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(resource);
		org.apache.shiro.mgt.SecurityManager securityManager = (org.apache.shiro.mgt.SecurityManager) appCtx
				.getBean("securityManager");
		SecurityUtils.setSecurityManager(securityManager);
	}

	protected void setSubject(Subject subject) {
		clearSubject();
		subjectThreadState = createThreadState(subject);
		subjectThreadState.bind();
	}

	protected Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	protected ThreadState createThreadState(Subject subject) {
		return new SubjectThreadState(subject);
	}

	/**
	 * Clears Shiro's thread state, ensuring the thread remains clean for future
	 * test execution.
	 */
	protected void clearSubject() {
		doClearSubject();
	}

	private static void doClearSubject() {
		if (subjectThreadState != null) {
			subjectThreadState.clear();
			subjectThreadState = null;
		}
	}

	protected static void setSecurityManager(SecurityManager securityManager) {
		SecurityUtils.setSecurityManager(securityManager);
	}

	protected static SecurityManager getSecurityManager() {
		return SecurityUtils.getSecurityManager();
	}

//	protected void createSubject22() {
//		// 1. Create a mock authenticated Subject instance for the test to run:
//		Subject subjectUnderTest = org.easymock.EasyMock.createNiceMock(Subject.class);
//		org.easymock.EasyMock.expect(subjectUnderTest.isAuthenticated()).andReturn(true);
//
//		// 2. Bind the subject to the current thread:
//		setSubject(subjectUnderTest);
//	}

	protected void createSubject() {
		// setSecurityManager(securityManager);
		securityManager();// 该类中的一个方法，作用是重新读取配置文件shiroConfigTest.xml
		// 如果不执行上面的方法，直接使用setSecurityManager(securityManager)，单个类测试不会有问题。
		// 但是mvn跑测试覆盖率，一次性跑完所有class的时候就会报sessionManager空指针,即使配置上也会报其他错误(认证时候空指针)。
		// shiroConfigTest.xml中也没有配置sessionManager，但是每次重新读取配置文件那就不会报错。
		MockHttpServletResponse res = new MockHttpServletResponse();
		request.setSession(session);
		Subject s = new WebSubject.Builder(request, res).buildSubject();
		s.login(new AccountAuthToken("admin", "admin", false, new Object()));
		setSubject(s);
	}

	@AfterClass
	public static void tearDownShiro() {
		doClearSubject();
		try {
			SecurityManager securityManager = getSecurityManager();
			LifecycleUtils.destroy(securityManager);
		} catch (UnavailableSecurityManagerException e) {
		}
		setSecurityManager(null);
	}
}
