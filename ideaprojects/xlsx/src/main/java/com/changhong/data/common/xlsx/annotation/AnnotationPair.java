package com.changhong.data.common.xlsx.annotation;

import java.lang.reflect.Method;

import com.changhong.data.common.xlsx.adapter.BooleanToStringCellAdapter;
import com.changhong.data.common.xlsx.adapter.DateCellAdapter;
import com.changhong.data.common.xlsx.adapter.NumberCellAdapter;
import com.changhong.data.common.xlsx.adapter.OutputCellAdapter;
import com.changhong.data.common.xlsx.adapter.StaticStringCellAdapter;
import com.changhong.data.common.xlsx.adapter.StringToStringCellAdapter;

/**
 * 注解与返回值的配对
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月11日
 */
public class AnnotationPair implements Comparable<AnnotationPair> {
	private XlsxColumn xc;

	private Method method;

	public AnnotationPair(Method method, XlsxColumn xc) {
		this.method = method;
		this.xc = xc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AnnotationPair o) {
		return Integer.compare(xc.order(), o.order());
	}

	public int order() {
		return xc.order();
	}

	/**
	 * 获取表头适配器
	 * @return 返回静态String类型的适配器
	 */
	public OutputCellAdapter getHeadAdapter() {
		return new StaticStringCellAdapter(xc.value());
	}

	/**
	 * 获取数据适配器
	 * @return 根据具体的Method返回类型进行类型器适配
	 */
	public OutputCellAdapter getDataAdapter() {
		Class<?> returnType = method.getReturnType();
		String name = returnType.getName();
		switch (name) {
		case "java.lang.String":
			return doString();
		case "int":
			return doNumber();
		case "double":
			return doNumber();
		case "long":
			return doNumber();
		case "boolean":
			return doBoolean();
		case "java.util.Date":
			return doDate();
		case "java.sql.Date":
			return doDate();
		
		default:
			return doString();
		}
	}

	/**
	 * 日期类型适配器
	 * @return 日期类型适配器
	 */
	private OutputCellAdapter doDate() {
		return new DateCellAdapter(method);
	}

	/**
	 * 获取适配器
	 * @return 布尔类型适配器
	 */
	private OutputCellAdapter doBoolean() {
		return new BooleanToStringCellAdapter(method);
	}

	
	/**
	 * 获取适配器
	 * @return 数字类型适配器
	 */
	private OutputCellAdapter doNumber() {
		return new NumberCellAdapter(method);
	}

	/**
	 * 获取适配器
	 * @return 字符串适配器
	 */
	private OutputCellAdapter doString() {
		return new StringToStringCellAdapter(method);
	}
}
