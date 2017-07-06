package com.changhong.data.common.xlsx.adapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import com.changhong.data.common.exception.ReflectException;

/**
 * 文本到文本Cell的转换器
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月9日
 */
public class StringToStringCellAdapter extends BaseOutputCellAdapter{

	//获取数据的方法
	private Method method;
	
	public StringToStringCellAdapter(Method method){
		this.method = method;
	}
	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.domain.adapter.OutputCellAdapter#getCellType()
	 */
	@Override
	public CellType getCellType() {
		return CellType.STRING;
	}

	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.domain.adapter.OutputCellAdapter#fillRow(org.apache.poi.ss.usermodel.Cell, java.lang.Object)
	 */
	@Override
	public void fillRow(Cell cell, Object obj) {
		try {
			String value = method.invoke(obj, new Object[]{}).toString();
			cell.setCellValue(value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			ReflectException re = new ReflectException(e);
			throw re;
		}
	}


}
