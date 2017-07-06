package com.changhong.yywl.data.analyse.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.PrintStream;
import java.util.Iterator;

/**
 * 检查json格式是否符合要求方法类,测试核心类
 * Created by Administrator on 16-8-16.
 */
public class JsonCheckUtils {
    /**
     * 检查输入字符串是否为json字符串
     * @param data 待判定字符串
     * @return
     */
    public static Boolean isJson(String data)
    {
        try {
            JSONObject jsonObject=JSONObject.fromObject(data);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 检查json是否含有normalJson的字段
     * @param normalJson 标准模板json
     * @param json 待比较json
     * @return
     */
    public static Boolean checkJsonFormat(String normalJson,String json)
    {
        JSONObject jsonObject1=JSONObject.fromObject(normalJson);
        JSONObject jsonObject2=JSONObject.fromObject(json);
        return checkJsonObject(jsonObject1,jsonObject2);
    }

    /**
     * 检查jsonObject是否含有normalJson字段,只判断字段是否存在，不做值类型检查
     * @param normalJsonObject
     * @param jsonObject
     * @return
     */
    public static Boolean checkJsonObject(JSONObject normalJsonObject,JSONObject jsonObject)
    {
        Iterator<String> ite=normalJsonObject.keys();
        while (ite.hasNext())
        {
            String key=ite.next();
            String className=normalJsonObject.get(key).getClass().getSimpleName();
            if(jsonObject.get(key)==null)
            {
                return false;
            }
            else
            {
                if(className.equals("JSONObject"))
                {
                    if("JSONObject".equals(jsonObject.get(key).getClass().getSimpleName()))
                    {
                        Boolean b= checkJsonObject(normalJsonObject.getJSONObject(key), jsonObject.getJSONObject(key));
                        if(b==false)
                        {
                            return b;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                if(className.equals("JSONArray"))
                {
                    if("JSONArray".equals(jsonObject.get(key).getClass().getSimpleName()))
                    {
                        Boolean b= checkJsonArray(normalJsonObject.getJSONArray(key), jsonObject.getJSONArray(key));
                        if(b==false)
                        {
                            return b;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * 检查array元素字段是否含有normalArray元素的字段，假设数组中的各个元素的结构是相同的，
     * 即数组中只含有一种类型的元素
     * @param normalArray
     * @param array
     * @return
     */
    public static Boolean checkJsonArray(JSONArray normalArray,JSONArray array)
    {
        if(normalArray.size()==0)
        {
            if(array.size()==0)
            {
                return true;//如果标准数组的大小为0且待比较数组大小为0表示两个数组相同
            }
            else
            {
                return false;
            }
        }
        if(array.size()==0)
        {
            return false;
        }
        String className=normalArray.get(0).getClass().getSimpleName();
        if("JSONObject".equals(className))
        {
            if("JSONObject".equals(array.get(0).getClass().getSimpleName()))
            {
                return checkJsonObject(normalArray.getJSONObject(0),array.getJSONObject(0));
            }
            else
            {
                return false;
            }
        }
        if("JSONArray".equals(className))
        {
            if("JSONArray".equals(array.get(0).getClass().getSimpleName()))
            {
                return checkJsonArray(normalArray.getJSONArray(0),array.getJSONArray(0));
            }
            else
            {
                return false;
            }
        }
        return true;
    }
}
