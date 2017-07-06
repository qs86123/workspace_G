package com.changhong.yywl.data.analyse.common.jdbc.entity;

import java.util.List;

/**
 * 柱状图及折线图节点数据
 * Created by Administrator on 16-8-16.
 */
public class BarEntity<T extends Number> {
    private String itemName;
    private List<T> itemCount;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<T> getItemCount() {
        return itemCount;
    }

    public void setItemCount(List<T> itemCount) {
        this.itemCount = itemCount;
    }
}
