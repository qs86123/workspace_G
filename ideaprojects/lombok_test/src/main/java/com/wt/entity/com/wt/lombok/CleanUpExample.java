package com.wt.entity.com.wt.lombok;

import lombok.Cleanup;

/**
 * @description
 * @author: wangtao
 * @date:13:32 2018/1/20
 * @email:tao8.wang@changhong.com
 */
public class CleanUpExample {

    public void close() {
        System.out.println("调用了close()方法");
    }
    public void myCloseMethod() {
        System.out.println("调用了myCloseMethod()方法");
    }

    public static void main(String[] args) {
        @Cleanup("myCloseMethod") CleanUpExample clean = new CleanUpExample();
        System.out.println("测试@CleanUp");
    }
}
