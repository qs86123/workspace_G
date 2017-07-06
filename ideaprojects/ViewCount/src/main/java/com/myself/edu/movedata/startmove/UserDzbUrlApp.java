package com.myself.edu.movedata.startmove;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.CHAppList2;
import com.myself.edu.movedata.CHAppLoginByAdmin;
import com.myself.edu.movedata.InputApp;
import com.myself.edu.movedata.parse.bean.UsrCashMonth;
import com.myself.edu.movedata.utils.MysqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:23 2017/6/28
 * @Email:tao8.wang@changhong.com
 */
public class UserDzbUrlApp {

    private static final Logger logger = LoggerFactory.getLogger(UserDzbUrlApp.class);

    private static Connection connection = null;

    public static void main(String[] args) throws Exception {
        connection = MysqlUtils.getConnection("172.17.123.193", "8096", "momx_dev", "momx_dev", "momx_dev20161019");
        String sql = "select username from movedata_dzb";
        List<String> dzbs = MysqlUtils.selectAllDzb(sql, connection);
        for (String username : dzbs)
            once(username, "123456");
//        once("DZB13", "123456");
//        once("DZB14", "123456");
    }

    public static void once(String userName, String password) throws Exception {
        logger.info("--------------------<" + userName + ">" + "----------------------------");
        String jsessionid = CHAppLoginByAdmin.login(userName, password);
        if (jsessionid.equals("")) {
            logger.info("党组织部<" + userName + ">" + "登录失败");
            return;
        }
        String userListByDZB = CHAppList2.List2(jsessionid);
        System.out.println(userListByDZB);
        JSONObject json = JSON.parseObject(userListByDZB);
        if (json == null) {
            logger.error("党组织部<" + userName + ">" + "list2为空");
            return;
        }
        JSONArray ja = json.getJSONArray("data");
        List<String> sqls = new ArrayList<>();
        StringBuilder sb = null;
        for (int i = 0; i < ja.size(); i++) {
            JSONObject j = ja.getJSONObject(i);
            System.out.println(j.toJSONString());
            String name = j.getString("name");
            String userId = j.getString("id");
            System.out.println(name + userId);

            logger.info("开始执行<" + userName + ">" + "{name=" + name + "useId" + userId + "} 党费录入");
            sb = new StringBuilder();
            sb.append("insert into user_dzb_url (name,dzbusername) values(")
                    .append("'" + name + "',")
                    .append("'" + userName + "');");
            sqls.add(sb.toString());
        }
        MysqlUtils.batchSql(sqls,connection);
    }

}
