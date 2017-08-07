package com.wt.pojo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:07 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "date_entity")
public class DateEntity extends AbstractMappedType {

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date time;

    @Column(name = "`time_stamp`")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "DateEntity{" +
                "date=" + date +
                ", time=" + time +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
