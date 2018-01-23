package com.wt.entity.com.wt.lombok;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.CONSTRUCTOR;

/**
 * @description
 * @author: wangtao
 * @date:10:21 2018/1/22
 * @email:tao8.wang@changhong.com
 */
@Target({ElementType.METHOD, ElementType.TYPE,CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnon {
}
