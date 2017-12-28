package com.myself.edu.qgdyxx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.utils.MysqlUtils;
import com.myself.edu.qgdyxx.bean.UpdateRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
public class UpdateMemberBaseInfoStart {
    public static void main(String[] args) throws Exception {
//        System.out.println("----------login---VPN");
//        String cookie = VpnLogin.loginvpn();
//        System.out.println("----------login---WEB");
//        String webcookie = WebLogin.loginweb(cookie);
        String cookieAll = "";
        cookieAll = "mapid=64a0a160;JSESSIONID=3385C3365786835126AF415FA3E943E3.csdj1;SRV=079b6108-1995-4124-80bc-4cd63796deba;VSG_SESSIONID=1551471276;";
        System.out.println(cookieAll);
        List<NameValuePair> list = new ArrayList<>();
        int pageSize = 100;
        Connection conn = MysqlUtils.getConnection("127.0.0.1", "3306", "qgdyxx", "root", "123456");
        //查询另一个表update_base_info的数据
        PreparedStatement ps = conn.prepareStatement("select a.*,c.content from update_base_info a " +
                "left join dyxx_base_info b on b.identity_card=a.idcard " +
                "LEFT JOIN basic_info_mini c on c.uuid = b.uuid " +
                "where c.content is not null and a.name=b.real_name");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String content = rs.getString("content");
            JSONObject json = JSONObject.parseObject(content);
            for (String key : json.keySet()) {
                list.add(new BasicNameValuePair(key, json.getString(key)));
            }
            list.addAll(new UpdateRequest().getNameValuePairList());
//            String contenrt = UpdateMemberBaseInfo.updateMemberBaseInfo(cookieAll, list);
            System.out.println(content);
        }

    }

}
