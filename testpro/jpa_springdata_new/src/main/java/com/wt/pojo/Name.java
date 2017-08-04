package com.wt.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:08 2017/8/4
 * @Email:tao8.wang@changhong.com
 */
//Name作为Contact的属性，这里使用@Embeddable注解，该实体的属性会直接创建在contact表里面，而不会创建name表以id关联
@Embeddable
public class Name {

    //option属性：指定属性是否能为null
    @Basic(optional = false)
    private String first;

    private String last;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Name{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
