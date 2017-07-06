package com.myself.edu.movedata.parse.bean;

import java.util.Arrays;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:22 2017/6/28
 * @Email:tao8.wang@changhong.com
 */
public class UsrCashMonth {

    private String userNo;
    private String name;
    private String zzjg;
    private String year;
    private String[] cashs = new String[12];
    private String cashAll;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZzjg() {
        return zzjg;
    }

    public void setZzjg(String zzjg) {
        this.zzjg = zzjg;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getCashs() {
        return cashs;
    }

    public void setCashs(String[] cashs) {
        this.cashs = cashs;
    }

    public String getCashAll() {
        return cashAll;
    }

    public void setCashAll(String cashAll) {
        this.cashAll = cashAll;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into user_cash_month (user_no,username,zzjg,year,month1,month2,month3,month4,month5,month6,month7,month8,month9,month10,month11,month12,cash_all) values(")
                .append("'" + userNo + "',")
                .append("'" + name + "',")
                .append("'" + zzjg + "',")
                .append("'" + year + "',");
        for (String s : cashs) {
            sb.append("'" + s + "',");
        }
        sb.append("'" + cashAll + "');");
        return sb.toString();
    }

    public String getInsertFirstCompare1DbSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into move_compare1 (user_no,username,zzjg,year,month1,month2,month3,month4,month5,month6,month7,month8,month9,month10,month11,month12,cash_all) values(")
                .append("'" + (userNo == null ? "" : userNo) + "',")
                .append("'" + name + "',")
                .append("'" + zzjg + "',")
                .append("'" + year + "',");
        for (String s : cashs) {
            sb.append("'" + s + "',");
        }
        sb.append("'" + cashAll + "');");
        return sb.toString();
    }

    public String getInsertFirstCompare2DbSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into move_compare2 (user_no,username,zzjg,year,month1,month2,month3,month4,month5,month6,month7,month8,month9,month10,month11,month12,cash_all) values(")
                .append("'" + userNo == null ? "" : userNo + "',")
                .append("'" + name + "',")
                .append("'" + zzjg + "',")
                .append("'" + year + "',");
        for (String s : cashs) {
            sb.append("'" + s + "',");
        }
        sb.append("'" + cashAll + "');");
        return sb.toString();
    }
}
