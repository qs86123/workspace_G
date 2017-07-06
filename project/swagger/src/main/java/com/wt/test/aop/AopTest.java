package com.wt.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopTest {

	@Around(value="execution(* com.wt.test.controller.TestController.getGet(java.lang.String)) && args(rid)")
	public Object around(ProceedingJoinPoint point,String rid) throws Throwable{
		System.out.println("before"+rid);
		Object o=point.proceed();
		System.out.println("around"+rid);
		return o;
	}
	
	@AfterReturning(value="execution(* com.wt.test.controller.TestController.getGet())")
	public void around(JoinPoint point) throws Throwable{
		System.out.println("@AfterReturning");
		System.out.println(point.getKind()+point.getArgs());
		System.out.println("@AfterReturning2");
	}
	
}
