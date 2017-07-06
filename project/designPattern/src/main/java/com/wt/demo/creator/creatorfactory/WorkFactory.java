package com.wt.demo.creator.creatorfactory;

import com.wt.demo.creator.product.Work;

/**
 * 定义工厂返回一个Work实例
 * 
 * @author wangtao
 * @date 2017年4月12日上午9:57:46
 */
public interface WorkFactory {
	public Work getWork();
}
