package com.wt.entity.com.wt.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description
 * @author: wangtao
 * @date:13:26 2018/1/20
 * @email:tao8.wang@changhong.com
 */
@Setter(value = AccessLevel.PUBLIC)
@Getter
public class GetterSetterExample {
    private final String finalStr = "";
    private static String staticStr;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String name;
    @Setter(value = AccessLevel.PUBLIC, onMethod = @__({@NonNull}), onParam = @__({@NonNull}))
    private Date birthday;
    @Accessors(prefix = "wt")
    private String wtFirstName;
    private boolean flag;

    public static void main(String[] args) {
        GetterSetterExample example = new GetterSetterExample();
        example.setBirthday(new Date());
        System.out.println(example.getBirthday());
    }
}
