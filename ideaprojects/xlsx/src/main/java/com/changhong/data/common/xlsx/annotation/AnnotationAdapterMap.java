package com.changhong.data.common.xlsx.annotation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.changhong.data.common.xlsx.adapter.OutputCellAdapterSet;

/**
 * 对象适配器存储MAP
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月10日
 */
@Component
public class AnnotationAdapterMap {

	@Autowired
	private AnnotationScanner xlsxAnnotationScanner;
	
	public static Map<String, OutputCellAdapterSet> map = new HashMap<>();
	
	public OutputCellAdapterSet getAdapterSet(String name){
		OutputCellAdapterSet set = map.get(name);
		if(set == null){
			set = xlsxAnnotationScanner.scanClass(name);
			map.put(name, set);
			return set;
		}else{
			return set;
		}
	}
	
	public OutputCellAdapterSet getAdapterSet(Object t){
		String name = t.getClass().getName();
		return getAdapterSet(name);
	}
	
    public OutputCellAdapterSet getAdapterSet(Class<?> c){
        String name = c.getName();
        return getAdapterSet(name);
    }
	
}
