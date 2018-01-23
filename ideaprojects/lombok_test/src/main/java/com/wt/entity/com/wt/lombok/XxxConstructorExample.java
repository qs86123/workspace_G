package com.wt.entity.com.wt.lombok;

import lombok.*;

/**
 * @description
 * @author: wangtao
 * @date:9:59 2018/1/22
 * @email:tao8.wang@changhong.com
 */
@NoArgsConstructor(staticName = "newInstence", access = AccessLevel.PROTECTED, onConstructor = @__({@MyAnon}))
@RequiredArgsConstructor
@AllArgsConstructor
public class XxxConstructorExample<T> {
    private final String a = "aaa";
    private static String b = "bbb";
    private T t;
    @NonNull
    private String name;
    private int age;
}
