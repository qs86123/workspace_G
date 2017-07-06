package com.changhong.data.common.xlsx.annotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.changhong.data.common.xlsx.adapter.OutputCellAdapter;

/**
 * 默认类型转换器
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月11日
 */
public class AnnotationPairTrasfer {

	private List<AnnotationPair> lap = new ArrayList<AnnotationPair>();
	
	public void add(AnnotationPair ap){
		this.lap.add(ap);
		Collections.sort(lap);
	}

	/**
	 * @return 表头适配器组
	 */
	public List<OutputCellAdapter> getHeaders() {
		List<OutputCellAdapter> headers = new ArrayList<OutputCellAdapter>();
		
		for(int i=0; i<lap.size(); i++){
			AnnotationPair ap = lap.get(i);
			OutputCellAdapter oca = ap.getHeadAdapter();
			headers.add(oca);
		}
		
		return headers;
	}


	/**
	 * @return column列适配器组
	 */
	public List<OutputCellAdapter> getAdapters() {
		List<OutputCellAdapter> adapters = new ArrayList<OutputCellAdapter>();
		
		for(int i=0; i<lap.size(); i++){
			AnnotationPair ap = lap.get(i);
			OutputCellAdapter oca = ap.getDataAdapter();
			adapters.add(oca);
		}
		
		return adapters;
	}
}
