package com.wt.entity.com.wt.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @description
 * @author: wangtao
 * @date:9:29 2018/1/22
 * @email:tao8.wang@changhong.com
 */
@EqualsAndHashCode(onParam = @__({@NonNull}), doNotUseGetters = true)
public class EqualsAndHashCodeExample {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        new EqualsAndHashCodeExample();
    }
}
