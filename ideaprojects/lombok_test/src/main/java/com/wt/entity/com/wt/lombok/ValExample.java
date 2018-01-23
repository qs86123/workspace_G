package com.wt.entity.com.wt.lombok;

import lombok.experimental.var;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @description
 * @author: wangtao
 * @date:11:18 2018/1/20
 * @email:tao8.wang@changhong.com
 */
public class ValExample {
    public static void main(String[] args) {
        ValExample e = new ValExample();
        e.example();
        e.example2();
    }

    public void example() {
        val example = new ArrayList<String>();
        example.add("Hello, Lombok!");
        val foo = example.get(0);
        System.out.println(foo.toLowerCase());
    }

    public void example2() {
        val map = new HashMap<Integer, String>(2, 1);
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public void example3() {
        var str="";
        str = "ppp";
        System.out.println(str);
    }

    public void example4() {
        //val 定义的变量是final的
        val str = "aaa";
//        str="lll";
        System.out.println(str);
    }
}
