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
        System.out.println("----------login---VPN");
        String cookie = VpnLogin.loginvpn();
        System.out.println("----------login---WEB");
        String webcookie = WebLogin.loginweb(cookie);
        String cookieAll = "";
        cookieAll = webcookie + cookie;
//        cookieAll = "mapid=64a0a160;JSESSIONID=CFEC1D758628D88B26E1FA91FD8EC5F3.csdj1;SRV=3533ff4c-eb4e-47be-bef7-17ec40ca86cb;VSG_SESSIONID=172271669;";
        System.out.println(cookieAll);
        List<NameValuePair> list = new ArrayList<>();
        int pageSize = 100;
        Connection conn = MysqlUtils.getConnection("127.0.0.1", "3306", "qgdyxx", "root", "123456");
        String sql = "insert into update_record (`uuid`,`organid`,`name`,`idcard`) values(";
        //查询另一个表update_base_info的数据
        PreparedStatement ps = conn.prepareStatement("select a.*,c.content from update_base_info a " +
                "left join dyxx_base_info b on b.identity_card=a.idcard " +
                "LEFT JOIN basic_info_mini c on c.uuid = b.uuid " +
                "where a.name=b.real_name and a.idcard not in (select idcard from update_record) and a.idcard='220122199005151111'");
        ResultSet rs = ps.executeQuery();
        UpdateRequest ur = null;
        while (rs.next()) {
            String content = rs.getString("content");
            if (content == null || content.trim().equals("")) {
                continue;
            }
            JSONObject json = JSONObject.parseObject(content);
            for (String key : json.keySet()) {
                list.add(new BasicNameValuePair(key, json.getString(key)));
            }
            ur = new UpdateRequest();
            String name = rs.getString("name");
            ur.setDyInfo_xm(name);
            ur.setDyInfo_xb(rs.getString("sex"));
            ur.setDyInfo_mz(rs.getString("mz"));
            String idcard = rs.getString("idcard");
            ur.setDyInfo_zjhm(idcard);
            ur.setDyInfo_csrq(rs.getString("birthday"));
            ur.setDyInfo_xl(rs.getString("xl"));
            ur.setDyInfo_dylb(rs.getString("person_type"));
            ur.setDyInfo_rdsj(rs.getString("join_org_date"));
            ur.setDyInfo_zzsj(rs.getString("change_date"));
            ur.setDyInfo_gzgw(rs.getString("work_unit"));
            ur.setDyInfo_lxdh(rs.getString("tel"));
            ur.setDyInfo_qh(rs.getString("phone"));
            ur.setDyInfo_dh(rs.getString("phone"));
            ur.setDyInfo_xjzd(rs.getString("home_address"));
            ur.setDyInfo_lkzgzzrq("");
            ur.setDyInfo_iscontact(rs.getString("is_notconnect"));
            ur.setDyInfo_losttimeStr(rs.getString("not_connect_time"));
            ur.setDyInfo_sfzld(rs.getString("is_flow"));
            ur.setDyInfo_wclx(rs.getString("flow_direction"));
            list.addAll(ur.getNameValuePairList());
            String result = UpdateMemberBaseInfo.updateMemberBaseInfo(cookieAll, list);
            JSONObject j = JSONObject.parseObject(result);
            if (j.getString("result").equals("0")) {
                PreparedStatement p = conn.prepareStatement(sql + "'" + json.getString("dyInfo.userid") + "'," + "'" + json.getString("dyInfo.organid") + "'," + "'" + name + "'," + "'" + idcard + "')");
                p.executeUpdate();
            } else {
                System.out.println(result);
            }
        }

    }

}
