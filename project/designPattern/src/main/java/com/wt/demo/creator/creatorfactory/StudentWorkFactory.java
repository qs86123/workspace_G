package com.wt.demo.creator.creatorfactory;

import com.wt.demo.creator.product.StudentWork;
import com.wt.demo.creator.product.Work;

public class StudentWorkFactory implements WorkFactory {

	public Work getWork() {
		return new StudentWork();
	}

}
