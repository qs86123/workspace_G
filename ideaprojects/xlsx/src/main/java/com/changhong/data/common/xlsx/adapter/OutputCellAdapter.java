package com.changhong.data.common.xlsx.adapter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 * 单元格转换器，需要排序
 * @author QiYao yao.qi@changhong.com yao.qi@changhong.com
 * @version 2014年10月9日
 */
public interface OutputCellAdapter extends Comparable<OutputCellAdapter>{
	/**
	 * @return the order
	 */
	public int getOrder();
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order);
	/**
	 * 返回Cell类型
	 * @return
	 */
	public CellType getCellType();

	/**
	 * 给Row填上值
	 */
	public void fillRow(Cell cell, Object obj);
}
