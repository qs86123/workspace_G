package com.myself.edu.movedata.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.utils.MysqlUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:06 2017/6/28
 * @Email:tao8.wang@changhong.com
 */
public class ParseJsonToDB {

    public static void main(String[] args) throws Exception {
        File file = new File("G:\\Program Files\\Tencent\\QQdata\\386427665\\FileRecv\\dd.json");
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        // 一次读入一行，直到读入null为文件结束
        String tempString = "";
        int line = 1;
        while ((tempString = reader.readLine()) != null) {
            // 显示行号
            sb.append(tempString);
        }
        JSONArray jsons = JSONArray.parseArray(sb.toString());
        List<String> sqls = new ArrayList<>();
        StringBuilder sql = null;
        for (int i = 0; i < jsons.size(); i++) {
            sql = new StringBuilder();
            JSONObject json = jsons.getJSONObject(i);
            sql.append("insert into movedata_dzb (id,rely_id,site_name,type,username) values(")
                    .append("'" + json.getString("id") + "',")
                    .append("'" + json.getString("relyId") + "',")
                    .append("'" + json.getString("siteName") + "',")
                    .append("'" + json.getString("type") + "',")
                    .append("'" + json.getString("userName") + "');");
            sqls.add(sql.toString());
        }
        MysqlUtils.batchSql(sqls, "172.17.123.193", "8096", "momx_dev", "momx_dev", "momx_dev20161019");
    }

}
