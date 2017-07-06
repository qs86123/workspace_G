/**
 * 
 */
package com.changhong.data.common.xlsx.adapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import com.changhong.data.common.exception.ReflectException;

/**
 * 数字类型适配器
 * 
 * @author QiYao yao.qi@changhong.com

 * @version 1.0.0 2014年10月11日
 */
public class NumberCellAdapter extends BaseInvokeOutputCellAdapter {

	/**
	 * @param method
	 */
	public NumberCellAdapter(Method method) {
		super(method);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.changhong.data.common.xlsx.adapter.OutputCellAdapter#getCellType()
	 */
	@Override
	public CellType getCellType() {
		return CellType.NUMERIC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.changhong.data.common.xlsx.adapter.OutputCellAdapter#fillRow(org.
	 * apache.poi.ss.usermodel.Cell, java.lang.Object)
	 */
	@Override
	public void fillRow(Cell cell, Object obj) {
		try {
			double number = new Double(this.method.invoke(obj, new Object[] {}) + "");
			cell.setCellValue(number);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			ReflectException re = new ReflectException(e);
			throw re;
		}
	}

}
