package com.wt.pojo;

import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:07 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "formula_entity")
public class FormulaEntity extends AbstractMappedType {

    private double credit;
    private double rate;
    //抽象列，不建立数据库表字段，但是查询时使用，value提供sql片段，查询是转换成：select ${value} from .....
    //详细查看sql语句，跟ColumnTransformer的read和write属性差不多
    @Formula(value = "credit * rate")
    private double interest;
    //子查询会直接作为一个属性，所以查询出来的结果必须是单列的，必须只能有一条记录，必须使用别名，否则会使用本实体的别名
    @Formula(value = "(select u.time_stamp from date_entity u where u.id = rate*10)")
    private Date date;

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FormulaEntity{" +
                "credit=" + credit +
                ", rate=" + rate +
                ", interest=" + interest +
                ", date=" + date +
                '}';
    }
}
