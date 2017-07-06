package com.changhong.yywl.data.analyse.common.jdbc.entity;

/**
 * 总注册用户实体类
 * Created by Administrator on 16-9-7.
 */
public class AllUserCountEntity {
    private String userType;//用户类型
    private Integer userCount;//用户总数

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
}
