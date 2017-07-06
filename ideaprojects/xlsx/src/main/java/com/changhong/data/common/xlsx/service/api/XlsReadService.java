package com.changhong.data.common.xlsx.service.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.changhong.data.common.xlsx.domain.XlsReadColumn;

/**
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 Apr 11, 2014
 * 读取xls文件接口
 * @param <T>
 */
public interface XlsReadService {


	/**
	 * 读取文件数据到对象
	 * @param file
	 * @param mapper
	 * @param clazz
	 * @return
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> List<T> readToObjects(
			int offsetColumns, int offsetRows,
			File file,List<XlsReadColumn> mapper, Class<T> clazz) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException;
	
	/**
	 * 读取文件到对象
	 * @param offsetColumns
	 * @param offsetRows
	 * @param is
	 * @param mapper
	 * @param clazz
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public <T> List<T> readToObjects(
			int offsetColumns, int offsetRows,
			InputStream is, List<XlsReadColumn> mapper, Class<T> clazz) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException;
	
	
}
