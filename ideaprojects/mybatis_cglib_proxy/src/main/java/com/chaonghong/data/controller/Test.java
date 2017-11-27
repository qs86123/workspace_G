package com.chaonghong.data.controller;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:17 2017/11/21
 * @Email:tao8.wang@changhong.com
 */
public class Test {

    public static void main(String[] args) {
        MyInterface proxy = ProxyFactory.getProxy(MyInterface.class, new MyInterfaceProxy());
        proxy.printmsg("aaaa");
        GreateInterface p = ProxyFactory.getProxy(GreateInterface.class, new MyInterfaceProxy());
        p.printlll("1", "2", "3");
    }

}
