package com.wt.entity.com.wt.lombok;

import lombok.Synchronized;

/**
 * @description
 * @author: wangtao
 * @date:16:40 2018/1/22
 * @email:tao8.wang@changhong.com
 */
public class SynchronizedExample {
    private final Object readLock = new Object();

    @Synchronized
    public static void staticMethod() {
        System.out.println("world");
    }

    @Synchronized
    public int unStaticMethod() {
        return 42;
    }

    @Synchronized("readLock")
    public void customMethod() {
        System.out.println("bar");
    }
}
