package com.myself.edu.qgdyxx.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.utils.MysqlUtils;
import com.myself.edu.qgdyxx.bean.DangyuanEntity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:37 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public class DangyaunInfoParse extends AbstractParse {

    public DangyaunInfoParse() {
        init();
    }

    public DangyaunInfoParse(String file) {
        this.file = file;
        init();
    }

    @Override
    public Object parse() {
        JSONObject json = JSON.parseObject(data);
        JSONArray ja = json.getJSONArray("info");
        int i = 0;
        List<String> sqls = new ArrayList<>();
        for (i = 0; i < ja.size(); i++) {
            JSONObject j = ja.getJSONObject(i);
            DangyuanEntity dangyuanEntity = JSONObject.parseObject(j.toJSONString(), DangyuanEntity.class);
            String sql = dangyuanEntity.toSql("dyxx_base_info") + ";";
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
