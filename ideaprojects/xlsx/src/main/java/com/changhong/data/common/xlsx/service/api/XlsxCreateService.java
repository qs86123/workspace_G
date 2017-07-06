package com.changhong.data.common.xlsx.service.api;

import java.io.OutputStream;
import java.util.List;

import com.changhong.data.common.xlsx.adapter.OutputCellAdapterSet;
import com.changhong.data.common.xlsx.datasource.OutputDataSource;

/**
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年4月3日
 * 创建xls服务
 */
public interface XlsxCreateService {

	public void create(OutputStream os, List<Object> data, OutputCellAdapterSet ocas);
	public void create(OutputStream os, List<Object> data);

    public void create(OutputStream os,  OutputDataSource outputDataSource,OutputCellAdapterSet ocas);
    public void create(OutputStream os, OutputDataSource outputDataSource,Class<?> c);
	
}
