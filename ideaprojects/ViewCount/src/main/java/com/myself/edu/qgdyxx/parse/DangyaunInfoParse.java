package com.myself.edu.qgdyxx.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.qgdyxx.bean.DangyuanEntity;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:37 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public class DangyaunInfoParse extends AbstractParse {

    public DangyaunInfoParse() {
    }

    public DangyaunInfoParse(String file) {
        this.file = file;
    }

    @Override
    public Object parse() {
        JSONObject json = JSON.parseObject(data);
        JSONArray ja = json.getJSONArray("info");
        int i = 0;
        for (i = 0; i < ja.size(); i++) {
            JSONObject j = ja.getJSONObject(i);
            DangyuanEntity dangyuanEntity = JSONObject.parseObject(j.toJSONString(), DangyuanEntity.class);
            System.out.println(dangyuanEntity.toString());
        }
        System.out.println("共处理了： < " + i + " > 条数据");
        return i;
    }
}
