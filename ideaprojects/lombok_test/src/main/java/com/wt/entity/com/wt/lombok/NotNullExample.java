package com.wt.entity.com.wt.lombok;

import com.sun.istack.internal.NotNull;
import lombok.NonNull;

/**
 * @description
 * @author: wangtao
 * @date:13:01 2018/1/20
 * @email:tao8.wang@changhong.com
 */
public class NotNullExample {

    public void get(@NonNull String a) {
        System.out.println(a);
    }

    public static void main(String[] args) {
        new NotNullExample().get(null);
    }

}
