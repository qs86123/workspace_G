package com.wt.entity.com.wt.lombok;

import lombok.Builder;
import lombok.Singular;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: wangtao
 * @date:12:48 2018/1/22
 * @email:tao8.wang@changhong.com
 */
public class BuilderExample {
    private String name;
    private String age;
    @Singular
    private List<String> options;
    @Singular
    private Map<String, Object> maps;

    public BuilderExample() {
    }

    @Builder
    public BuilderExample(String name, String age, @Singular List<String> options, @Singular Map<String, Object> maps) {
        this.name = name;
        this.age = age;
        this.options = options;
        this.maps = maps;
    }

    @Builder(builderMethodName = "oneBuilder")
    public OneEntity getOneEntity(String name, int age) {
        OneEntity one = new OneEntity();
        one.setName(name);
        one.setAge(age);
        return one;
    }

    public static void main(String[] args) {
        BuilderExample example = BuilderExample.builder().map("a", "a").map("b", "b").option("option1").option("opiton2").build();
        for (String s : example.options) {
            System.out.println(s);
        }
        for (String s : example.maps.keySet()) {
            System.out.println(s + "=" + example.maps.get(s));
        }
        OneEntity wt = new BuilderExample().oneBuilder().age(23).name("wt").build();
        System.out.println(wt);
    }
}
