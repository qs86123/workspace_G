package com.myself.edu.qgdyxx;

import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.utils.MysqlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description
 * @author: wangtao
 * @date:9:05 2017/12/28
 * @email:tao8.wang@changhong.com
 */
public class Test {
    public static void main1(String[] args) throws IOException {
        Document doc = Jsoup.parse(new File("C:\\Users\\The_kid\\Desktop\\111.txt"), "UTF-8");
        Elements elements = doc.select("#saveForm input[type=hidden]");
        JSONObject json = new JSONObject();
        for (Element e : elements) {
            String name = e.attr("name");
            String id = e.attr("id");
            String value = e.attr("value");
            if (name != null && !"".equals(name.trim())) {
                System.out.println(name + "=" + value);
                json.put(name, value);
            }
        }
        System.out.println(json.toJSONString());
        System.out.println("-----------------");
        for (String key : json.keySet()) {
            System.out.println(key + "=" + json.getString(key));
        }
    }

    public static void main(String[] args) throws Exception {
        Connection conn = MysqlUtils.getConnection("127.0.0.1", "3306", "qgdyxx", "root", "123456");
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = conn.prepareStatement("select a.name,b.real_name,b.identity_card,c.content from update_base_info a " +
                "left join dyxx_base_info b on b.identity_card=a.idcard " +
                "LEFT JOIN basic_info_mini c on c.uuid = b.uuid " +
                "where c.content is not null and a.name=b.real_name");
        rs = ps.executeQuery();
        String name = "";
        JSONObject json = null;
        String name2 = "";
        int i = 0;
        while (rs.next()) {
            name = rs.getString("name");
            json = JSONObject.parseObject(rs.getString("content"));
            name2 = json.getString("oldXm");
            if (!name.equals(name2)) {
                System.out.println(name + ":" + name2);
            }
            ++i;
        }
        System.out.println(i);

    }
}
