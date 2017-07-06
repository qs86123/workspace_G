/**
 * 
 */
package com.changhong.data.common.xlsx.adapter;

/**
 * 单元格输出基础适配器
 * 用于完善适配器排序问题
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月9日
 */
public abstract class BaseOutputCellAdapter implements OutputCellAdapter {

	private int order = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(OutputCellAdapter o) {
		int co = o.getOrder();
		return Integer.compare(order, co);
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
