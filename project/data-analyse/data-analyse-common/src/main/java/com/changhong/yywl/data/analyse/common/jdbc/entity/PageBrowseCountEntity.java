package com.changhong.yywl.data.analyse.common.jdbc.entity;

/**
 * 页面访问数实体类
 * Created by Administrator on 16-9-7.
 */
public class PageBrowseCountEntity {
    private String time;//时间点
    private int count;//访问人数

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
