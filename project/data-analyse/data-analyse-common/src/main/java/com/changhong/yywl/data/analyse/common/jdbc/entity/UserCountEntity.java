package com.changhong.yywl.data.analyse.common.jdbc.entity;

/**
 * 某时间点的用户数实体类
 * Created by Administrator on 16-9-7.
 */
public class UserCountEntity {
    private String time;//时间点
    private int userCount;//用户数

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
}
