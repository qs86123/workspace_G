package com.myself.edu.movedata.compare.util;

import com.myself.edu.movedata.compare.bean.Cash;
import com.myself.edu.utils.Logger;

import java.math.BigDecimal;

/**
 * 数据对比
 * @author wanglei
 */
public class DataCompare {
    public static boolean compare(Cash cash1, Cash cash2){
        boolean bool = true;
        //验证名字
        if(ValidateUtil.checkStr(cash1.getUsername()) && ValidateUtil.checkStr(cash2.getUsername())){
            if(!cash1.getUsername().equals(cash2.getUsername())){
                Logger.INS.info("名称不一致");
                return false;
            }
        }

        //验证名字
        if(ValidateUtil.checkStr(cash1.getZzjg()) && ValidateUtil.checkStr(cash2.getZzjg())){
            if(!cash1.getZzjg().equals(cash2.getZzjg())){
                Logger.INS.info("组织机构不一致");
                return false;
            }
        }

        //验证1月
        if(ValidateUtil.checkStr(cash1.getMonth1()) && ValidateUtil.checkStr(cash2.getMonth1())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth1());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth1());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("一月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证2月
        if(ValidateUtil.checkStr(cash1.getMonth2()) && ValidateUtil.checkStr(cash2.getMonth2())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth2());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth2());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("二月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证3月
        if(ValidateUtil.checkStr(cash1.getMonth3()) && ValidateUtil.checkStr(cash2.getMonth3())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth3());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth3());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("三月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证4月
        if(ValidateUtil.checkStr(cash1.getMonth4()) && ValidateUtil.checkStr(cash2.getMonth4())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth4());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth4());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("四月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证5月
        if(ValidateUtil.checkStr(cash1.getMonth5()) && ValidateUtil.checkStr(cash2.getMonth5())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth5());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth5());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("五月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证6月
        if(ValidateUtil.checkStr(cash1.getMonth6()) && ValidateUtil.checkStr(cash2.getMonth6())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth6());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth6());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("六月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证7月
        if(ValidateUtil.checkStr(cash1.getMonth7()) && ValidateUtil.checkStr(cash2.getMonth7())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth7());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth7());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("七月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证8月
        if(ValidateUtil.checkStr(cash1.getMonth8()) && ValidateUtil.checkStr(cash2.getMonth8())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth8());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth8());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("八月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证9月
        if(ValidateUtil.checkStr(cash1.getMonth9()) && ValidateUtil.checkStr(cash2.getMonth9())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth9());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth9());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("九月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }
        //验证10月
        if(ValidateUtil.checkStr(cash1.getMonth10()) && ValidateUtil.checkStr(cash2.getMonth10())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth10());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth10());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("十月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }

        //验证11月
        if(ValidateUtil.checkStr(cash1.getMonth11()) && ValidateUtil.checkStr(cash2.getMonth11())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth11());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth11());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("十一月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }

        //验证12月
        if(ValidateUtil.checkStr(cash1.getMonth12()) && ValidateUtil.checkStr(cash2.getMonth12())){
            BigDecimal decimal1 = new BigDecimal(cash1.getMonth12());
            decimal1.setScale(2);
            BigDecimal decimal2 = new BigDecimal(cash2.getMonth12());
            decimal2.setScale(2);
            if(decimal1.compareTo(decimal2) != 0){
                Logger.INS.info("十二月份不一致,市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
                bool = false;
            }
            else {
                Logger.INS.info("市党委数据："+ decimal1.toString()+"数据库数据"+decimal2.toString());
            }
        }

        return bool;
    }



    public static void main(String[] args) {
        Cash cash1 = new Cash();
        Cash cash2 = new Cash();
        cash1.setUsername("aaaa");
        cash2.setUsername("aaaa");
        cash1.setZzjg("zzjg");
        cash2.setZzjg("zzjg");
        cash1.setMonth1("123.00");
        cash2.setMonth1("123");
        cash1.setMonth2("123");
        cash2.setMonth2("123");
        cash1.setMonth3("123");
        cash2.setMonth3("123");
        cash1.setMonth4("123.00");
        cash2.setMonth4("123");
        cash1.setMonth5("123");
        cash2.setMonth5("123");
        cash1.setMonth6("123");
        cash2.setMonth6("123");
        cash1.setMonth7("123.00");
        cash2.setMonth7("123");
        cash1.setMonth8("123");
        cash2.setMonth8("123");
        cash1.setMonth9("123");
        cash2.setMonth9("123");
        cash1.setMonth10("123");
        cash2.setMonth10("123");
        cash1.setMonth11("123");
        cash2.setMonth11("123");
        cash1.setMonth12("123");
        cash2.setMonth12("123");
        System.out.println(compare(cash1, cash2));
    }
}
