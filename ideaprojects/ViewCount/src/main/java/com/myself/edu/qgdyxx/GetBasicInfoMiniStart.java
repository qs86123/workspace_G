package com.myself.edu.qgdyxx;

import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.utils.MysqlUtils;
import com.myself.edu.qgdyxx.bean.BasicInfoMiniEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:20:36 2017/11/6
 * @Email:tao8.wang@changhong.com
 */
public class GetBasicInfoMiniStart {
    public static void main(String[] args) throws Exception {
        System.out.println("----------login---VPN");
        String cookie = VpnLogin.loginvpn();
        System.out.println("----------login---WEB");
        String webcookie = WebLogin.loginweb(cookie);
        String cookieAll = webcookie + cookie;
//        String cookieAll = "mapid=64a0a160;JSESSIONID=F2B89949F4031AB135D355255C9BA800.csdj1;SRV=d6e3d598-0f80-4278-8cc6-10b3042f8564;VSG_SESSIONID=918978227;";
        System.out.println(cookieAll);
        String uuid = "";
        String organid = "";
        Connection conn = MysqlUtils.getConnection("127.0.0.1", "3306", "qgdyxx", "root", "123456");
        PreparedStatement ps = null;
        ResultSet rs = null;
        String content = "";
        int pageSize = 100;
        JSONObject json = null;
        BasicInfoMiniEntity basicInfo = null;
        List<String> sqls = new ArrayList<>();
        int count = 0;
        for (; ; ) {
            ps = conn.prepareStatement("select uuid,organid from dyxx_base_info where uuid not in (select uuid from basic_info_mini) limit " + pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                json = new JSONObject();
                uuid = rs.getString("uuid");
                organid = rs.getString("organid");
                System.out.println(uuid + ":" + organid + "--->begain");
                content = GetBasicInfoMini.getBasicInfoMini(cookieAll, uuid, organid);
//                System.out.println(content);
                Document doc = Jsoup.parse(content);
                Elements elements = doc.select("#saveForm input[type=hidden]");
                for (Element e : elements) {
                    String name = e.attr("name");
                    String value = e.attr("value");
                    if (name != null && !"".equals(name.trim())) {
                        json.put(name, value);
                    }
                }
                System.out.println(json.toJSONString());
                basicInfo = new BasicInfoMiniEntity(uuid, organid, json.toJSONString());
                String sql = basicInfo.toSql("basic_info_mini");
                System.out.println(sql);
                sqls.add(sql);
                System.out.println(uuid + "------>toSql");
                System.out.println(count++);
            }
            if (sqls.size() == 0) {
                break;
            }
            MysqlUtils.batchSql(sqls, conn);
            sqls.clear();
        }
        System.out.println("共处理<" + count + ">条数据");

    }

}
