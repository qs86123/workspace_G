package com.myself.edu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:07 2017/6/28
 * @Email:tao8.wang@changhong.com
 */
public class Test {
    public static void main(String[] args) {
        String date = "2017-03-01";
        System.out.println(date.substring(0, 7));
        JSONObject j= JSON.parseObject("");
        if (j == null) {
            System.out.println("null");
        }else
            System.out.println("not null");
        A a=new B();
        B b= (B) a;
    }
}
