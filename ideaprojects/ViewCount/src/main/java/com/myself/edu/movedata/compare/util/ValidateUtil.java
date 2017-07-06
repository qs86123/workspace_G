package com.myself.edu.movedata.compare.util;

/**
 * 数据验证工具
 * 2017年5月12日
 * @author wanglei
 */
public class ValidateUtil {
    /**
     * 检验Str格式
     * @param str String
     * */
    public static boolean checkStr(String str){
        if(str != null){
            if(str.trim().length()!=0){
                return true;
            }
        }
        return false;
    }
    /**
     * 检验Str格式
     * @param str String
     * @param maxLength int 去首尾空格后的长度
     * */
    public static boolean checkStr(String str, int maxLength){
        if(checkStr(str)){
            if(str.trim().length() <= maxLength){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args)throws Exception{
        String str = " aaaaa ";
        System.out.println(checkStr(str, 4));

    }
}