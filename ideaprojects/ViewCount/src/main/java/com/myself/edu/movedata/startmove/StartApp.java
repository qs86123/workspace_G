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
public class StartApp {

    private static final Logger logger = LoggerFactory.getLogger(StartApp.class);

    private static Connection connection = null;

    public static void main(String[] args) throws Exception {
        connection = MysqlUtils.getConnection("172.17.123.193", "8096", "momx_dev", "momx_dev", "momx_dev20161019");
//        String sql = "select username from movedata_dzb";
        String sql = "select username from movedata_dzb where site_name in(" +
                "SELECT " +
                "DISTINCT a.zzjg azzjg " +
                "FROM " +
                "user_cash_month a " +
                "LEFT JOIN move_compare1 b ON b.username = a.username " +
                "AND a.zzjg = (" +
                "SELECT " +
                "site_name " +
                "FROM " +
                "movedata_dzb c " +
                "WHERE " +
                "c.username = b.zzjg " +
                ")" +
                "where b.year is null " +
                ")";
//        List<String> dzbs = MysqlUtils.selectAllDzb(sql, connection);
        List<String> dzbs=new ArrayList<>();
//        dzbs.add("CH63");
        dzbs.add("CH02");
//        dzbs.add("CH63");
//        dzbs.add("DZB10");
//        dzbs.add("DZB19");
//        dzbs.add("DZB22");
//        dzbs.add("DZB32");
        System.out.println(dzbs.size());
        for (String username : dzbs)
            once(username, "123456");
            Thread.sleep(2000);
//        once("DZB13", "123456");
//        once("DZB14", "123456");
    }

    public static void once(String userName, String password) {
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
        for (int i = 0; i < ja.size(); i++) {
            JSONObject j = ja.getJSONObject(i);
            System.out.println(j.toJSONString());
            String name = j.getString("name");
            String userId = j.getString("id");
            System.out.println(name + userId);
            System.out.println(name);
            if(!name.equals("郭峰"))
                continue;
            logger.info("开始执行<" + userName + ">" + "{name=" + name + "useId" + userId + "} 党费录入");
            UsrCashMonth ucm = MysqlUtils.select("select a.* from user_cash_month a where a.username='" + name + "' and zzjg=(select b.site_name from movedata_dzb b where b.username='" + userName + "')", connection);
            if (ucm == null) {
                logger.error("用户<" + name + ">未找到");
            } else {
                String[] cashs = ucm.getCashs();
                for (int k = 0; k < 12; k++) {
                    int month = k + 1;
                    String m = month < 10 ? "0" + month : month + "";
                    String startAndEnd = ucm.getYear() + "-" + m + "-01";
                    System.out.println(startAndEnd);
//                    if (!cashs[k].equals("0"))
                    InputApp.inputCost(jsessionid, userId, startAndEnd, startAndEnd, cashs[k]);
                }
            }
        }
    }

}
