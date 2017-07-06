package com.changhong.data.common.xlsx.domain;

import java.lang.reflect.Method;

/**
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 Apr 11, 2014
 * 读取 XLS 列描述
 */
@SuppressWarnings("rawtypes")
public class XlsReadColumn {
	/**
	 * 列号，除开offset外的列号, 从0开始
	 */
	private int columnIndex;
	/**
	 * 是否必须
	 */
	private boolean required = true;
	/**
	 * 属性名称
	 */
	private String propertyName;
	/**
	 * 反射字段
	 */
	private Method method = null;
	/**
	 * 字段类型
	 */
	private Class filedType = null;
	
	public XlsReadColumn(){
		
	}
	public XlsReadColumn(int columnIndex, boolean required, String propertyName, Class fileType) {
		super();
		this.columnIndex = columnIndex;
		this.required = required;
		this.propertyName = propertyName;
		this.filedType = fileType;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Class getFiledType() {
		return filedType;
	}
	public void setFiledType(Class filedType) {
		this.filedType = filedType;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
}
