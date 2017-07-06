package com.changhong.data.common.xlsx.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 单元格适配器组
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月9日
 */
public class OutputCellAdapterSet {

	/**
	 * sheet名称
	 */
	private String name = null;
	/**
	 * 表头
	 */
	private List<OutputCellAdapter> headers = null;
	/**
	 * 单页数据最大行数
	 */
	private int size = 0;
	/**
	 * 单元格
	 */
	private List<OutputCellAdapter> adapters = new ArrayList<OutputCellAdapter>();

	public OutputCellAdapterSet(){};
	
	public OutputCellAdapterSet(String name, int size, List<OutputCellAdapter> headers, List<OutputCellAdapter> adapters){
		this.headers = headers;
		this.name = name;
		this.size = size;
		this.setAdapters(adapters);
	}
	public OutputCellAdapterSet(String name, List<OutputCellAdapter> headers, List<OutputCellAdapter> adapters){
		this.headers = headers;
		this.name = name;
		this.setAdapters(adapters);
	}
	public OutputCellAdapterSet(List<OutputCellAdapter> adapters){
		this.setAdapters(adapters);
	}

	

	/**
	 * @return the headers
	 */
	public List<OutputCellAdapter> getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(List<OutputCellAdapter> headers) {
		this.headers = headers;
	}

	/**
	 * @return the adapters
	 */
	public List<OutputCellAdapter> getAdapters() {
		return adapters;
	}

	/**
	 * @param adapters the adapters to set
	 */
	public void setAdapters(List<OutputCellAdapter> adapters) {
		this.adapters = adapters;
		Collections.sort(adapters);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
