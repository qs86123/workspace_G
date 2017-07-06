package com.changhong.data.jxw.report.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Zhoufan [https://git.oschina.net/fantazy/].
 * @Date 2017/3/15
 * @Description:日期工具
 */
public class DateUtils {

    public static boolean isValidate(String date,String regex){
        SimpleDateFormat sdf = new SimpleDateFormat(regex);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static String format(Date date,String regex){
        SimpleDateFormat sdf = new SimpleDateFormat(regex);
        return  sdf.format(date);
    }
}
