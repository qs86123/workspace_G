package com.myself.edu.movedata.pagedatatodb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.CHAppAllPaid;
import com.myself.edu.movedata.CHAppLoginByAdmin;
import com.myself.edu.movedata.ChangeStatus;
import com.myself.edu.movedata.parse.bean.UsrCashMonth;
import com.myself.edu.movedata.startmove.StartApp;
import com.myself.edu.movedata.utils.MysqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:8:46 2017/6/29
 * @Email:tao8.wang@changhong.com
 */
public class PageDataToDb {
    private static final Logger logger = LoggerFactory.getLogger(StartApp.class);

    private static Connection connection = null;

    public static void main(String[] args) throws Exception {
        connection = MysqlUtils.getConnection("172.17.123.193", "8096", "momx_dev", "momx_dev", "momx_dev20161019");
//        String sql = "select username from movedata_dzb where username='CH62'";
        String sql = "select username from movedata_dzb";
        List<String> dzbs = MysqlUtils.selectAllDzb(sql, connection);
        for (String username : dzbs) {
            once(username, "123456");
        }
    }

    public static void once(String userName, String password) throws Exception {
        logger.info("--------------------<" + userName + ">" + "----------------------------");
        String jsessionid = CHAppLoginByAdmin.login(userName, password);
        if (jsessionid.equals("")) {
            logger.info("党组织部<" + userName + ">" + "登录失败");
            logger.info("JSESSIONID=" + jsessionid);
            return;
        }
        String allPaid = CHAppAllPaid.appPaid(jsessionid);
        JSONObject json = JSON.parseObject(allPaid);
        if (json == null) {
            logger.error("党组织部<" + userName + ">" + "appPaid为空");
            return;
        }
        JSONArray rows = json.getJSONArray("rows");
        List<String> sqls = new ArrayList<>();
        UsrCashMonth u = null;
        for (int i = 0; i < rows.size(); i++) {
            u = new UsrCashMonth();
            JSONObject rowi = rows.getJSONObject(i);
            String name = rowi.getString("name");
            u.setName(name);
            logger.info("开始添加插入<" + name + ">的数据sql语句");
            JSONArray paids = rowi.getJSONArray("paids");
            String[] cashs = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
            String year = "";
            for (int j = 0; j < paids.size(); j++) {
                JSONObject jj = paids.getJSONObject(j);
                if (j == 0)
                    year = jj.getString("pYear");
                String id = jj.getString("id");
                String userId = jj.getString("userId");
                String cash = jj.getString("cash");
                String state = jj.getString("state");
                String dateStr = jj.getString("pDate").substring(0, 7);
                cashs[j] = cash;
            }
            u.setCashs(cashs);
            u.setZzjg(userName);
            u.setYear(year);
            //第一次比较时存库
            sqls.add(u.getInsertFirstCompare1DbSql());
            //第二次比较时存库
//            sqls.add(u.getInsertFirstCompare2DbSql());
        }
        logger.info("开始批量执行sql");
        MysqlUtils.batchSql(sqls, connection);
        logger.info("支部<" + userName + ">sql执行完成");
        logger.info("----------------------------------");
    }
}
