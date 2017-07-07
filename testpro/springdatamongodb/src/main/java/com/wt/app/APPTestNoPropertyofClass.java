package com.wt.app;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wt.entity.Person;
import com.wt.entity.Personnal;
import com.wt.repro.Repository;

public class APPTestNoPropertyofClass {
	public <T> T getBeanforCustom(Class<T> t) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-noclass.xml");
		T tt = (T) context.getBean(t);
		return tt;
	}

	// 保存的时候如果id相同直接覆盖
	@Test
	public void test() {
		
		Repository repository = getBeanforCustom(Repository.class);
		Person p=new Person("10101", "noclassagain", "100");
		repository.saveObject(p);
	}

}
