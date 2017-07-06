package com.wt.demo.creator;

import com.wt.demo.creator.creatorfactory.StudentWorkFactory;
import com.wt.demo.creator.creatorfactory.TeacherWorkFactory;
import com.wt.demo.creator.creatorfactory.WorkFactory;

/**
 * 
 * 创建型模式 测试类
 * 
 * @author wangtao
 * @date 2017年4月12日上午9:54:39
 */
public class Test {
	public static void main(String[] args) {
		WorkFactory studentWorkFactory = new StudentWorkFactory();
		studentWorkFactory.getWork().doWork();
		WorkFactory teacherWorkFactory = new TeacherWorkFactory();
		teacherWorkFactory.getWork().doWork();
	}
}
