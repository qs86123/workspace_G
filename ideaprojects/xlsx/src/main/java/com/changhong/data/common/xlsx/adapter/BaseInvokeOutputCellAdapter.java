package com.changhong.data.common.xlsx.adapter;

import java.lang.reflect.Method;

/**
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月11日
 */
public abstract class BaseInvokeOutputCellAdapter extends BaseOutputCellAdapter {

	protected Method method;
	
	public BaseInvokeOutputCellAdapter(Method method){
		this.method = method;
	}
}
