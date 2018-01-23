package com.wt.entity.com.wt.lombok;

import com.sun.istack.internal.Nullable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

/**
 * @description
 * @author: wangtao
 * @date:10:54 2018/1/22
 * @email:tao8.wang@changhong.com
 */
@Data(staticConstructor = "newInstence")
public class DataExample {
    private final String a = "a";
    private static String b = "b";
    @NonNull
    private String firstName;
    @Setter(AccessLevel.PROTECTED)
    private String lastName;
    private int age;
    @Nullable
    private Integer sex;
}
