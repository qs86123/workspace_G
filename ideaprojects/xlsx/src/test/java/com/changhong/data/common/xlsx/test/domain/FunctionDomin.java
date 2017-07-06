package com.changhong.data.common.xlsx.test.domain;

import com.changhong.data.common.xlsx.annotation.Xlsx;
import com.changhong.data.common.xlsx.annotation.XlsxColumn;

import java.util.Date;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月18日
 */
@Xlsx(value = "data",size = 500001)
public class FunctionDomin {
    @XlsxColumn("日期数据")
    private Date date;
    @XlsxColumn("字符串数据")
    private String string;
    @XlsxColumn("整数数据")
    private int anInt;
    @XlsxColumn("浮点数数据")
    private double aDouble;
    @XlsxColumn("布尔数据")
    private boolean aBoolean;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public boolean getABoolean() {
        return aBoolean;
    }

    public void setABoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public double getADouble() {
        return aDouble;
    }

    public void setADouble(double aDouble) {
        this.aDouble = aDouble;
    }
}
