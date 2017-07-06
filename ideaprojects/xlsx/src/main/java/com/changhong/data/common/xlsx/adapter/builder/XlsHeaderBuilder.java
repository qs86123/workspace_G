package com.changhong.data.common.xlsx.adapter.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.changhong.data.common.xlsx.adapter.OutputCellAdapter;
import com.changhong.data.common.xlsx.adapter.StaticStringCellAdapter;

/**
 * 表头构造器
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月10日
 */
public class XlsHeaderBuilder {


	/**
	 * 构建表头
	 * @param strings 表头名称
	 * @return 默认返回静态表头适配器
	 */
	public static List<OutputCellAdapter> build(String... strings) {
		List<OutputCellAdapter> headers = new ArrayList<>();
		for(int i=strings.length; i>0; i--){
			StaticStringCellAdapter adapter = new StaticStringCellAdapter(strings[i-1]);
			adapter.setOrder(i);
			headers.add(adapter);
		}
        Collections.sort(headers);
		return headers;
	}

}
