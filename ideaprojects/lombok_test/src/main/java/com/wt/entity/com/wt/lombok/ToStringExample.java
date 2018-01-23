package com.wt.entity.com.wt.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * @description
 * @author: wangtao
 * @date:14:43 2018/1/20
 * @email:tao8.wang@changhong.com
 */
@ToString(includeFieldNames = true, exclude = {"name"}, callSuper = true, doNotUseGetters = false)
public class ToStringExample {
    private final String finalStr="finalStr";
    private static String staticStr="staticStr";
    @Getter
    @Setter
    private String $key;

    private String name;
    @Getter
    private int age;

    @Tolerate
    public static void main(String[] args) {
        ToStringExample example = new ToStringExample();
        example.age = 1;
        example.name = "hhh";
        example.$key="key";
        System.out.println(example);
    }

//    @Override
//    public String toString() {
//        return "myToString()";
//    }
}
