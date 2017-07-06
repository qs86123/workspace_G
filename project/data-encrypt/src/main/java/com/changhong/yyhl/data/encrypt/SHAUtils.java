package com.changhong.yyhl.data.encrypt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * 通过sha-256加密方法类
 * Created by Administrator on 16-9-13.
 */
public class SHAUtils {
    private static long count=0;
    private static String key="";
    static{
        Properties properties=new Properties();
        try {
            //如果配置文件存在，从类路径下加载获取key
            properties.load(SHAUtils.class.getClassLoader().getResourceAsStream("key.properties"));
            key=properties.getProperty("key");
        } catch (Exception e) {
        }
    }

    /**
     * 将timestamp和key组合起来通过sha-256加密成16进制的字符串
     * @param timestamp 时间戳,毫秒级
     * @param count 计数器
     * @param key 密钥
     * @return
     */
    public static String digest(String timestamp,String count,String key)
    {
        String plain=timestamp+count+key;
        return digestToHexString(plain.getBytes());
    }

    /**
     * 同上，使用默认key
     * @param timestamp 时间戳，毫秒级
     * @param count
     * @return
     */
    public static String digestDefaultKey(String timestamp,String count)
    {
        String plain=timestamp+count+key;
        return digestToHexString(plain.getBytes());
    }

    /**
     * 根据时间戳与密钥，生成验证权限的url的请求参数
     * @param timestamp 时间戳，毫秒级
     * @param key 密钥
     * @return
     */
    public static String requestParam(String timestamp,String key)
    {
        String count=nextLong()+"";
        return "?t="+timestamp+"&ac="+digest(timestamp,count, key)+"&cnt="+count;
    }

    /**
     * 同上，使用默认key
     * @param timestamp 时间戳，毫秒级
     * @return
     */
    public static String requestParamDefaultKey(String timestamp)
    {
        String count=nextLong()+"";
        return "?t="+timestamp+"&ac="+digest(timestamp,count, key)+"&cnt="+count;
    }

    /**
     * 根据密钥，时间使用当前服务器时间，生成验证权限的url的请求参数
     * @param key 密钥
     * @return
     */
    public static String requestParam(String key)
    {
        String timestamp=System.currentTimeMillis()+"";
        String count=nextLong()+"";
        return "?t="+timestamp+"&ac="+digest(timestamp,count, key)+"&cnt="+count;
    }

    /**
     * 同上，使用默认key
     * @return
     */
    public static String requestParamDefaultKey()
    {
        String timestamp=System.currentTimeMillis()+"";
        String count=nextLong()+"";
        return "?t="+timestamp+"&ac="+digest(timestamp,count, key)+"&cnt="+count;
    }



    /**
     * 将明文字节数组加密成16进制密文字符串
     * @param plain 明文字节数组
     * @return
     */
    private static String digestToHexString(byte[] plain)
    {
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("sha-256");
            byte[] cipher=messageDigest.digest(plain);
            return toHexString(cipher);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 将字节数组转化成16进制字符串
     * @param bytes
     * @return
     */
    private static String toHexString(byte[] bytes)
    {
        if(bytes==null)
        {
            return null;
        }
        StringBuffer stringBuffer=new StringBuffer();
        for (int i=0;i<bytes.length;i++)
        {
            stringBuffer.append(Integer.toHexString(bytes[i]&0x000000ff));
        }
        return stringBuffer.toString();
    }

    /**
     * 线程安全，使计数器增1
     * @return
     */
    private static synchronized long nextLong()
    {
        if(count==Long.MAX_VALUE)
        {
            count=0;
        }
        return count++;
    }

}
