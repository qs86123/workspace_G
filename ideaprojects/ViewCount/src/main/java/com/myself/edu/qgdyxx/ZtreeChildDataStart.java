package com.myself.edu.qgdyxx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.qgdyxx.bean.ZZJGEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: wangtao
 * @date:17:24 2017/12/28
 * @email:tao8.wang@changhong.com
 */
public class ZtreeChildDataStart {
    public static void main(String[] args) throws Exception {
        System.out.println("----------login---VPN");
        String cookie = VpnLogin.loginvpn();
        System.out.println("----------login---WEB");
        String webcookie = WebLogin.loginweb(cookie);
        System.out.println("----------zTree---start");

        File file = new File("D:zuzhijigou.txt");
        JSONArray ja = JSONArray.parseArray(readFile(file));
        int i = 0;
        String orgCode;
        List<String> sqls = new ArrayList<>();
        for (i = 0; i < ja.size(); i++) {
            JSONObject j = ja.getJSONObject(i);
            if (j.getString("is_parent").equals("true")) {
                orgCode = j.getString("id");
                ZTreeChildData.zTree(webcookie + cookie, orgCode, "D:childztree_" + i + ".txt");
            }
        }
    }

    public static String readFile(File file) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        //使用readLine方法，一次读一行
        while ((s = br.readLine()) != null) {
            sb.append(System.lineSeparator() + s);
        }
        br.close();
        return sb.toString();
    }
}
