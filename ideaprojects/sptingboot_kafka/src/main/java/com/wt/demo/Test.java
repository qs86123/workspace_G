package com.wt.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:12 2017/7/25
 * @Email:tao8.wang@changhong.com
 */
public class Test {
    public static void main(String[] args) {
        List<String> strs = Arrays.asList("a", "b", "c");
        strs.forEach((String e) -> {
            System.out.println(e);
            System.out.println("eee");
        });
    }
}
