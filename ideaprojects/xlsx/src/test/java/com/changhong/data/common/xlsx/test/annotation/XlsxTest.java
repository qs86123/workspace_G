/**
 * 
 */
package com.changhong.data.common.xlsx.test.annotation;

import java.lang.annotation.*;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * 工程测试支持注解
 * 
 * @author QiYao yao.qi@changhong.com

 * @version 1.0.0 2014年10月10日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration("classpath:test.applicationContext.xml")
@ActiveProfiles("dev")
public @interface XlsxTest {

}
