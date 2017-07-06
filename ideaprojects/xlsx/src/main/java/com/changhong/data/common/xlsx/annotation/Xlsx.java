/**
 * 
 */
package com.changhong.data.common.xlsx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * xlsx注解
 * @author QiYao yao.qi@changhong.com

 * @version 1.0.0 2014年10月10日
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Xlsx {

	/**
	 * 默认的sheet页的名称
	 * @return 生成的sheet页名称
	 */
	String value() default "";
	
	/**
	 * 每页最大的数量
	 * @return 每个sheet最大的数量
	 */
	int size() default 500000;
}
