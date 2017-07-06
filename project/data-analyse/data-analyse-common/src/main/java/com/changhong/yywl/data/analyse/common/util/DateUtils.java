package com.changhong.yywl.data.analyse.common.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间方法类
 * Created by Administrator on 16-8-29.
 */
public class DateUtils {
    private static String[] hours={
            "00:00","01:00","02:00","03:00","04:00","05:00",
            "06:00","07:00","08:00","09:00","10:00","11:00",
            "12:00","13:00","14:00","15:00","16:00","17:00",
            "18:00","19:00","20:00","21:00","22:00","23:00","24:00"};
    private static DateFormat dateFormatYMD =new SimpleDateFormat("yyyy-MM-dd");
    private static DateTimeFormatter dateTimeFormatterYW=DateTimeFormat.forPattern("yyyyww");
    private static DateFormat dateFormatYM =new SimpleDateFormat("yyyy-MM");

    /**
     * 将整数类的小时转换为24小时制的小时，不合法数据返回null
     * @param hour
     * @return
     */
    public static String hourExchange(int hour)
    {
        if(hour<0||hour>24)
        {
            return null;
        }
        return hours[hour];
    }

    /**
     * 将字符串整数转换为整数，主要用于小时的转换，格式化异常返回-1
     * @param hour
     * @return
     */
    public static int hourToInt(String hour)
    {
        try {
            int h=Integer.parseInt(hour);
            return h;
        }catch (Exception e)
        {
            return -1;
        }
    }

    /**
     * 将yyyy-MM-dd类型字符串解析为Date对象
     * @param s
     * @return
     */
    public synchronized static Date parseYMD(String s)
    {
        try {
            return dateFormatYMD.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将日期格式化为字符串，yyyy-MM-dd
     * @param date
     * @return
     */
    public synchronized static String formatYMD(Date date)
    {
        return dateFormatYMD.format(date);
    }

    /**
     * 将yyyy-ww类型字符串解析为Date对象
     * @param s
     * @return
     */
    public synchronized static Date parseYW(String s)
    {
        return DateTime.parse(s,dateTimeFormatterYW).toDate();
    }
    /**
     * 将日期格式化为字符串，yyyy-ww
     * @param date
     * @return
     */
    public synchronized static String formatYW(Date date)
    {
        return new DateTime(date).toString(dateTimeFormatterYW);
    }

    /**
     * 将yyyy-MM类型字符串解析为Date对象
     * @param s
     * @return
     */
    public synchronized static Date parseYM(String s)
    {
        try {
            return dateFormatYM.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }
    /**
     * 将日期格式化为字符串，yyyy-MM
     * @param date
     * @return
     */
    public synchronized static String formatYM(Date date)
    {
        return dateFormatYM.format(date);
    }

    /**
     * 获取一个字符串组成的时间列表，该列表由start和end参数指定日期之间的日期组成
     * @param category
     * @param start
     * @param end
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<String> dateList(String category,String start,String end)
    {
        List<String> list=new ArrayList<String>();
        if (category.equals("daily"))
        {
            Date startDate=parseYMD(start);
            Date endDate=parseYMD(end);
            for (;startDate.getTime()<=endDate.getTime();startDate.setTime(startDate.getTime()+24*60*60*1000l))
            {
                list.add(formatYMD(startDate));
            }
            return list;
        }
        if (category.equals("weekly"))
        {
            Date startDate=parseYW(start);
            Date endDate=parseYW(end);
            for (;startDate.getTime()<=endDate.getTime();startDate.setTime(startDate.getTime()+7*24*60*60*1000l))
            {
                list.add(formatYW(startDate));
            }
            return list;
        }
        if (category.equals("monthly"))
        {
            Date startDate=parseYM(start);
            Date endDate=parseYM(end);
            for (;startDate.getTime()<=endDate.getTime();startDate.setMonth(startDate.getMonth()+1))
            {
                list.add(formatYM(startDate));
            }
            return list;
        }
        return null;
    }
}
