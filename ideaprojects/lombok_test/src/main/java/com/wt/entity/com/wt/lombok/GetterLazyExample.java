package com.wt.entity.com.wt.lombok;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: wangtao
 * @date:17:05 2018/1/22
 * @email:tao8.wang@changhong.com
 */
public class GetterLazyExample {

    @Getter(lazy = true)
    private final double cached = expensive();
    @Setter
    @Getter
    private List<Double> cacheValue;

    private double expensive() {
        double result = 0;
        for (int i = 0; i < cacheValue.size(); i++) {
            result += cacheValue.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        GetterLazyExample example = new GetterLazyExample();
        List<Double> ds = new ArrayList<>();
        ds.add(1d);
        ds.add(2d);
        ds.add(3d);
        example.setCacheValue(ds);
        System.out.println(example.getCached());
        example.getCacheValue().add(4d);
        System.out.println(example.getCached());
    }
}
