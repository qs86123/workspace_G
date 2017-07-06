package com.changhong.data.common.xlsx.datasource;

import java.util.List;

/**
 * 输出数据接口
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月9日
 */
public interface OutputDataSource {
	
	/**
	 * 获取数据
	 * @return
	 */
	public List<Object> getData();

	/**
	 * 是否还有数据
	 * @return
	 */
	public boolean hasNext();
}
