package com.changhong.data.common.xlsx.datasource;

import java.util.List;

/**
 * 单次数据源
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月9日
 */
public class OnceDataSource implements OutputDataSource {

	private boolean hasNext = true;
	
	private List<Object> data;
	
	public OnceDataSource(List<Object> data){
		this.data = data;
	}
	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.datasource.OutputDataSource#getData()
	 */
	@Override
	public List<Object> getData() {
		this.hasNext = false;
		return data;
	}
	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.datasource.OutputDataSource#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return hasNext;
	}

}
