package com.wt.mybatis.interceptor.demo;

import com.wt.mybatis.interceptor.demo.interfaces.MyPrintInterface;
import com.wt.mybatis.interceptor.demo.interfaces.MyPrintInterface2;


/**
 * @Description
 * @Author: wangtao
 * @Date:8:44 2017/11/27
 * @Email:tao8.wang@changhong.com
 */
public class TestMain {
    public static void main(String[] args) {
        MyPluginChain chain = new MyPluginChain();
        chain.addInterceptor(new Interceptor1("this is Interceptor1"));
        chain.addInterceptor(new Interceptor1("this is Interceptor2"));
        chain.addInterceptor(new Interceptor1("this is Interceptor3"));
        Object p = chain.plugInAll(new MyPrint());
//        MyPrint mp = (MyPrint) p;//报错，只能转换成他的接口类，任何一个接口类都可以
        MyPrintInterface m = (MyPrintInterface) p;
        m.print("aaa");
        m.abc("abc");

        MyPrintInterface2 m2 = (MyPrintInterface2) p;
        m2.print2("aaa2");
        m2.abc2("abc2");
    }
}
