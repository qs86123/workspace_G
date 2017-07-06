package com.myself.edu.movedata.startmove;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.CHAppAllPaid;
import com.myself.edu.movedata.CHAppLoginByAdmin;
import com.myself.edu.movedata.ChangeStatus;
import com.myself.edu.movedata.parse.bean.UsrCashMonth;
import com.myself.edu.movedata.utils.MysqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:58 2017/6/28
 * @Email:tao8.wang@changhong.com
 */
public class ChangeStatusApp {

    private static final Logger logger = LoggerFactory.getLogger(StartApp.class);

    private static Connection connection = null;

    public static void main(String[] args) throws Exception {
        connection = MysqlUtils.getConnection("172.17.123.193", "8096", "momx_dev", "momx_dev", "momx_dev20161019");
        String sql = "select username from movedata_dzb";
        List<String> dzbs = MysqlUtils.selectAllDzb(sql, connection);
        for (String username : dzbs)
            once(username, "123456");
    }

    public static void once(String userName, String password) {
        logger.info("--------------------<" + userName + ">" + "----------------------------");
        String jsessionid = CHAppLoginByAdmin.login(userName, password);
        if (jsessionid.equals("")) {
            logger.info("党组织部<" + userName + ">" + "登录失败");
            return;
        }
        String allPaid = CHAppAllPaid.appPaid(jsessionid);
        JSONObject json = JSON.parseObject(allPaid);
        JSONArray rows = json.getJSONArray("rows");
        for (int i = 0; i < rows.size(); i++) {
            JSONObject rowi = rows.getJSONObject(i);
            String name = rowi.getString("name");
            logger.info("开始修改<" + name + ">的缴费状态");

            UsrCashMonth ucm = MysqlUtils.select("select a.* from user_cash_month a where a.username='" + name + "' and zzjg=(select b.site_name from movedata_dzb b where b.username='" + userName + "')", connection);
            if (ucm == null) {
                logger.error("用户<" + name + ">未找到");
            } else {
                JSONArray paids = rowi.getJSONArray("paids");
                for (int j = 0; j < paids.size(); j++) {
                    JSONObject jj = paids.getJSONObject(j);
                    String id = jj.getString("id");
                    String userId = jj.getString("userId");
                    String cash = jj.getString("cash");
                    String state = "已交";
                    int pMonth = jj.getIntValue("pMonth");
                    String dateStr = jj.getString("pDate").substring(0, 7);
                    if (pMonth <= 6 && Double.parseDouble(cash) != (double) 0) {
                        logger.info("{dateStr:" + dateStr + ",cash=" + cash + "}");
                        ChangeStatus.changeState(jsessionid, id, userId, cash, dateStr, state);
                    }
                }
            }
        }
    }
}
