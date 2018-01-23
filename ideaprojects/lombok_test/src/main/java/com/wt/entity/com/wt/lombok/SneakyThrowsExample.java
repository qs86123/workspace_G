package com.wt.entity.com.wt.lombok;

import lombok.SneakyThrows;

/**
 * @description
 * @author: wangtao
 * @date:16:21 2018/1/22
 * @email:tao8.wang@changhong.com
 */
public class SneakyThrowsExample {

    @SneakyThrows({NullPointerException.class})
    public void print(Object s) throws Exception {
        System.out.println(s.toString());
    }

    @SneakyThrows({NullPointerException.class})
    public void print2(Object s) {
        System.out.println(s.toString());
    }

    public static void main(String[] args) {
        try {
            new SneakyThrowsExample().print(null);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        new SneakyThrowsExample().print2(null);
    }
}
