package com.changhong.yywl.data.analyse.common.jdbc.entity;

import java.util.List;

/**
 * 柱状图及折线图返回实体类
 * Created by Administrator on 16-8-16.
 */
public class BarGraphResponseEntity {
    private List<String> timeList;
    private List<BarEntity> itemData;

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> name) {
        this.timeList = name;
    }

    public List<BarEntity> getItemData() {
        return itemData;
    }

    public void setItemData(List<BarEntity> data) {
        this.itemData = data;
    }
}
