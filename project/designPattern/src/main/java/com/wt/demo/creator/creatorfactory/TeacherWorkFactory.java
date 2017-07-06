package com.wt.demo.creator.creatorfactory;

import com.wt.demo.creator.product.TeacherWork;
import com.wt.demo.creator.product.Work;

public class TeacherWorkFactory implements WorkFactory {

	public Work getWork() {
		return new TeacherWork();
	}

}
