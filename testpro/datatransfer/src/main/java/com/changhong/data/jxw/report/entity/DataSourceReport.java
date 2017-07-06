package com.changhong.data.jxw.report.entity;
import java.util.Date;
/**
 * @Author Zhoufan [https://git.oschina.net/fantazy/].
 * @Date 2017/3/14
 * @Description:数据源报表
 */
public class DataSourceReport {

    private String id;

    private Date date;

    private Integer cnt;

    private Integer increasement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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