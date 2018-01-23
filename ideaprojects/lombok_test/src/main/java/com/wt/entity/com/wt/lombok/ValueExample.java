package com.wt.entity.com.wt.lombok;

import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.PackagePrivate;

/**
 * @description
 * @author: wangtao
 * @date:11:29 2018/1/22
 * @email:tao8.wang@changhong.com
 */
@Value
public class ValueExample {
    String name;
    @NonFinal
    int age;
    @PackagePrivate
    double score;
    protected String[] tags;
}
