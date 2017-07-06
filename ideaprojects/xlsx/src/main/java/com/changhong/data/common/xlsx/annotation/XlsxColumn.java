package com.changhong.data.common.xlsx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 列注解
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月10日
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XlsxColumn {
	
	/**
	 * 列名
	 * @return
	 */
	String value() default "列";
	
	/**
	 * 列优先级
	 * @return
	 */
	int order() default 0;

	/**
	 * 各自的配置使用
	 * @return
	 */
	String[] config() default {};
}
