package com.changhong.data.common.xlsx.adapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import com.changhong.data.common.exception.ReflectException;

/**
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月11日
 */
public class BooleanToStringCellAdapter extends BaseOutputCellAdapter {

	private Method method;
	
	public BooleanToStringCellAdapter(Method method){
		this.method = method;
	}
	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.adapter.OutputCellAdapter#getCellType()
	 */
	@Override
	public CellType getCellType() {
		return CellType.STRING;
	}

	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.adapter.OutputCellAdapter#fillRow(org.apache.poi.ss.usermodel.Cell, java.lang.Object)
	 */
	@Override
	public void fillRow(Cell cell, Object obj) {
		try {
			boolean b = (boolean) this.method.invoke(obj, new Object[]{});
			if(b){
				cell.setCellValue("是");
			}else{
				cell.setCellValue("否");
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			ReflectException re = new ReflectException(e);
			throw re;
		}
	}

}
