package com.myself.edu.qgdyxx.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.utils.MysqlUtils;
import com.myself.edu.qgdyxx.bean.ZZJGEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:32 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public class ZTreeParse extends AbstractParse {

    public ZTreeParse() {
        init();
    }

    public ZTreeParse(String file) {
        this.file = file;
        init();
    }

    @Override
    public Object parse() {
        JSONArray ja = JSONArray.parseArray(data);
        int i = 0;
        List<String> sqls = new ArrayList<>();
        for (i = 0; i < ja.size(); i++) {
            JSONObject j = ja.getJSONObject(i);
            ZZJGEntity zzjgEntity = JSONObject.parseObject(j.toJSONString(), ZZJGEntity.class);
            String sql = zzjgEntity.toSql("zzjg_base_info") + ";";
            System.out.println(sql);
            sqls.add(sql);
        }
        try {
            MysqlUtils.batchSql(sqls, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("共处理了： < " + i + " > 条数据");
        return i;
    }
}
