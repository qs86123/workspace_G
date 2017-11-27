package com.chaonghong.data.test;

public class CgLibMain
{
    public static void main(String[] args)
    {
        CgLibTest tt = new CgLibTest();

        SayHello proxyImp = (SayHello) tt.getProxy(SayHello.class);

        proxyImp.say();
    }
}
