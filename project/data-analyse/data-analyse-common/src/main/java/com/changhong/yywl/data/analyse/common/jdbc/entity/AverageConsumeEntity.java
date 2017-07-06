package com.changhong.yywl.data.analyse.common.jdbc.entity;

import java.math.BigDecimal;

/**
 * 平均消费实体类
 * Created by Administrator on 16-9-7.
 */
public class AverageConsumeEntity {
    private String time;//时间点
    private BigDecimal avgConsume;//消费额

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public BigDecimal getAvgConsume() {
        return avgConsume;
    }

    public void setAvgConsume(BigDecimal avgConsume) {
        this.avgConsume = avgConsume;
    }
}
