package com.myself.edu.qgdyxx.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.qgdyxx.bean.ZZJGEntity;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:32 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public class ZTreeParse extends AbstractParse {

    public ZTreeParse() {
    }

    public ZTreeParse(String file) {
        this.file = file;
    }

    @Override
    public Object parse() {
        JSONArray ja = JSONArray.parseArray(data);
        int i = 0;
        for (i = 0; i < ja.size(); i++) {
            JSONObject j = ja.getJSONObject(i);
            ZZJGEntity zzjgEntity = JSONObject.parseObject(j.toJSONString(), ZZJGEntity.class);
            System.out.println(zzjgEntity.toString());
        }
        System.out.println("共处理了： < " + i + " > 条数据");
        return i;
    }
}
