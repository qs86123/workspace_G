package com.changhong.data.jxw.report.entity;

/**
 * @Author Zhoufan [https://git.oschina.net/fantazy/].
 * @Date 2017/3/15
 * @Description:数据交易报表
 */
public class TradeReport {
    private String id;

    private String date;

    private Integer cnt;

    private Integer increasement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Integer getIncreasement() {
        return increasement;
    }

    public void setIncreasement(Integer increasement) {
        this.increasement = increasement;
    }
}
