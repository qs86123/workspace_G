package com.wt.mybatis.interceptor.demo;

import com.wt.mybatis.interceptor.demo.interfaces.MyPrintInterface;
import com.wt.mybatis.interceptor.demo.interfaces.MyPrintInterface2;

/**
 * @Description
 * @Author: wangtao
 * @Date:8:45 2017/11/27
 * @Email:tao8.wang@changhong.com
 */
public class MyPrint implements MyPrintInterface, MyPrintInterface2 {

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public void abc(String abc) {
        System.out.println(abc);
    }

    @Override
    public void print2(String msg) {
        System.out.println(msg);
    }

    @Override
    public void abc2(String abc) {
        System.out.println(abc);
    }
}
